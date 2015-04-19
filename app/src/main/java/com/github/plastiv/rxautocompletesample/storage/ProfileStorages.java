package com.github.plastiv.rxautocompletesample.storage;

public class ProfileStorages {
    private ProfileStorages() {
        throw new AssertionError("No instances.");
    }

    public static ProfileStorage getInstance() {
        return new StubProfileStorage();
    }
}
