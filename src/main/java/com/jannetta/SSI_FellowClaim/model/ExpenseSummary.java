package com.jannetta.SSI_FellowClaim.model;

import com.google.gson.annotations.Expose;

public class ExpenseSummary {

//    @Expose
    double travelTotal;
    double subsistence;
    double other;
    @Expose
    double mileageTotal;
    @Expose
    double perMile;
    @Expose
    String currency;
    @Expose
    String visit;
    @Expose
    String date;
    @Expose
    String purpose;
    @Expose
    String signatureFile;
    @Expose
    String signedDate;
//    double grandTotal;

    public ExpenseSummary() {
    }


    public double getTravelTotal() {
        return travelTotal;
    }

    public void setTravelTotal(double travelTotal) {
        this.travelTotal = travelTotal;
    }

    public double getMileageTotal() {
        return mileageTotal;
    }

    public void setMileageTotal(double mileageTotal) {
        this.mileageTotal = mileageTotal;
    }

    public double getPerMile() {
        return perMile;
    }

    public void setPerMile(double perMile) {
        this.perMile = perMile;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSignatureFile() {
        return signatureFile;
    }

    public void setSignatureFile(String signatureFile) {
        this.signatureFile = signatureFile;
    }

    public String getSignedDate() {
        return signedDate;
    }

    public double getSubsistence() {
        return subsistence;
    }

    public void setSubsistence(double subsistence) {
        this.subsistence = subsistence;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public void setExpenseSummary(double travelTotal, double mileageTotal, double perMile, double subsistence, double other,
                                  String currency, String visit, String date, String purpose, String signature,
                                  String signedDate) {
        this.travelTotal = travelTotal;
        this.mileageTotal = mileageTotal;
        this.perMile = perMile;
        this.subsistence = subsistence;
        this.other = other;
        this.currency = currency;
        this.visit = visit;
        this.date = date;
        this.purpose = purpose;
        this.signatureFile = signature;
        this.signedDate = signedDate;
    }

}
