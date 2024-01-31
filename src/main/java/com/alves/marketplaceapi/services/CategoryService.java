package com.alves.marketplaceapi.services;

import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
  private final CacheManager cacheManager;

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

  @Caching(
    put=@CachePut(key="#result.id"),
    evict=@CacheEvict(value="catalogs", allEntries=true)
  )
  public Category createCategory(CategoryRequest categoryData) {
    var category = convert(categoryData);
    catalogService.getCatalog(category.getOwner());
    category.setProducts(List.of());
    return categoryRepository.save(category);
  }

  @Caching(
    put=@CachePut(key="#id"),
    evict=@CacheEvict(value="catalogs", allEntries=true)
  )
  public Category updateCategory(Long id, CategoryRequest categoryData) {
    // cannot change the catalog ID
    var category = getCategory(id);
    if(categoryData.name() != null)
      category.setName(categoryData.name());
    if(categoryData.description() != null)
      category.setDescription(categoryData.description());
    return categoryRepository.save(category);
  }

  @Caching(evict={
    @CacheEvict,
    @CacheEvict(value="catalogs", allEntries=true)
  })
  public void deleteCategory(Long id) {
    var category = getCategory(id);
    // removing products in the cache
    var productCache = cacheManager.getCache("products");
    category.getProducts().stream()
      .forEach(product -> productCache.evict(product.getId()));
    categoryRepository.delete(category);
  }
  
}