package com.baukur.api.expenses.service;

import com.baukur.api.categories.domain.CategoryComparison;
import com.baukur.api.expenses.domain.Expense;
import com.baukur.api.expenses.repository.ExpenseRepository;
import com.baukur.api.user.domain.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public void deleteExpense(Long id, UserDetailsImpl user) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isEmpty()) {
            throw new RuntimeException("Expense not found");
        } else if (!expense.get().getUserId().equals(user.getId())) {
            throw new RuntimeException("Expense does not belong to user");
        }
        expenseRepository.deleteById(id);
    }

    public List<Expense> getExpensesByCategoryId(Long categoryId, UserDetailsImpl user) {
        return expenseRepository.findExpenseByCategoryIdAndUserId(categoryId, user.getId());
    }


    public Expense createExpense(Expense expense, UserDetailsImpl user) {
        expense.setUserId(user.getId());
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<CategoryComparison> compareExpenses(Long userId) {
        // Fetch user's total expenses by category
        Map<String, Double> userExpenses = expenseRepository.getExpensesByCategory(userId);

        // Fetch average expenses by category for all users
        Map<String, Double> averageExpenses = expenseRepository.getAverageExpensesByCategory();

        // Construct the comparison data
        List<CategoryComparison> comparisons = new ArrayList<>();
        for (String category : userExpenses.keySet()) {
            double userSpending = userExpenses.getOrDefault(category, 0.0);
            double avgSpending = averageExpenses.getOrDefault(category, 0.0);
            comparisons.add(new CategoryComparison(category, userSpending, avgSpending));
        }

        return comparisons;
    }
}