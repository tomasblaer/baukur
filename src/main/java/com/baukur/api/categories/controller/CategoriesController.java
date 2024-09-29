package com.baukur.api.categories.controller;

import com.baukur.api.categories.domain.Category;
import com.baukur.api.categories.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoriesController {

    @Autowired
    private final CategoryService categoryService;

    @GetMapping
    public Category getCategories() {
        return new Category("Category 1", "This is category 1", 50000);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @DeleteMapping
    public void deleteCategory() {
        categoryService.deleteCategory();
    }
}
