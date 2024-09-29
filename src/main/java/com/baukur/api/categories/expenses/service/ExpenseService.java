package com.baukur.api.categories.expenses.service;

import com.baukur.api.categories.expenses.domain.Expense;
import com.baukur.api.categories.expenses.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}
