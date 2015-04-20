package com.github.plastiv.rxautocompletesample.domain;

import java.util.concurrent.TimeUnit;

import com.github.plastiv.rxautocompletesample.model.Address;
import com.github.plastiv.rxautocompletesample.model.Contact;
import com.github.plastiv.rxautocompletesample.model.Profile;
import com.github.plastiv.rxautocompletesample.storage.ContactStorage;
import com.github.plastiv.rxautocompletesample.storage.ProfileStorage;

import rx.Observable;
import rx.functions.Func1;

public class AddressAutocompleteInteractor {

    private final ProfileStorage profileStorage;
    private final ContactStorage contactStorage;

    public AddressAutocompleteInteractor(ProfileStorage profileStorage, ContactStorage contactStorage) {
        this.profileStorage = profileStorage;
        this.contactStorage = contactStorage;
    }

    public Observable<Address> autocomplete(Observable<String> search) {
        return search
                .debounce(500, TimeUnit.MILLISECONDS)
                .switchMap(new Func1<String, Observable<? extends Address>>() {
                    @Override public Observable<? extends Address> call(String s) {
                        return Observable.merge(contactAddress(s),
                                                profileAddress(s));
                    }
                });
    }

    public Observable<Address> contactAddress(final String search) {
        Observable<Contact> contacts = contactStorage.getAll();
        return contacts.map(new Func1<Contact, Address>() {
            @Override public Address call(Contact contact) {
                return contact.getAddress();
            }
        })
                       .filter(new Func1<Address, Boolean>() {
                           @Override public Boolean call(Address address) {
                               if (address == null) {
                                   return false;
                               }
                               return address.contains(search);
                           }
                       });
    }

    public Observable<Address> profileAddress(final String search) {
        Observable<Profile> profile = profileStorage.get();
        return profile.flatMap(new Func1<Profile, Observable<Address>>() {
            @Override public Observable<Address> call(Profile profile) {
                return Observable.just(profile.getHomeAddress(),
                                       profile.getWorkAddress());
            }
        })
                      .filter(new Func1<Address, Boolean>() {
                          @Override public Boolean call(Address address) {
                              if (address == null) {
                                  return false;
                              }
                              return address.contains(search);
                          }
                      });
    }
}
