package com.baukur.api.categories.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Long id;
    private String name;
    private String description;

    public void addExpense() {
        "select * from expenses where date between :from and :to;"
    }

}
