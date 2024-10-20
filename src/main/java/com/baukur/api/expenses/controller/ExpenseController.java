package com.baukur.api.expenses.controller;

import com.baukur.api.expenses.domain.Expense;
import com.baukur.api.expenses.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Get expense
    @GetMapping
    public Expense getExpenses() {
        return new Expense();
    }

    // Add expense
    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        Expense createdExpense = expenseService.createExpense(expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    // Edit expense
    @PatchMapping
    public ResponseEntity<Expense> editExpense(@RequestBody Expense expense) {
        Expense editedExpense = expenseService.updateExpense(expense);
        return new ResponseEntity<>(editedExpense, HttpStatus.OK);
    }


}
