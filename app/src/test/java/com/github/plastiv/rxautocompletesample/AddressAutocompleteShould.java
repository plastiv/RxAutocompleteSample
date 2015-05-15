package com.github.plastiv.rxautocompletesample;

import java.util.List;

import com.github.plastiv.rxautocompletesample.domain.AddressAutocomplete;
import com.github.plastiv.rxautocompletesample.googleplaces.GooglePlacesConnector;
import com.github.plastiv.rxautocompletesample.googleplaces.model.PredictionResult;
import com.github.plastiv.rxautocompletesample.model.Address;
import com.github.plastiv.rxautocompletesample.model.Contact;
import com.github.plastiv.rxautocompletesample.model.Profile;
import com.github.plastiv.rxautocompletesample.storage.ContactStorage;
import com.github.plastiv.rxautocompletesample.storage.ProfileStorage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.Subscription;
import rx.android.RecordingObserver;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AddressAutocompleteShould {

    /* Real app would use real dependencies.
     * Mock is used here because there is no real filebased or memory based storage implemented. */
    @Mock ContactStorage contactStorage;
    @Mock ProfileStorage profileStorage;
    @Mock GooglePlacesConnector googlePlacesConnector;

    private AddressAutocomplete addressAutocomplete;

    @Before
    public void initialise() {
        addressAutocomplete = new AddressAutocomplete(profileStorage, contactStorage, googlePlacesConnector);
        given(contactStorage.getAll()).willReturn(contactWithAddress("Kharkiv"));
        given(profileStorage.get()).willReturn(profileWithAddress("London", "Berlin"));
        given(googlePlacesConnector.autocomplete(any(String.class), any(String.class)))
                .willReturn(Observable.<PredictionResult>empty());
    }

    @Test public void
    autocomplete_addresses() {
        final RecordingObserver<List<Address>> o = new RecordingObserver<>();
        Subscription subscription = addressAutocomplete.asObservable().subscribe(o);
        o.assertNoMoreEvents(); // No initial value.

        addressAutocomplete.onQueryChange("");
        assertThat(o.takeNext()).containsExactly(address("Kharkiv"),
                                                 address("London"),
                                                 address("Berlin"));

        addressAutocomplete.onQueryChange("Ber");
        assertThat(o.takeNext()).containsExactly(address("Berlin"));

        subscription.unsubscribe();
        addressAutocomplete.onQueryChange("should-not-work");
        o.assertNoMoreEvents();
    }

    private Observable<Contact> contactWithAddress(String streetName) {
        return Observable.just(new Contact(address(streetName)));
    }

    private Observable<Profile> profileWithAddress(String homeStreetName, String workStreetName) {
        return Observable.just(new Profile(address(homeStreetName), address(workStreetName)));
    }

    private Address address(String streetName) {
        return new Address.Builder()
                .streetName(streetName)
                .build();
    }
}
