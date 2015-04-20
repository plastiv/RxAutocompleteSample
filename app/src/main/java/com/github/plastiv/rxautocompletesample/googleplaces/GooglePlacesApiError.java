package com.github.plastiv.rxautocompletesample.googleplaces;

import com.github.plastiv.rxautocompletesample.googleplaces.model.StatusCode;

public class GooglePlacesApiError extends RuntimeException {

    private final StatusCode statusCode;

    public GooglePlacesApiError(String googleApiErrorStatus) {
        super("Google api error: " + googleApiErrorStatus);
        statusCode = StatusCode.getStatusCodeFromValue(googleApiErrorStatus);
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
