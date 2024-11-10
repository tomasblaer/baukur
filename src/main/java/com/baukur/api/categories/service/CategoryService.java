package com.baukur.api.categories.service;

import com.baukur.api.categories.domain.Category;
import com.baukur.api.categories.repository.CategoriesRepository;
import com.baukur.api.user.domain.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Category createCategory(Category category, UserDetailsImpl user) {
        category.setUserId(user.getId());
        return categoriesRepository.save(category);
    }

    public Category updateCategory(Category category, UserDetailsImpl user) {
        Optional<Category> existingCategory = categoriesRepository.findById(category.getId());
        if (existingCategory.isEmpty()) {
            throw new RuntimeException("Category not found");
        } else if (!Objects.equals(existingCategory.get().getUserId(), user.getId())) {
            throw new RuntimeException("Category does not belong to user");
        } else if (!Objects.equals(category.getUserId(), existingCategory.get().getUserId())) {
            throw new RuntimeException("Category user id does not match");
        } else {
            existingCategory.get().setName(category.getName());
        }

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

    public void deleteCategory(Long id, UserDetailsImpl user) {
        Optional<Category> existingCategory = categoriesRepository.findById(id);
        if (existingCategory.isEmpty()) {
            throw new RuntimeException("Category not found");
        } else if (!Objects.equals(existingCategory.get().getUserId(), user.getId())) {
            throw new RuntimeException("Category does not belong to user");
        }
        categoriesRepository.deleteById(id);
    }

    public void deleteManyCategories(List<Long> ids, UserDetailsImpl user) {
        List<Long> notFoundIds = new ArrayList<>();
        for (Long id : ids) {
            Optional<Category> existingCategory = categoriesRepository.findById(id);
            if (existingCategory.isEmpty()) {
                notFoundIds.add(id);
            } else if (!Objects.equals(existingCategory.get().getUserId(), user.getId())) {
                throw new RuntimeException("Category does not belong to user");
            }
        }
        if (!notFoundIds.isEmpty()) {
            throw new RuntimeException("Categories not found: " + notFoundIds);
        }
        categoriesRepository.deleteAllById(ids);
    }

    public List<Category> getCategoriesByUserId(Long userId) {
        return categoriesRepository.findCategoriesByUserId(userId);
    }

    public List<Category> getHiddenCategoriesByUserId(Long userId) {
        return categoriesRepository.findCategoriesByUserIdAndHiddenIsTrue(userId);
    }

}
