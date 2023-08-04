package com.jannetta.SSI_FellowClaim.model;

import com.google.gson.annotations.Expose;

public class PersonalDetails {
    @Expose
    private String fullName;
    @Expose
    private String address;
    @Expose
    private String number;
    @Expose
    private String bacs_email;

    public void setSection1(String fullName, String address, String number, String bacs_email) {
        this.fullName = fullName;
        this.address = address;
        this.number = number;
        this.bacs_email = bacs_email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getBacs_email() {
        return bacs_email;
    }
}

