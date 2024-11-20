package com.baukur.api.expenses.repository;

import com.baukur.api.expenses.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findExpenseByCategoryIdAndUserId(Long categoryId, Long userId);

    Map<String, Double> getAverageExpensesByCategory();

    Map<String, Double> getExpensesByCategory(Long userId);
}