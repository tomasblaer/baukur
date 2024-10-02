package com.baukur.api.categories.service;

import com.baukur.api.categories.domain.Category;
import com.baukur.api.categories.repository.CategoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Category deleteCategory(Long id) {
        return categoriesRepository.deleteCategoryById(id);
    }

}
