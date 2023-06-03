package com.jannetta.SSI_FellowClaim.view;

import com.jannetta.SSI_FellowClaim.model.AllSections;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExpenseSummaryPanel extends JPanel implements ActionListener {
    Logger logger = Logger.getLogger(getClass().getName());

    JTextField txt_travelTotal = new JTextField("0", 10);
    JTextField txt_mileageTotal = new JTextField("0",5);
    JTextField txt_perMile = new JTextField("0", 5);
    JTextField txt_subsistence = new JTextField("0", 10);
    JTextField txt_other = new JTextField("0", 10);
    JTextField txt_grandTotal = new JTextField("0", 10);
    JTextField txt_currency = new JTextField(5);
    JTextField txt_visit = new JTextField(50);
    JTextField txt_date = new JTextField(10);
    JTextField txt_purpose = new JTextField(50);
    JTextField txt_signatureFile = new JTextField( "unset", 50);
    JLabel lbl_signature = new JLabel();
    JTextField txt_signedDate = new JTextField(10);
    JButton btn_signature = new JButton("Select File");
    MigLayout migLayout = new MigLayout("fillx", "[]rel[]", "[]10[]");

    ExpenseSummaryPanel() {
        setLayout(migLayout);
        btn_signature.addActionListener(this);
        txt_mileageTotal.setHorizontalAlignment(JTextField.RIGHT);
        txt_perMile.setHorizontalAlignment(JTextField.RIGHT);
        txt_grandTotal.setEditable(false);
        txt_grandTotal.setHorizontalAlignment(JTextField.RIGHT);
        txt_subsistence.setEditable(false);
        txt_subsistence.setHorizontalAlignment(JTextField.RIGHT);
        txt_other.setEditable(false);
        txt_other.setHorizontalAlignment(JTextField.RIGHT);
        txt_travelTotal.setEditable(false);
        txt_travelTotal.setHorizontalAlignment(JTextField.RIGHT);

        add(new JLabel("Fares (i.e. air, train, bus, taxi etc): "));
        add(txt_travelTotal,"wrap");
        add(new JLabel("Mileage Allowance: "));
        add(txt_mileageTotal,"wrap");
        add(new JLabel("Rate per mile: "));
        add(txt_perMile,"wrap");
        add(new JLabel("Subsistence:"));
        add(txt_subsistence,"wrap");
        add(new JLabel("Other Expenses: "));
        add(txt_other,"wrap");
        add(new JLabel("Total: "));
        add(txt_grandTotal, "wrap");
        add(new JLabel("Payment Currency (i.e. GBP, USD, EUR: "));
        add(txt_currency,"wrap");
        add(new JLabel("My visit to: "));
        add(txt_visit,"wrap");
        add(new JLabel("On (date) DD/MM/YYYY: "));
        add(txt_date,"wrap");
        add(new JLabel("For the purpose of: "));
        add(txt_purpose,"wrap");
        add(new JLabel("Signature (select file): "));
        add(txt_signatureFile);
        add(btn_signature,"wrap");
        add(lbl_signature,"wrap");
        add(new JLabel("Date signed: "));
        add(txt_signedDate,"wrap");
    }

    public void updateData(AllSections allSections) {
        clearAll();
        logger.log(Level.INFO, "Updating ExpenseSummaryPanel");
        setTravelTotal(allSections.getExpenses().getTravelTotal());
        setMileageTotal(allSections.getExpenseSummary().getMileageTotal());
        setSubsistence(allSections.getExpenses().getSubsistenceTotal());
        setOther(allSections.getExpenses().getOtherTotal());
        setCurrency(allSections.getExpenseSummary().getCurrency());
        setVisit(allSections.getExpenseSummary().getVisit());
        setDate(allSections.getExpenseSummary().getDate());
        setPurpose(allSections.getExpenseSummary().getPurpose());
        setSignatureFile(allSections.getExpenseSummary().getSignatureFile());
        setSignedDate(allSections.getExpenseSummary().getSignedDate());
        setGrandTotal(allSections.getExpenses().getGrandTotal());
        if  (!(allSections.getExpenseSummary().getSignatureFile()==null))
        if (!allSections.getExpenseSummary().getSignatureFile().equals("unset")) {
            displaySignature(new File(allSections.getExpenseSummary().getSignatureFile()));
        }
    }

    public void clearAll() {
        txt_travelTotal.setText("0");
        txt_perMile.setText("0");
        txt_mileageTotal.setText("0");
        txt_subsistence.setText("0");
        txt_other.setText("0");
        txt_grandTotal.setText("0");

    }


     public void setMileageTotal(double mileageTotal) {
        txt_mileageTotal.setText(String.valueOf(mileageTotal));
    }
    public void setPerMile(double perMile) {
        txt_perMile.setText(String.valueOf(perMile));;
    }
    public void setTravelTotal(double travelTotal) {
        txt_travelTotal.setText(String.format("%17.2f",travelTotal));
    }
    public void setSubsistence(double subsistence) {
        txt_subsistence.setText(String.format("%17.2f",subsistence));
    }
    public void setOther(double other) {
        txt_other.setText(String.format("%17.2f", other));
    }
    public void setGrandTotal(double grandTotal) {
        System.out.println("Grand: " + grandTotal);
        txt_grandTotal.setText(String.format("%17.2f", grandTotal));
    }

    public void setCurrency(String currency) {
        txt_currency.setText(currency);;
    }
    public void setDate(String date) {
        txt_date.setText(date);;
    }
    public void setVisit(String visit) {
        txt_visit.setText(visit);;
    }

    public void setPurpose(String purpose) {
        txt_purpose.setText(purpose);;
    }

    public void setSignatureFile(String signatureFile) {
        txt_signatureFile.setText(signatureFile);
    }

    public void setSignedDate(String signedDate) {
        txt_signedDate.setText(signedDate);
    }

    public double getGrandTotal() { return Double.parseDouble(txt_grandTotal.getText());}
    public double getTravelTotal() {
        return Double.parseDouble(txt_travelTotal.getText().substring(1));
    }

    public double getMileageTotal() {
        return Double.parseDouble(txt_mileageTotal.getText());
    }

    public double getPerMile() {
        return Double.parseDouble(txt_perMile.getText());
    }

    public double getSubsistence() {
        return Double.parseDouble(txt_subsistence.getText().substring(1));
    }

    public double getOther() {
        return Double.parseDouble(txt_other.getText().substring(1));
    }


    public String getCurrency() {
        return txt_currency.getText();
    }

    public String getVisit() {
        return txt_visit.getText();
    }

    public String getDate() {
        return txt_date.getText();
    }

    public String getPurpose() {
        return txt_purpose.getText();
    }

    public String getSignature() {
        return txt_signatureFile.getText();
    }

    public String getSignedDate() {
        return txt_signedDate.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        System.out.println(returnVal);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File signature_file = fc.getSelectedFile();
            displaySignature(signature_file);
        }
    }

    public void displaySignature(File signature_file) {
        txt_signatureFile.setText(signature_file.getAbsolutePath());
        try {
            BufferedImage originalImage = ImageIO.read(signature_file);
            Image newResizedImage = originalImage
                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lbl_signature.setIcon(new ImageIcon(newResizedImage));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
