package com.jannetta.SSI_FellowClaim.model;

import java.util.ArrayList;

public class Expenses extends ArrayList<Expense> {
    private double travelTotal;
    private double subsistenceTotal;
    private double otherTotal;
    private double grandTotal;

    public void updateTotals() {
        travelTotal = 0;
        otherTotal = 0;
        subsistenceTotal = 0;
        for (int i = 0; i < this.size(); i++) {
            Expense expense = this.get(i);
            switch (expense.getExpenseType()) {
                case "Travel" -> travelTotal += expense.getAmount_gbp();
                case "Subsistence" -> subsistenceTotal += expense.getAmount_gbp();
                case "Other" -> otherTotal += expense.getAmount_gbp();
            }
        }

        grandTotal = subsistenceTotal + travelTotal + otherTotal;
    }

    public double getTravelTotal() {
        return travelTotal;
    }

    public void setTravelTotal(double travelTotal) {
        this.travelTotal = travelTotal;
    }

    public double getSubsistenceTotal() {
        return subsistenceTotal;
    }

    public void setSubsistenceTotal(double subsistenceTotal) {
        this.subsistenceTotal = subsistenceTotal;
    }

    public double getOtherTotal() {
        return otherTotal;
    }

    public void setOtherTotal(double otherTotal) {
        this.otherTotal = otherTotal;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }
}
