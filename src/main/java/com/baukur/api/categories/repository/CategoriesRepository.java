package com.baukur.api.categories.repository;


import com.baukur.api.categories.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {

    Category deleteCategoryById(Long id);

}
