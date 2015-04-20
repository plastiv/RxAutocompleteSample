package com.github.plastiv.rxautocompletesample.storage;

import com.github.plastiv.rxautocompletesample.model.Profile;

import rx.Observable;

class StubProfileStorage implements ProfileStorage {
    @Override public Observable<Profile> get() {
        Profile profile = new Profile(null, null);
        return Observable.just(profile);
    }
}
