package com.baukur.api.categories.domain;

import com.baukur.api.expenses.domain.Expense;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean hidden;
    @JsonIgnore
    private Long iconId;
    @JsonIgnore
    private Long userId;

    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.REMOVE)
    private List<Expense> expenses;

}
