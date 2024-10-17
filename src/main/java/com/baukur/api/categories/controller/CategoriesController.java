package com.baukur.api.categories.controller;

import com.baukur.api.categories.domain.Category;
import com.baukur.api.categories.service.CategoryService;
import com.baukur.api.user.domain.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Slf4j
public class CategoriesController {

    @Autowired
    private final CategoryService categoryService;

    // Get Categories
    @GetMapping
    public ResponseEntity<?> getCategories(@AuthenticationPrincipal UserDetailsImpl user) {
//        return new ResponseEntity<>(categoryService.getCategoriesByUserId(user.getId()), HttpStatus.OK);
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
