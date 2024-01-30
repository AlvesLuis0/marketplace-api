package com.alves.marketplaceapi.services;

import org.springframework.stereotype.Service;

import com.alves.marketplaceapi.domain.catalog.Catalog;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CatalogService {

  private final CategoryService categoryService;

  public Catalog getCatalog() {
    return new Catalog(categoryService.getAllCategory());
  }
  
}