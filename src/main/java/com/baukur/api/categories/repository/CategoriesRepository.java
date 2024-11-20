package com.baukur.api.categories.repository;


import com.baukur.api.categories.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {

    Category deleteCategoryById(Long id);

    List<Category> findCategoriesByUserIdAndHiddenIsFalse(Long userId);

    List<Category> findCategoriesByUserIdAndHiddenIsTrue(Long userId);

}
