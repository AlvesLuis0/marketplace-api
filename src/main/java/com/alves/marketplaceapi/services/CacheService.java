package com.alves.marketplaceapi.services;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.alves.marketplaceapi.domain.catalog.Catalog;
import com.alves.marketplaceapi.domain.category.Category;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CacheService {

  private final CacheManager cacheManager;

  public void deleteAllProductsByCategory(Category category) {
    var productsCache = cacheManager.getCache("products");
    category.getProducts()
      .stream()
      .forEach(product -> productsCache.evict(product.getId()));
  }

  public void deleteAllCategoriesByCatalog(Catalog catalog) {
    var categoriesCache = cacheManager.getCache("categories");
    catalog.getCategories()
      .stream()
      .forEach(category -> {
        deleteAllProductsByCategory(category);
        categoriesCache.evict(category.getId());
      });
  }

  public void deleteCatalogByOwner(String owner) {
    var catalogsCache = cacheManager.getCache("catalogs");
    catalogsCache.evict(owner);
  }
  
}