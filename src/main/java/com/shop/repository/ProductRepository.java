package com.shop.repository;

import com.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA interface with all CRUD operations for product entity
 * Auto implemented in runtime by Spring Data
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

}