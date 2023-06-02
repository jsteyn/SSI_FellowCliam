package com.jannetta.SSI_FellowClaim.model;

public class Expense implements Comparable<Expense> {


    @Override
    public int compareTo(Expense o) {
        return Integer.valueOf(getReceipt_number()).compareTo(Integer.valueOf(o.getReceipt_number()));
    }

    enum type {
        UNSET, TRAVEL, SUBSISTENCE, OTHER
    }

    private String description;
    private String expenseType;
    private double amount_currency;
    private double amount_gbp;
    private int receipt_number;

    public Expense(String description, String expenseType, double amount_currency, double amount_gbp, int receipt_number) {
        this.description = description;
        this.expenseType = expenseType;
        this.amount_currency = amount_currency;
        this.amount_gbp = amount_gbp;
        this.receipt_number = receipt_number;
    }

    public Expense() {
        this.description = "";
        this.expenseType = "UNSET";
        this.amount_currency = 0;
        this.amount_gbp = 0;
        this.receipt_number = 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public double getAmount_currency() {
        return amount_currency;
    }

    public void setAmount_currency(double amount_currency) {
        this.amount_currency = amount_currency;
    }

    public double getAmount_gbp() {
        return amount_gbp;
    }

    public void setAmount_gbp(double amount_gbp) {
        this.amount_gbp = amount_gbp;
    }

    public int getReceipt_number() {
        return receipt_number;
    }

    public void setReceipt_number(int receipt_number) {
        this.receipt_number = receipt_number;
    }
}
