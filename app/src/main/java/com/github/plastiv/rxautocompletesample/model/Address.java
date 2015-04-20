package com.github.plastiv.rxautocompletesample.model;

/**
 * Simple POJO for the Sample purpose. Real world app would have hashCode, equals, toString and builder implemented with
 * some codegeneration tool, like AutoValue. And Object Mapper to deal with json/xml/proto serialization to communicate
 * with server api.
 */
public class Address {
    private final String streetName;
    private final String houseNumber;
    private final String postalCode;
    private final String city;
    private final String country;
    private final double latitude;
    private final double longitude;

    Address(String streetName, String houseNumber, String postalCode, String city, String country,
            double latitude, double longitude) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean contains(String query) {
        return streetName.contains(query) || city.contains(query) || country.contains(query);
    }

    public static class Builder {
        private String streetName;
        private String houseNumber;
        private String postalCode;
        private String city;
        private String country;
        private double latitude;
        private double longitude;

        public Builder() {
        }

        public Builder streetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder houseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Address build() {
            // real app would have assertions and field validations. they are skipped to keep sample simple
            return new Address(streetName, houseNumber, postalCode, city, country, latitude, longitude);
        }
    }
}
