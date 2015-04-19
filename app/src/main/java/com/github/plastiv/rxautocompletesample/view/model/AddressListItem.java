package com.github.plastiv.rxautocompletesample.view.model;

import com.github.plastiv.rxautocompletesample.model.Address;

public class AddressListItem {
    private final String firstLine;
    private final String secondLine;
    private final Address address;

    // real app would use drawable res annotation for static analyze tools
    public AddressListItem(String firstLine, String secondLine, Address address) {
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.address = address;
    }

    public static AddressListItem from(Address address) {
        return new AddressListItem(address.getStreetName() + address.getHouseNumber(), address.getCity(), address);
    }

    public String getFirstLine() {
        return firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public Address getAddress() {
        return address;
    }
}
