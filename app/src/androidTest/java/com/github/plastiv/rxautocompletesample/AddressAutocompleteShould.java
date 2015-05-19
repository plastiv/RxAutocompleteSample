package com.github.plastiv.rxautocompletesample;

import java.util.Map;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.plastiv.rxautocompletesample.googleplaces.GooglePlacesConnector;
import com.github.plastiv.rxautocompletesample.googleplaces.model.PredictionResult;
import com.github.plastiv.rxautocompletesample.model.Address;
import com.github.plastiv.rxautocompletesample.model.Contact;
import com.github.plastiv.rxautocompletesample.model.Profile;
import com.github.plastiv.rxautocompletesample.storage.ContactStorage;
import com.github.plastiv.rxautocompletesample.storage.ProfileStorage;
import com.github.plastiv.rxautocompletesample.view.AddressAutocompleteActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.plugins.RxJavaObservableExecutionHook;
import rx.plugins.RxJavaPlugins;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.google.android.apps.common.testing.deps.guava.base.Preconditions.checkNotNull;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class AddressAutocompleteShould {

    private ProfileStorage profileStorage;
    private ContactStorage contactStorage;
    private GooglePlacesConnector googlePlacesConnector;

    @Rule
    public ActivityTestRule<AddressAutocompleteActivity> rule =
            new ActivityTestRule<AddressAutocompleteActivity>(AddressAutocompleteActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    AutocompleteSampleApplication application =
                            (AutocompleteSampleApplication) InstrumentationRegistry.getTargetContext()
                                                                                   .getApplicationContext();

                    profileStorage = mock(ProfileStorage.class);
                    application.setProfileStorage(profileStorage);
                    contactStorage = mock(ContactStorage.class);
                    application.setContactStorage(contactStorage);
                    googlePlacesConnector = mock(GooglePlacesConnector.class);
                    application.setGooglePlacesConnector(googlePlacesConnector);
                }
            };

    @Test
    public void display_matched_address() throws Exception {
        given(contactStorage.getAll()).willReturn(contactWithAddress("Kharkiv"));
        given(profileStorage.get()).willReturn(profileWithAddress("London", "Berlin"));
        given(googlePlacesConnector.autocomplete(any(String.class), any(String.class)))
                .willReturn(Observable.<PredictionResult>empty());

        onView(withId(R.id.action_search))
                .perform(typeText("Khar"), pressKey(KeyEvent.KEYCODE_ENTER));

        onData(anything())
                .inAdapterView(withId(R.id.addressListView))
                .atPosition(0)
                .check(matches(hasDescendant(
                        allOf(withId(R.id.firstLineView), withText(containsString("Kharkiv"))))));

        onData(anything())
                .inAdapterView(withId(R.id.addressListView))
                .check(matches(not(hasDescendant(
                        allOf(withId(R.id.firstLineView), withText(containsString("Berlin")))))));
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
