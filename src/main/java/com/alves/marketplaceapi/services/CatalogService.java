package com.alves.marketplaceapi.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alves.marketplaceapi.domain.catalog.Catalog;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CatalogService {

  private final CategoryService categoryService;

  @Cacheable("catalogs")
  public Catalog getCatalog() {
    return new Catalog(categoryService.getAllCategory());
  }
  
}