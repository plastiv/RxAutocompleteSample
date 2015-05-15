package com.github.plastiv.rxautocompletesample.domain;

import java.util.List;
import java.util.concurrent.TimeUnit;

import android.text.TextUtils;

import com.github.plastiv.rxautocompletesample.googleplaces.GooglePlacesApiError;
import com.github.plastiv.rxautocompletesample.googleplaces.GooglePlacesConnector;
import com.github.plastiv.rxautocompletesample.googleplaces.model.AddressComponent;
import com.github.plastiv.rxautocompletesample.googleplaces.model.PlaceDetails;
import com.github.plastiv.rxautocompletesample.googleplaces.model.PlaceDetailsResult;
import com.github.plastiv.rxautocompletesample.googleplaces.model.Prediction;
import com.github.plastiv.rxautocompletesample.googleplaces.model.PredictionResult;
import com.github.plastiv.rxautocompletesample.googleplaces.model.StatusCode;
import com.github.plastiv.rxautocompletesample.model.Address;
import com.github.plastiv.rxautocompletesample.model.Contact;
import com.github.plastiv.rxautocompletesample.model.Profile;
import com.github.plastiv.rxautocompletesample.storage.ContactStorage;
import com.github.plastiv.rxautocompletesample.storage.ProfileStorage;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

public class AddressAutocomplete {

    private final ProfileStorage profileStorage;
    private final ContactStorage contactStorage;
    private final GooglePlacesConnector googlePlacesConnector;
    private final PublishSubject<String> searchQuery;

    public AddressAutocomplete(ProfileStorage profileStorage,
                               ContactStorage contactStorage,
                               GooglePlacesConnector googlePlacesConnector) {
        this.profileStorage = profileStorage;
        this.contactStorage = contactStorage;
        this.googlePlacesConnector = googlePlacesConnector;
        searchQuery = PublishSubject.create();
    }

    public Observable<List<Address>> asObservable() {
        return searchQuery.asObservable()
                          .debounce(500, TimeUnit.MILLISECONDS)
                          .switchMap(new Func1<String, Observable<List<Address>>>() {
                              @Override public Observable<List<Address>> call(String s) {
                                  return Observable.merge(contactAddress(s),
                                                          profileAddress(s),
                                                          googleAddress(s))
                                                   .toList();
                              }
                          });
    }

    public void onQueryChange(String query) {
        searchQuery.onNext(query);
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

    public Observable<Address> googleAddress(final String search) {
        if (TextUtils.isEmpty(search) || search.length() < 2) {
            return Observable.empty();
        }

        return googlePlacesConnector
                .autocomplete(search, "country:de")
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends PredictionResult>>() {
                    @Override public Observable<? extends PredictionResult> call(Throwable throwable) {
                        if (throwable instanceof GooglePlacesApiError) {
                            GooglePlacesApiError error = (GooglePlacesApiError) throwable;
                            if (error.getStatusCode() == StatusCode.InvalidRequest) {
                                // if country is not resolved, try without it
                                return googlePlacesConnector.autocomplete(search, null);
                            }
                        }
                        // hide error. If google api request fail, show contact or profile instead
                        return Observable.empty();
                    }
                })
                .flatMap(new Func1<PredictionResult, Observable<Prediction>>() {
                    @Override public Observable<Prediction> call(PredictionResult predictionResult) {
                        return Observable.from(predictionResult.getPredictions());
                    }
                })
                .flatMap(new Func1<Prediction, Observable<PlaceDetailsResult>>() {
                    @Override public Observable<PlaceDetailsResult> call(Prediction prediction) {
                        return googlePlacesConnector.details(prediction.getPlaceId());
                    }
                })
                .onErrorResumeNext(Observable.<PlaceDetailsResult>empty())
                .map(new Func1<PlaceDetailsResult, Address>() {
                    @Override public Address call(PlaceDetailsResult placeDetailsResult) {
                        PlaceDetails details = placeDetailsResult.getResult();
                        return convert(details);
                    }
                });
    }

    public static Address convert(PlaceDetails placeDetails) {
        Address.Builder builder = new Address.Builder();
        List<AddressComponent> addressComponents = placeDetails.getAddressComponents();
        for (AddressComponent component : addressComponents) {
            if (component.isCountry()) {
                builder.country(component.getLongName());
            } else if (component.isLocality()) {
                builder.city(component.getLongName());
            } else if (component.isPostalCode()) {
                builder.postalCode(component.getLongName());
            } else if (component.isRoute()) {
                builder.streetName(component.getLongName());
            } else if (component.isStreetNumber()) {
                builder.houseNumber(component.getLongName());
            }
        }
        return builder.build();
    }
}
