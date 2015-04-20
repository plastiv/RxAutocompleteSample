package com.github.plastiv.rxautocompletesample.googleplaces.model;

import android.text.TextUtils;

public enum StatusCode {
    OK,
    ZeroResults,
    OverQueryLimit,
    RequestDenied,
    InvalidRequest,
    Unknown;

    private static final String STATUS_CODE_OK = "OK";
    private static final String STATUS_CODE_ZERO_RESULTS = "ZERO_RESULTS";
    private static final String STATUS_CODE_OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";
    private static final String STATUS_CODE_REQUEST_DENIED = "REQUEST_DENIED";
    private static final String STATUS_CODE_INVALID_REQUEST = "INVALID_REQUEST";

    public static StatusCode getStatusCodeFromValue(String statusCodeValue) {
        if (TextUtils.equals(statusCodeValue, STATUS_CODE_OK)) {
            return StatusCode.OK;
        } else if (TextUtils.equals(statusCodeValue, STATUS_CODE_ZERO_RESULTS)) {
            return StatusCode.ZeroResults;
        } else if (TextUtils.equals(statusCodeValue, STATUS_CODE_OVER_QUERY_LIMIT)) {
            return StatusCode.OverQueryLimit;
        } else if (TextUtils.equals(statusCodeValue, STATUS_CODE_REQUEST_DENIED)) {
            return StatusCode.RequestDenied;
        } else if (TextUtils.equals(statusCodeValue, STATUS_CODE_INVALID_REQUEST)) {
            return StatusCode.InvalidRequest;
        } else {
            return StatusCode.Unknown;
        }
    }
}
