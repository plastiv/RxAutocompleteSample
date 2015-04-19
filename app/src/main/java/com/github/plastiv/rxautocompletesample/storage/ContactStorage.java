package com.github.plastiv.rxautocompletesample.storage;

import java.util.List;

import com.github.plastiv.rxautocompletesample.model.Contact;

public interface ContactStorage {
    List<Contact> getAll();
    // other methods like getById, updateById, deleteById, deleteAll are omitted for the Sample
}
