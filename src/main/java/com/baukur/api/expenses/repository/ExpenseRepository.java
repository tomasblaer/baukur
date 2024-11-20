package com.baukur.api.expenses.repository;

import com.baukur.api.expenses.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findExpenseByCategoryIdAndUserId(Long categoryId, Long userId);

    List<Expense> findExpensesByUserIdAndDateIsBetweenOrderByDateAsc(Long userId, Date date, Date date2);

}