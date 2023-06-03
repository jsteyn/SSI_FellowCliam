package com.jannetta.SSI_FellowClaim.view;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jannetta.SSI_FellowClaim.Root;
import com.jannetta.SSI_FellowClaim.controller.MakeDocument;
import com.jannetta.SSI_FellowClaim.controller.Utils;
import com.jannetta.SSI_FellowClaim.model.AllSections;
import com.jannetta.SSI_FellowClaim.model.Expense;
import com.jannetta.SSI_FellowClaim.model.Expenses;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jannetta.SSI_FellowClaim.controller.Utils.checkFile;

public class MainFrame extends JFrame implements ActionListener {

    Logger logger = Logger.getLogger(getClass().getName());
    Root root;

    private PersonalDetailsPanel personalDetailsPanel;
    private BankDetailsPanel bankDetailsPanel;
    private ExpenseSummaryPanel expenseSummaryPanel = new ExpenseSummaryPanel();
    private ExpenditurePanel expenditurePanel;
    String propertiesFilePath;
    Properties properties;

    public MainFrame(Root root) {
        super("SSI_FellowClaim");
        this.root = root;
    }

    public void init() {

        checkFile("system.properties");
        properties = Utils.readProperties();
        propertiesFilePath = properties.getProperty("defaultDir");
        if (propertiesFilePath == null || propertiesFilePath.equals("defaultDir")) {
            propertiesFilePath = System.getProperty("user.home");
            properties.setProperty("defaultDir", propertiesFilePath);
            Utils.storeProperties(properties);
        }
        propertiesFilePath = properties.getProperty("defaultDir");
        logger.log(Level.INFO, propertiesFilePath);

        personalDetailsPanel = new PersonalDetailsPanel();
        bankDetailsPanel = new BankDetailsPanel();
        expenditurePanel = new ExpenditurePanel(root);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Personal Details", personalDetailsPanel);
        tabbedPane.addTab("Bank Details", bankDetailsPanel);
        tabbedPane.addTab("List of Expenses", expenditurePanel);
        tabbedPane.addTab("Summary", expenseSummaryPanel);
        MenuBar menuBar = new MenuBar(this);
        setJMenuBar(menuBar);
        setContentPane(tabbedPane);
        pack();
        setVisible(true);
        setSize(1024, 768);
    }

    public PersonalDetailsPanel getPersonalDetailsPanel() {
        return personalDetailsPanel;
    }

    public void setPersonalDetailsPanel(PersonalDetailsPanel personalDetailsPanel) {
        this.personalDetailsPanel = personalDetailsPanel;
    }

    public BankDetailsPanel getBankDetailsPanel() {
        return bankDetailsPanel;
    }

    public void setBankDetailsPanel(BankDetailsPanel bankDetailsPanel) {
        this.bankDetailsPanel = bankDetailsPanel;
    }

    public ExpenseSummaryPanel getExpenseSummaryPanel() {
        return expenseSummaryPanel;
    }

    public void setExpenseSummaryPanel(ExpenseSummaryPanel expenseSummaryPanel) {
        this.expenseSummaryPanel = expenseSummaryPanel;
    }

    public ExpenditurePanel getExpenditurePanel() {
        return expenditurePanel;
    }

    public void setExpenditurePanel(ExpenditurePanel expenditurePanel) {
        this.expenditurePanel = expenditurePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Print")) {
            try {
                MakeDocument.print(expenseSummaryPanel.getSignature(), "data/output.pdf", root.getAllSections());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getActionCommand().equals("Open")) {
            String fileDir = (String) properties.get("defaultDir");
            final JFileChooser fc = new JFileChooser();
            try {
                File f = new File(new File(fileDir).getCanonicalPath());
                logger.log(Level.INFO, f.toString());
                fc.setCurrentDirectory(f);

                int returnVal = fc.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File readFrom = fc.getSelectedFile();
                    AllSections allSections = root.getAllSections();

                    BufferedReader bufferedReader = null;
                    try {
                        properties.setProperty("defaultDir", readFrom.getAbsolutePath());
                        Utils.storeProperties(properties);
                        bufferedReader = new BufferedReader(new FileReader(readFrom.getAbsolutePath()));
                        Gson gson = new Gson();
                        AllSections newSections = gson.fromJson(bufferedReader, AllSections.class);
                        logger.log(Level.INFO, newSections.getExpenses().get(0).getDescription());
                        allSections.setAll(newSections);
                        personalDetailsPanel.updateData(allSections.getPersonalDetails());
                        bankDetailsPanel.updateData(allSections.getBankDetails());
                        expenseSummaryPanel.updateData(allSections);//.getSection3(), allSections.getExpenses()
                        expenditurePanel.updateTable(allSections.getExpenses());
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getActionCommand().equals("Save")) {
            save();
            expenseSummaryPanel.updateData(root.getAllSections());//.getSection3(), allSections.getExpenses()

        }
    }

    private void save() {
        Expenses expenses = root.getAllSections().getExpenses();
        if (expenses.size() == 0) expenses.add(new Expense());
        double travel = expenses.getTravelTotal();
        double other = expenses.getOtherTotal();
        double subsistence = expenses.getSubsistenceTotal();
        double total = expenses.getGrandTotal();

        expenseSummaryPanel.setTravelTotal(travel);
        expenseSummaryPanel.setSubsistence(subsistence);
        expenseSummaryPanel.setOther(other);
        expenseSummaryPanel.setGrandTotal(total);
        root.getAllSections().setPersonalDetails(personalDetailsPanel.getFullName(), personalDetailsPanel.getAddress(),
                personalDetailsPanel.getNumber(), personalDetailsPanel.getBacs_email());
        root.getAllSections().setBankDetails(bankDetailsPanel.getBank(), bankDetailsPanel.getBranch(),
                bankDetailsPanel.getAccount(), bankDetailsPanel.getSort_code(), bankDetailsPanel.getIban(),
                bankDetailsPanel.getSwift());
        root.getAllSections().setExpenseSummary(expenses.getTravelTotal(), expenseSummaryPanel.getMileageTotal(),
                expenseSummaryPanel.getPerMile(), expenses.getSubsistenceTotal(), expenses.getOtherTotal(), expenseSummaryPanel.getCurrency(),
                expenseSummaryPanel.getVisit(), expenseSummaryPanel.getDate(), expenseSummaryPanel.getPurpose(), expenseSummaryPanel.getSignature(),
                expenseSummaryPanel.getSignedDate());

        String fileDir = (String) properties.get("defaultDir");
        final JFileChooser fc = new JFileChooser();
        try {
            File f = new File(new File(fileDir).getCanonicalPath());
            logger.log(Level.INFO, f.toString());
            fc.setCurrentDirectory(f);
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File saveTo = fc.getSelectedFile();
                try {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    FileWriter writer = new FileWriter(saveTo);
                    gson.toJson(root.getAllSections(), writer);
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Data saved.");

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
