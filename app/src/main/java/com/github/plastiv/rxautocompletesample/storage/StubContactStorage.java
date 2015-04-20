package com.github.plastiv.rxautocompletesample.storage;

import java.util.Collections;
import java.util.List;

import com.github.plastiv.rxautocompletesample.model.Contact;

import rx.Observable;

class StubContactStorage implements ContactStorage {

    @Override public Observable<Contact> getAll() {
        return Observable.empty();
    }
}
