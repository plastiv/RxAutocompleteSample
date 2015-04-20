package com.github.plastiv.rxautocompletesample.googleplaces.model;

import java.util.List;

import android.text.TextUtils;

public class AddressComponent {

    private String longName;
    private List<String> types;
    // other fields are skipped

    public String getLongName() {
        return longName;
    }

    public List<String> getTypes() {
        return types;
    }

    public boolean isStreetNumber() {
        return isType("street_number");
    }

    public boolean isRoute() {
        return isType("route");
    }

    public boolean isLocality() {
        return isType("locality");
    }

    public boolean isCountry() {
        return isType("country");
    }

    public boolean isPostalCode() {
        return isType("postal_code");
    }

    private boolean isType(String typeName) {
        for (String type : types) {
            if (TextUtils.equals(type, typeName)) {
                return true;
            }
        }
        return false;
    }
}
