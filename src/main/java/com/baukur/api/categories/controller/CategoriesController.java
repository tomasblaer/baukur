package com.baukur.api.categories.controller;

import com.baukur.api.categories.domain.Category;
import com.baukur.api.categories.domain.CreateDefaultCategoriesPayload;
import com.baukur.api.categories.domain.DefaultCategories;
import com.baukur.api.categories.domain.DeleteManyCategoriesPayload;
import com.baukur.api.categories.service.CategoryService;
import com.baukur.api.user.domain.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Slf4j
public class CategoriesController {

    @Autowired
    private final CategoryService categoryService;

    // Get Categories
    @GetMapping
    public ResponseEntity<?> getCategoriesForUser(@AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return new ResponseEntity<>(categoryService.getCategoriesByUserId(user.getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get categories", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/hide")
    public ResponseEntity<?> hideCategory(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            Category category = categoryService.hideCategory(id, user);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to hide category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hidden")
    public ResponseEntity<?> getHiddenCategoriesForUser(@AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return new ResponseEntity<>(categoryService.getHiddenCategoriesByUserId(user.getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get hidden categories", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/unhide")
    public ResponseEntity<?> unhideCategory(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            Category category = categoryService.unhideCategory(id, user);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to unhide category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add Category
    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody @Valid Category category, @AuthenticationPrincipal UserDetailsImpl user) {
        Category createdCategory = categoryService.createCategory(category, user);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PostMapping("/default")
    public ResponseEntity<?> createDefaultCategories(@RequestBody CreateDefaultCategoriesPayload payload) {
        try {
            List<Category> defaultCategories = categoryService.createDefaultCategories(payload.getUserId(), payload.getIds());
            return new ResponseEntity<>(defaultCategories, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create default categories", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/default")
    public ResponseEntity<?> getDefaultCategories() {
        try {
            List<DefaultCategories> defaultCategories = categoryService.getDefaultCategories();
            return new ResponseEntity<>(defaultCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get default categories", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/editName")
    public ResponseEntity<?> editCategoryName(@RequestParam Long id, @RequestParam String name, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            Category category = categoryService.editCategoryName(id, name, user);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to edit category name", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/editDescription")
    public ResponseEntity<?> editCategoryDescription(@RequestParam Long id, @RequestParam String description, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            Category category = categoryService.editCategoryDescription(id, description, user);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to edit category description", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Edit Category
    @PatchMapping
    public ResponseEntity<?> editCategory(@RequestBody Category category, @AuthenticationPrincipal UserDetailsImpl user) {
        Category editedCategory =  categoryService.updateCategory(category, user);
        return new ResponseEntity<>(editedCategory, HttpStatus.OK);
    }

    // Delete Category
    @DeleteMapping
    public ResponseEntity<?> deleteCategory(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            categoryService.deleteCategory(id, user);
            return new ResponseEntity<>("Category deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteMany")
    public ResponseEntity<?> deleteManyCategories(@RequestBody DeleteManyCategoriesPayload payload, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            categoryService.deleteManyCategories(payload.getIds(), user);
            return new ResponseEntity<>("Categories deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete categories", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
