package com.baukur.api.categories.repository;

import com.baukur.api.categories.domain.DefaultCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultCategoriesRepository extends JpaRepository<DefaultCategories, Long> {
}
