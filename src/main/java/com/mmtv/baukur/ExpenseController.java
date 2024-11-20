package com.mmtv.baukur;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ExpenseController {

    static Expense expense = new Expense(1, "Test", "Expense 1", 200);

    @GetMapping("/expense")
    public static Expense getExpense() {
        return expense;
    }

    @PutMapping("/expense")
    public Expense createExpense(@RequestBody Expense newExpense) {
        expense = newExpense;
        return expense;
    }
}
