package com.baukur.api.categories.controller;

import com.baukur.api.categories.domain.Category;
import com.baukur.api.categories.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoriesController {

    @Autowired
    private final CategoryService categoryService;

    @GetMapping
    public Category getCategories() {
        return new Category(1L, "Category 1", "This is category 1");
    }

    @DeleteMapping
    public void deleteCategory() {
        categoryService.deleteCategory();
    }
}
