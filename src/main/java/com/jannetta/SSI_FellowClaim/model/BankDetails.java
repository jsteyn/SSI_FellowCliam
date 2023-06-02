package com.jannetta.SSI_FellowClaim.model;

import com.google.gson.annotations.Expose;

public class BankDetails {
    @Expose
    String bank;
    @Expose
    String branch;
    @Expose
    String account;
    @Expose
    String sortCode;
    @Expose
    String iban;
    @Expose
    String swift;

    public void setSection2(String bank, String branch, String account, String sortCode, String iban, String swift) {
        this.bank = bank;
        this.branch = branch;
        this.account = account;
        this.sortCode = sortCode;
        this.iban = iban;
        this.swift = swift;
    }

    public BankDetails() {

    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }
}
