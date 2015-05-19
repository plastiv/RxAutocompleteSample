package com.github.plastiv.rxautocompletesample;

import android.app.Application;

import com.github.plastiv.rxautocompletesample.googleplaces.GooglePlacesConnector;
import com.github.plastiv.rxautocompletesample.googleplaces.GooglePlacesConnectors;
import com.github.plastiv.rxautocompletesample.storage.ContactStorage;
import com.github.plastiv.rxautocompletesample.storage.ProfileStorage;
import com.github.plastiv.rxautocompletesample.storage.StubContactStorage;
import com.github.plastiv.rxautocompletesample.storage.StubProfileStorage;

public class AutocompleteSampleApplication extends Application {
    private GooglePlacesConnector googlePlacesConnector;
    private ProfileStorage profileStorage;
    private ContactStorage contactStorage;

    public AutocompleteSampleApplication() {
        googlePlacesConnector = GooglePlacesConnectors.getInstance();
        profileStorage = new StubProfileStorage();
        contactStorage = new StubContactStorage();
    }

    public GooglePlacesConnector getGooglePlacesConnector() {
        return googlePlacesConnector;
    }

    public void setGooglePlacesConnector(GooglePlacesConnector googlePlacesConnector) {
        this.googlePlacesConnector = googlePlacesConnector;
    }

    public ProfileStorage getProfileStorage() {
        return profileStorage;
    }

    public void setProfileStorage(ProfileStorage profileStorage) {
        this.profileStorage = profileStorage;
    }

    public ContactStorage getContactStorage() {
        return contactStorage;
    }

    public void setContactStorage(ContactStorage contactStorage) {
        this.contactStorage = contactStorage;
    }
}
