package com.baukur.api.expenses.domain;

import com.baukur.api.categories.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;

@Data
@AllArgsConstructor
public class ExpenseAmountGraphResponse {
    private String date;

    private Category category;

    private Long amount;
}
