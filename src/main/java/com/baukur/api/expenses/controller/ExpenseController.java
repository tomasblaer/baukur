package com.baukur.api.expenses.controller;

import com.baukur.api.expenses.domain.Expense;
import com.baukur.api.expenses.domain.ExpenseAmountGraphResponse;
import com.baukur.api.expenses.domain.GraphExpense;
import com.baukur.api.expenses.service.ExpenseService;
import com.baukur.api.user.domain.UserDetailsImpl;
import org.hibernate.query.QueryParameter;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Get expense
    @GetMapping
    public ResponseEntity<?> getExpense(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            Expense expense = expenseService.getExpenseById(id, user);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get expense");
        }
    }

    @GetMapping("/graph")
    public ResponseEntity<?> getExpensesForGraph(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date to)
    {
        try {
            List<GraphExpense> expenses = expenseService.getExpensesForGraph(user, from, to);

            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get expenses");
        }
    }

    @GetMapping("/inCategory")
    public ResponseEntity<?> getExpensesInCategory(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            List<Expense> expenses = expenseService.getExpensesByCategoryId(id, user);
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get expense");
        }
    }

    // Add expense
    @PostMapping
    public ResponseEntity<?> addExpense(@RequestBody Expense expense, @AuthenticationPrincipal UserDetailsImpl user) {
        Expense createdExpense = expenseService.createExpense(expense, user);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteExpense(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            expenseService.deleteExpense(id, user);
            return new ResponseEntity<>("Expense deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete expense");
        }
    }

    // Edit expense
    @PatchMapping
    public ResponseEntity<?> editExpense(@RequestBody Expense expense, @AuthenticationPrincipal UserDetailsImpl user) {
        Expense editedExpense = expenseService.updateExpense(expense, user);
        return new ResponseEntity<>(editedExpense, HttpStatus.OK);
    }

}
