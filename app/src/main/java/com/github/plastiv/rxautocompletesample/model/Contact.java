package com.github.plastiv.rxautocompletesample.model;

/**
 * Simple POJO for the Sample purpose. Real world app would have hashCode, equals, toString and builder implemented with
 * some codegeneration tool, like AutoValue. And Object Mapper to deal with json/xml/proto serialization to
 * communicate with server api.
 */
public class Contact {
    private final Address address;
    // other fields like ContactName, ContactCompany are skipped for the Sample

    public Contact(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
