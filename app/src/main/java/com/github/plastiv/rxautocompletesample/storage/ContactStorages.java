package com.github.plastiv.rxautocompletesample.storage;

public class ContactStorages {
    private ContactStorages() {
        throw new AssertionError("No instances.");
    }

    public static ContactStorage getInstance() {
        return new StubContactStorage();
    }
}
