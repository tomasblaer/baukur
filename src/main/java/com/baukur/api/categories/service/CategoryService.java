package com.baukur.api.categories.service;

import com.baukur.api.categories.domain.Category;
import com.baukur.api.categories.repository.CategoriesRepository;
import com.baukur.api.user.domain.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {

    @Autowired

    private CategoriesRepository categoriesRepository;

    public List<Category> getCategories () {
        return categoriesRepository.findAll();
    }

    public Category createCategory(Category category) {
        return categoriesRepository.save(category);
    }

    public Category updateCategory(Category category) {
        return categoriesRepository.save(category);
    }


    public Category hideCategory(Long id, UserDetailsImpl user) {
        Optional<Category> existingCategory = categoriesRepository.findById(id);
        if (existingCategory.isEmpty()) {
            throw new RuntimeException("Category not found");
        } else if (!Objects.equals(existingCategory.get().getUserId(), user.getId())) {
            throw new RuntimeException("Category does not belong to user");
        } else {
            existingCategory.get().setHidden(true);
        }

        return categoriesRepository.save(existingCategory.get());
    }

    public Category unhideCategory(Long id, UserDetailsImpl user) {
        Optional<Category> existingCategory = categoriesRepository.findById(id);
        if (existingCategory.isEmpty()) {
            throw new RuntimeException("Category not found");
        } else if (!Objects.equals(existingCategory.get().getUserId(), user.getId())) {
            throw new RuntimeException("Category does not belong to user");
        } else {
            existingCategory.get().setHidden(false);
        }

        return categoriesRepository.save(existingCategory.get());
    }

    public Category deleteCategory(Long id) {
        return categoriesRepository.deleteCategoryById(id);
    }

    public List<Category> getCategoriesByUserId(Long userId) {
        return categoriesRepository.findCategoriesByUserId(userId);
    }

}
