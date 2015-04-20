package com.github.plastiv.rxautocompletesample.googleplaces.model;

import java.util.List;

public class PlaceDetails {

    private String formattedAddress;
    private Geometry geometry;
    private List<AddressComponent> addressComponents;
    // other fields are skipped

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public double getLongitude() {
        return geometry.getLocation().getLng();
    }

    public double getLatitude() {
        return geometry.getLocation().getLat();
    }
}
