package com.github.plastiv.rxautocompletesample.model;

/**
 * Simple POJO for the Sample purpose. Real world app would have hashCode, equals, toString and builder implemented with
 * some codegeneration tool, like AutoValue. And Object Mapper to deal with json/xml/proto serialization to
 * communicate with server api.
 */
public class Profile {
    private final Address homeAddress;
    private final Address workAddress;
    // other fields like firstName, lastName, gender, email are skipped for the Sample

    public Profile(Address homeAddress, Address workAddress) {
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Address getWorkAddress() {
        return workAddress;
    }
}
