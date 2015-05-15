package com.github.plastiv.rxautocompletesample.model;

import android.text.TextUtils;

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
        return contains(streetName, query) || contains(city, query) || contains(country, query);
    }

    private boolean contains(String str, String searchStr) {
        if (str != null) {
            return str.contains(searchStr);
        }
        return false;
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

    /*
    * Real app would get rid of the equals / hascode / tostring boilerplate with AutoValue
    * */

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }

        Address address = (Address) o;

        if (Double.compare(address.latitude, latitude) != 0) {
            return false;
        }
        if (Double.compare(address.longitude, longitude) != 0) {
            return false;
        }
        if (streetName != null ? !streetName.equals(address.streetName) : address.streetName != null) {
            return false;
        }
        if (houseNumber != null ? !houseNumber.equals(address.houseNumber) : address.houseNumber != null) {
            return false;
        }
        if (postalCode != null ? !postalCode.equals(address.postalCode) : address.postalCode != null) {
            return false;
        }
        if (city != null ? !city.equals(address.city) : address.city != null) {
            return false;
        }
        if (country != null ? !country.equals(address.country) : address.country != null) {
            return false;
        }

        return true;
    }

    @Override public int hashCode() {
        int result;
        long temp;
        result = streetName != null ? streetName.hashCode() : 0;
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("streetName='").append(streetName).append('\'');
        sb.append(", houseNumber='").append(houseNumber).append('\'');
        sb.append(", postalCode='").append(postalCode).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }
}
