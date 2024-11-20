package com.baukur.api.categories.service;

import com.baukur.api.categories.domain.Category;
import com.baukur.api.categories.domain.DefaultCategories;
import com.baukur.api.categories.repository.CategoriesRepository;
import com.baukur.api.categories.repository.DefaultCategoriesRepository;
import com.baukur.api.user.domain.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CategoryService {

    @Autowired

    private CategoriesRepository categoriesRepository;

    @Autowired
    private DefaultCategoriesRepository defaultCategoriesRepository;

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
        } else {
            category.setId(existingCategory.get().getId());
            category.setUserId(user.getId());
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

    public Category editCategoryName(Long id, String name, UserDetailsImpl user) {
        Optional<Category> existingCategory = categoriesRepository.findById(id);
        if (existingCategory.isEmpty()) {
            throw new RuntimeException("Category not found");
        } else if (!Objects.equals(existingCategory.get().getUserId(), user.getId())) {
            throw new RuntimeException("Category does not belong to user");
        } else {
            existingCategory.get().setName(name);
        }

        return categoriesRepository.save(existingCategory.get());
    }

    public Category editCategoryDescription(Long id, String description, UserDetailsImpl user) {
        Optional<Category> existingCategory = categoriesRepository.findById(id);
        if (existingCategory.isEmpty()) {
            throw new RuntimeException("Category not found");
        } else if (!Objects.equals(existingCategory.get().getUserId(), user.getId())) {
            throw new RuntimeException("Category does not belong to user");
        } else {
            existingCategory.get().setDescription(description);
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

    public HashMap<Long, String> getCategoryNameMap(UserDetailsImpl user) {
        List<Category> category = categoriesRepository.findCategoriesByUserIdAndHiddenIsFalse(user.getId());
        HashMap<Long, String> categoryMap = new HashMap<>();
        if (category.isEmpty()) {
            throw new RuntimeException("Category not found");
        }
        category.forEach(c -> categoryMap.put(c.getId(), c.getName()));
        return categoryMap;
    }

    public List<Category> getCategoriesByUserId(Long userId) {
        return categoriesRepository.findCategoriesByUserIdAndHiddenIsFalse(userId);
    }

    public List<Category> getHiddenCategoriesByUserId(Long userId) {
        return categoriesRepository.findCategoriesByUserIdAndHiddenIsTrue(userId);
    }

    public List<DefaultCategories> getDefaultCategories() {
        return defaultCategoriesRepository.findAll();
    }

    public List<Category> createDefaultCategories(Long userId, List<Long> ids) {
        List<DefaultCategories> defaultCategories = defaultCategoriesRepository.findAllById(ids);
        List<Category> categories = new ArrayList<>();
        for (DefaultCategories defaultCategory : defaultCategories) {
            Category category = new Category();
            category.setName(defaultCategory.getName());
            category.setUserId(userId);
            category.setDefaultCategoryId(defaultCategory.getId());
            categories.add(categoriesRepository.save(category));
        }
        return categories;
    }

}
