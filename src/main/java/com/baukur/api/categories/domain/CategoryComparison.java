package com.baukur.api.categories.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryComparison {
    private String category;
    private double userSpending;
    private double averageSpending;
}
