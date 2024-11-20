package com.baukur.api.categories.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class DefaultCategories {
    @Id
    private Long id;
    private String name;
    private String description;
    private Long iconId;
}
