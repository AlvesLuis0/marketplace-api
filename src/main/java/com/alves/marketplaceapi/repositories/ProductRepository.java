package com.alves.marketplaceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.marketplaceapi.domain.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  // 
  
}