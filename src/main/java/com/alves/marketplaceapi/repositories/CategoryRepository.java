package com.alves.marketplaceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alves.marketplaceapi.domain.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  // 
  
}