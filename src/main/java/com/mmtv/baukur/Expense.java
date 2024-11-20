package com.mmtv.baukur;

public class Expense {
    int expense_id;
    String expense_name;
    String expense_description;
    int expense_amount;

    public Expense() {}

    public Expense(int expense_id, String expense_description, String expense_name, int expense_amount) {
        super();
        this.expense_id = expense_id;
        this.expense_description = expense_description;
        this.expense_name = expense_name;
        this.expense_amount = expense_amount;
    }

    public int getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
    }

    public String getExpense_name() {
        return expense_name;
    }

    public void setExpense_name(String expense_name) {
        this.expense_name = expense_name;
    }

    public String getExpense_description() {
        return expense_description;
    }

    public void setExpense_description(String expense_description) {
        this.expense_description = expense_description;
    }

    public int getExpense_amount() {
        return expense_amount;
    }

    public void setExpense_amount(int expense_amount) {
        this.expense_amount = expense_amount;
    }
}
