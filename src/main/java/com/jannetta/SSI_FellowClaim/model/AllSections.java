package com.jannetta.SSI_FellowClaim.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class AllSections {

    @Expose
    PersonalDetails personalDetails;
    @Expose
    BankDetails bankDetails;
    @Expose
    ExpenseSummary expenseSummary;
    @Expose
    Expenses expenses;

    public AllSections() {
        this.personalDetails = new PersonalDetails();
        this.bankDetails = new BankDetails();
        this.expenses = new Expenses();
        this.expenseSummary = new ExpenseSummary();
    }

    public void setAll(AllSections other) {
        this.personalDetails = other.personalDetails;
        this.bankDetails = other.bankDetails;
        this.expenses = other.expenses;
        this.expenses.updateTotals();
        this.expenseSummary = other.expenseSummary;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public ExpenseSummary getExpenseSummary() {
        return expenseSummary;
    }

    public Expenses getExpenses() {
        return expenses;
    }

    public void setPersonalDetails(String fullName, String address, String number, String bacs_email) {
        personalDetails.setSection1(fullName, address, number, bacs_email);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    }
    public void setBankDetails(String bank, String branch, String account, String sort_code, String iban, String swift) {
        bankDetails.setSection2(bank, branch, account, sort_code, iban, swift);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    }
    public void setExpenseSummary(double travelTotal, double mileageTotal, double perMile, double subsistence, double other,
                                  String currency, String visit, String date, String purpose, String signature,
                                  String signedDate) {
        expenseSummary.setExpenseSummary(travelTotal, mileageTotal, perMile, subsistence, other, currency, visit, date, purpose,
                signature, signedDate);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    }
}
