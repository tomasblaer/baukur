package com.baukur.api.categories.domain;

import com.baukur.api.expenses.domain.Expense;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name is required")
    private String name;
    private String description;
    private boolean hidden;
    private Long iconId;
    private Long userId;
    private Long defaultCategoryId;

    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.REMOVE)
    private List<Expense> expenses;

}
