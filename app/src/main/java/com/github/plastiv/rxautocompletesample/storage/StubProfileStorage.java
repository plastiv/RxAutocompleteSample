package com.github.plastiv.rxautocompletesample.storage;

import com.github.plastiv.rxautocompletesample.model.Profile;

class StubProfileStorage implements ProfileStorage {
    @Override public Profile get() {
        Profile profile = new Profile(null, null);
        return profile;
    }
}
