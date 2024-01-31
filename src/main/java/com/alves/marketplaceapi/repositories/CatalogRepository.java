package com.alves.marketplaceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.marketplaceapi.domain.catalog.Catalog;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, String> {

  // 
  
}