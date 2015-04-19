package com.github.plastiv.rxautocompletesample.domain;

import com.github.plastiv.rxautocompletesample.model.Address;

import rx.Observable;

public class Addresses {

    public Observable<Address> autocomplete(Observable<String> search) {
        return Observable.empty();
    }
}
