package com.jannetta.SSI_FellowClaim.view;

import com.jannetta.SSI_FellowClaim.model.BankDetails;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class BankDetailsPanel extends JPanel {

    MigLayout migLayout = new MigLayout("fillx", "[]rel[]", "[]10[]");

    JTextField txt_bank = new JTextField(50);
    JTextField txt_branch= new JTextField(50);;
    JTextField txt_account= new JTextField(50);;
    JTextField txt_sort_code= new JTextField(50);;
    JTextField txt_iban= new JTextField(50);;
    JTextField txt_swift= new JTextField(50);;

    BankDetailsPanel() {
        setLayout(migLayout);
        add(new JLabel("Bank / Building Society name:"));
        add(txt_bank, "wrap");
        add(new JLabel("Branch address::"));
        add(txt_branch, "wrap");
        add(new JLabel("Account Number/Roll Number:"));
        add(txt_account, "wrap");
        add(new JLabel("Sort Code:"));
        add(txt_sort_code, "wrap");
        add(new JLabel("IBAN:"));
        add(txt_iban, "wrap");
        add(new JLabel("BIC/SWIFT:"));
        add(txt_swift, "wrap");

    }

    public String getBank() {
        return txt_bank.getText();
    }
    public String getBranch() {
        return txt_branch.getText();
    }
    public String getAccount() {
        return txt_account.getText();
    }
    public String getSort_code() {
        return txt_sort_code.getText();
    }
    public String getIban() {
        return txt_iban.getText();
    }
    public String getSwift() {
        return txt_swift.getText();
    }

    public void updateData(BankDetails s2) {
        txt_bank.setText(s2.getBank());
        txt_branch.setText(s2.getBranch());
        txt_account.setText(s2.getAccount());
        txt_sort_code.setText(s2.getSortCode());
        txt_iban.setText(s2.getIban());
        txt_swift.setText(s2.getSwift());
    }
}
