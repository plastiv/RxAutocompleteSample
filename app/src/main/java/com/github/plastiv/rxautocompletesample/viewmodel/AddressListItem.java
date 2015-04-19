package com.github.plastiv.rxautocompletesample.viewmodel;

import com.github.plastiv.rxautocompletesample.model.Address;

public class AddressListItem {
    private final String firstLine;
    private final String secondLine;
    private final int iconRes;
    private final Address address;

    // real app would use drawable res annotation for static analyze tools
    public AddressListItem(String firstLine, String secondLine, int iconRes, Address address) {
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.iconRes = iconRes;
        this.address = address;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public int getIconRes() {
        return iconRes;
    }

    public Address getAddress() {
        return address;
    }
}
