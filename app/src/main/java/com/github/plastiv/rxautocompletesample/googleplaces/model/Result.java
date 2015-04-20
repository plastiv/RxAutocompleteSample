package com.github.plastiv.rxautocompletesample.googleplaces.model;

public abstract class Result {

    private String status;

    public String getStatus() {
        return status;
    }

    public StatusCode getStatusCode() {
        return StatusCode.getStatusCodeFromValue(status);
    }

    public boolean isSuccessful() {
        StatusCode statusCode = getStatusCode();
        return statusCode == StatusCode.OK || statusCode == StatusCode.ZeroResults;
    }
}
