package com.github.plastiv.rxautocompletesample.storage;

import java.util.Collections;
import java.util.List;

import com.github.plastiv.rxautocompletesample.model.Contact;

class StubContactStorage implements ContactStorage {

    @Override public List<Contact> getAll() {
        return Collections.emptyList();
    }
}
