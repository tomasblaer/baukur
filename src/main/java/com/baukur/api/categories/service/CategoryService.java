package com.baukur.api.categories.service;

import com.baukur.api.categories.domain.Category;
import com.baukur.api.categories.repository.CategoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService {

    @Autowired

    private CategoriesRepository categoriesRepository;

    public Category createCategory(Category category) {
        return categoriesRepository.save(category);
    }

    public void deleteCategory() {
        log.info("Deleting category");
    }

}
