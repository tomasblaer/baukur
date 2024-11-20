package com.baukur.api.expenses.domain;

import java.util.Date;

public class GraphExpense extends Expense {

        private String categoryName;

        public GraphExpense(Long id, String name, String comment, Long amount, Date date, Long categoryId, Long userId, String categoryName) {
            super(id, name, comment, amount, date, categoryId, userId);
            this.categoryName = categoryName;
        }

        public GraphExpense(Expense expense, String categoryName) {
            super(expense.getId(), expense.getName(), expense.getComment(), expense.getAmount(), expense.getDate(), expense.getCategoryId(), expense.getUserId());
            this.categoryName = categoryName;
        }

        public String getCategoryName() {
            return categoryName;
        }

}
