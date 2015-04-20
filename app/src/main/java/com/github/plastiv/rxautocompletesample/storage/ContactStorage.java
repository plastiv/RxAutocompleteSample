package com.github.plastiv.rxautocompletesample.storage;

import com.github.plastiv.rxautocompletesample.model.Contact;

import rx.Observable;

public interface ContactStorage {
    Observable<Contact> getAll();
    // other methods like getById, updateById, deleteById, deleteAll are omitted for the Sample
}
