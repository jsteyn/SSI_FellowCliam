package com.jannetta.SSI_FellowClaim.model;

import javax.swing.table.AbstractTableModel;
import java.util.Collections;

public class ExpenditureTableModel extends AbstractTableModel {
    Expenses arr_expenses;
    double travelTotal = 0;
    double subsistenceTotal = 0;
    double grandTotal = 0;

    public ExpenditureTableModel(Expenses expenses) {
        super();
        arr_expenses = expenses;
    }

    public void setExpenses(Expenses expenses) {
        arr_expenses = expenses;
    }

    public Expenses getExpenses() {
        return arr_expenses;
    }

    /**
     * Recalculate the totals in the ExpenseSummaryPanel and update the table
     * @param expenses
     */
    public void updateData(Expenses expenses) {
        arr_expenses = expenses;
        expenses.updateTotals();
        fireTableDataChanged();

    }

    /**
     * Get the number of rows from the data structure that holds the table informtion
     * @return the number of rows
     */
    @Override
    public int getRowCount() {

        return arr_expenses.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    /**
     * Get the value of the cell specified with the row and column
     * @param rowIndex        the row whose value is to be queried
     * @param columnIndex     the column whose value is to be queried
     * @return the value of the cell being queried
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < arr_expenses.size()) {
            if (arr_expenses.size() != 0) {
                Expense expense = arr_expenses.get(rowIndex);
                return switch (columnIndex) {
                    case 0 -> expense.getDescription();
                    case 1 -> expense.getExpenseType();
                    case 2 -> expense.getAmount_currency();
                    case 3 -> expense.getAmount_gbp();
                    case 4 -> expense.getReceipt_number();
                    default -> null;
                };
            }
        }
        return null;
    }

    /**
     * Set the value of the cell specified by the row and column
     * @param value   value to assign to cell
     * @param row   row of cell
     * @param col  column of cell
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        int startRow = arr_expenses.size();
        for (int r = startRow; r <= row; r++)
            arr_expenses.add(new Expense());
        if (startRow <= row)
            fireTableRowsInserted(startRow, row);

        Expense expense = arr_expenses.get(row);
        switch (col) {
            case 0 -> expense.setDescription((String) value);
            case 1 -> expense.setExpenseType((String) value);
            case 2 -> expense.setAmount_currency(Double.parseDouble((String) value));
            case 3 -> expense.setAmount_gbp(Double.parseDouble((String) value));
            case 4 -> expense.setReceipt_number(Integer.parseInt((String) value));
        }
        Collections.sort(arr_expenses);
        fireTableDataChanged();
    }

    /**
     * Names of columns
     * @param column  the column being queried
     * @return the name of the column being queried
     */
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Description of ExpenditurePanel";
            case 1:
                return "Expense Type";
            case 2:
                return "Amount in currency";
            case 3:
                return "Amount in GBP";
            case 4:
                return "Receipt number";

        }
        return null;
    }

    /**
     * Remove element from the array
     * @param rowToRemove index of array element to remove
     */
    public void removeRow(int rowToRemove) {
        arr_expenses.remove(rowToRemove);
    }

    /**
     * Keep cell returns separate for the time being. Could be merged in the end.
     * @param row  the row being queried
     * @param col the column being queried
     * @return true or false depending on whether cell can be edited
     */
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 0, 1, 2, 3, 4: return true;
            default: return false;
        }
    }

}
