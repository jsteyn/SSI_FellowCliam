package com.jannetta.SSI_FellowClaim.view;

import com.jannetta.SSI_FellowClaim.Root;
import com.jannetta.SSI_FellowClaim.model.ExpenditureTableModel;
import com.jannetta.SSI_FellowClaim.model.Expenses;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpenditurePanel extends JPanel {
    JTable tbl_expenditure;
    JScrollPane scrollPane;

    MigLayout migLayout;
    ExpenditureTableModel expenditureTableModel;
    ExpenseSummaryPanel expenseSummaryPanel;
    Root root;

    ExpenditurePanel(Root r) {
        super();
        root = r;
        Expenses expenses = root.getAllSections().getExpenses();
        expenseSummaryPanel = r.getMainFrame().getExpenseSummaryPanel();
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tbl_expenditure.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(getParent(), "You have to select a row before a row can be deleted.");
                } else {
                    int response = JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to delete row " + tbl_expenditure.getSelectedRow());
                    if (response == 0) {
                        expenditureTableModel.removeRow(tbl_expenditure.getSelectedRow());
                        tbl_expenditure.repaint();
                    }
                }
            }
        });
        popupMenu.add(deleteItem);
        expenditureTableModel = new ExpenditureTableModel(root);
        tbl_expenditure = new JTable(expenditureTableModel);
        tbl_expenditure.setComponentPopupMenu(popupMenu);
        tbl_expenditure.setComponentPopupMenu(popupMenu);
        scrollPane = new JScrollPane(tbl_expenditure);
        migLayout = new MigLayout("fillx");
        setLayout(migLayout);
        JPanel pnl_header = new JPanel();
        pnl_header.add(new JLabel("List of expenditurePanel - Non staff/student expense claim"));
        setUpExpenseTypeColumn(tbl_expenditure, tbl_expenditure.getColumnModel().getColumn(1));
        tbl_expenditure.setFillsViewportHeight(true);
        add(pnl_header, "span, wrap");
        add(scrollPane, "span, grow");
    }

    public void updateTable(Expenses expenses) {
        expenditureTableModel.updateData(expenses, true);
        expenditureTableModel.fireTableDataChanged();
        expenseSummaryPanel.updateData(root.getAllSections());
        repaint();
    }


    public void setUpExpenseTypeColumn(JTable table,
                                       TableColumn expenseTypeColumn) {
        //Set up the editor for the sport cells.
        JComboBox<String> comboBox = new JComboBox();
        comboBox.addItem("UNSET");
        comboBox.addItem("Travel");
        comboBox.addItem("Subsistence");
        comboBox.addItem("Other");

        expenseTypeColumn.setCellEditor(new DefaultCellEditor(comboBox));

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        expenseTypeColumn.setCellRenderer(renderer);
    }

}
