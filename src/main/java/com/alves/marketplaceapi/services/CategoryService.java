package com.alves.marketplaceapi.services;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alves.marketplaceapi.domain.category.Category;
import com.alves.marketplaceapi.domain.category.CategoryRequest;
import com.alves.marketplaceapi.domain.category.exceptions.CategoryNotFoundException;
import com.alves.marketplaceapi.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CacheConfig(cacheNames="categories")
@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CatalogService catalogService;
  private final CacheService cacheService;

  private Category convert(CategoryRequest categoryData) {
    var category = new Category();
    category.setOwner(categoryData.owner());
    category.setName(categoryData.name());
    category.setDescription(categoryData.description());
    return category;
  }

  public List<Category> getAllCategory() {
    return categoryRepository.findAll();
  }

  @Cacheable
  public Category getCategory(Long id) {
    var category = categoryRepository.findById(id)
      .orElseThrow(() -> new CategoryNotFoundException("ID", id));
    return category;
  }

  @CachePut(key="#result.id")
  public Category createCategory(CategoryRequest categoryData) {
    var category = convert(categoryData);
    category.setProducts(List.of());
    cacheService.deleteCatalogByOwner(category.getOwner());
    return categoryRepository.save(category);
  }

  @CachePut(key="#id")
  public Category updateCategory(Long id, CategoryRequest categoryData) {
    // cannot change the catalog ID
    var category = getCategory(id);
    catalogService.getCatalog(category.getOwner());
    if(categoryData.name() != null)
      category.setName(categoryData.name());
    if(categoryData.description() != null)
      category.setDescription(categoryData.description());
    cacheService.deleteCatalogByOwner(category.getOwner());
    return categoryRepository.save(category);
  }

  @CacheEvict
  public void deleteCategory(Long id) {
    var category = getCategory(id);
    cacheService.deleteCatalogByOwner(category.getOwner());
    categoryRepository.delete(category);
  }
  
}