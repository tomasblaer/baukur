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

    // Get Categories
    @GetMapping
    public ResponseEntity<?> getCategories() {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    // Add Category
    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // Edit Category
    @PatchMapping
    public ResponseEntity<?> editCategory(@RequestBody Category category) {
        Category editedCategory =  categoryService.updateCategory(category);
        return new ResponseEntity<>(editedCategory, HttpStatus.OK);
    }

    // Delete Category
    @DeleteMapping
    public ResponseEntity<?> deleteCategory(@RequestParam Long id) {
        Category deleted = categoryService.deleteCategory(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
