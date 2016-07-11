package com.shop.repository;

import com.shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA interface with all CRUD operations for category entity
 * Auto implemented in runtime by Spring Data
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
