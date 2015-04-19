package com.github.plastiv.rxautocompletesample.domain;

import java.util.List;

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
        return Observable.empty();
    }

    public Observable<Address> contactAddress(final String search) {
        List<Contact> contacts = contactStorage.getAll();
        return Observable.from(contacts)
                         .map(new Func1<Contact, Address>() {
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
        Profile profile = profileStorage.get();
        return Observable.just(profile.getHomeAddress(), profile.getWorkAddress())
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
