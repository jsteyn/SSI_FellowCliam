package com.jannetta.SSI_FellowClaim.view;

import com.jannetta.SSI_FellowClaim.model.PersonalDetails;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class PersonalDetailsPanel extends JPanel {
    private JTextField fullName = new JTextField(50);
    private JTextArea address = new JTextArea(5, 50);
    private JTextField number = new JTextField(50);
    private JTextField bacs_email = new JTextField(50);
    JButton submit = new JButton("submit");

    MigLayout migLayout = new MigLayout("fillx", "[]rel[]", "[]10[]");
    JPanel buttons = new JPanel();

    PersonalDetailsPanel() {

        setLayout(migLayout);
        add(new JLabel("Full Name: "));
        add(fullName, "wrap");
        add(new JLabel("Address: "));
        add(address, "wrap");
        add(new JLabel("Visitor/Student number: "));
        add(number, "wrap");
        add(new JLabel("Email Address (for BACS): "));
        add(bacs_email, "wrap");
    }

    public String getFullName() {
        return fullName.getText();
    }


    public String getAddress() {
        return address.getText();
    }

     public String getNumber() {
        return number.getText();
    }


    public String getBacs_email() {
        return bacs_email.getText();
    }

    public void updateData(PersonalDetails s1) {
        this.fullName.setText(s1.getFullName());
        this.address.setText(s1.getAddress());
        this.number.setText(s1.getNumber());
        this.bacs_email.setText(s1.getBacs_email());
    }


}
