package com.baukur.api.categories.repository;

import com.baukur.api.categories.domain.DefaultCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultCategoryRepository extends JpaRepository<DefaultCategory, Long> {
}
