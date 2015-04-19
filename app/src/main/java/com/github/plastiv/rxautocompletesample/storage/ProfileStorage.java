package com.github.plastiv.rxautocompletesample.storage;

import com.github.plastiv.rxautocompletesample.model.Profile;

public interface ProfileStorage {
    Profile get();
    // other methods like update, delete are omitted
}
