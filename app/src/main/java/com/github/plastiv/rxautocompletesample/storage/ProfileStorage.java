package com.github.plastiv.rxautocompletesample.storage;

import com.github.plastiv.rxautocompletesample.model.Profile;

import rx.Observable;

public interface ProfileStorage {

    Observable<Profile> get();
    // other methods like update, delete are omitted
}
