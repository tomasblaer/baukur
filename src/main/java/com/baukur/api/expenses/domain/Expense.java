package com.baukur.api.expenses.domain;

import com.baukur.api.categories.domain.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String comment;
    private Long amount;
    @JsonFormat(pattern = "dd/MM/YYYY")
    private Date date;
    private Long categoryId;
    private Long userId;

}
