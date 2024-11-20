package com.baukur.api.expenses.service;

import com.baukur.api.categories.domain.Category;
import com.baukur.api.categories.service.CategoryService;
import com.baukur.api.expenses.domain.Expense;
import com.baukur.api.expenses.domain.ExpenseAmountGraphResponse;
import com.baukur.api.expenses.domain.GraphExpense;
import com.baukur.api.expenses.repository.ExpenseRepository;
import com.baukur.api.user.domain.UserDetailsImpl;
import org.hibernate.graph.Graph;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryService categoryService;

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

    public List<GraphExpense> getExpensesForGraph(UserDetailsImpl user, Date from, Date to) {
        List <Expense> expenses =  expenseRepository.findExpensesByUserIdAndDateIsBetweenOrderByDateAsc(user.getId(), from, to);
        HashMap<Long, String> categoryMap = categoryService.getCategoryNameMap(user);
        List <GraphExpense> graphExpenses = new ArrayList <>();
        expenses.forEach(expense -> {
            graphExpenses.add(new GraphExpense(expense, categoryMap.get(expense.getCategoryId())));
        });
        return graphExpenses;
    }

    public Expense updateExpense(Expense expense, UserDetailsImpl user) {
            Optional<Expense> existingExpense = expenseRepository.findById(expense.getId());
        if (existingExpense.isEmpty()) {
            throw new RuntimeException("Expense not found");
        } else if (!Objects.equals(existingExpense.get().getUserId(), user.getId())) {
            throw new RuntimeException("Expense does not belong to user");
        } else {
            expense.setId(existingExpense.get().getId());
            expense.setUserId(user.getId());
        }

        return expenseRepository.save(expense);
    }
}