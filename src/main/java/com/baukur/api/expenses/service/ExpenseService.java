package com.baukur.api.expenses.service;

import com.baukur.api.expenses.domain.Expense;
import com.baukur.api.expenses.repository.ExpenseRepository;
import com.baukur.api.user.domain.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense getExpenseById(Long id, UserDetailsImpl user) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isEmpty()) {
            throw new RuntimeException("Expense not found");
        } else if (!expense.get().getUserId().equals(user.getId())) {
            throw new RuntimeException("Expense does not belong to user");
        }
        return expense.get();
    }

    public List<Expense> getExpensesByCategoryId(Long categoryId, UserDetailsImpl user) {
        return expenseRepository.findExpenseByCategoryIdAndUserId(categoryId, user.getId());
    }


    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}