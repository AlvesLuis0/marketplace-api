package com.alves.marketplaceapi.services;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.alves.marketplaceapi.domain.product.Product;
import com.alves.marketplaceapi.domain.product.ProductRequest;
import com.alves.marketplaceapi.domain.product.exceptions.ProductNotFoundException;
import com.alves.marketplaceapi.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CacheConfig(cacheNames="products")
@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryService categoryService;
  private final CacheService cacheService;

  private Product convert(ProductRequest productData) {
    var product = new Product();
    product.setCategoryId(productData.categoryId());
    product.setName(productData.name());
    product.setDescription(productData.description());
    product.setPrice(productData.price());
    return product;
  }

  @Cacheable
  public Product getProduct(Long id) {
    var product = productRepository.findById(id)
      .orElseThrow(() -> new ProductNotFoundException("ID", id));
    return product;
  }

  @Caching(
    put=@CachePut(key="#result.id"),
    evict=@CacheEvict(value="categories", key="#result.categoryId")
  )
  public Product createProduct(ProductRequest productData) {
    var product = convert(productData);
    categoryService.getCategory(product.getCategoryId());
    cacheService.deleteCatalogByOwner(categoryService.getCategory(product.getCategoryId()).getOwner());
    return productRepository.save(product);
  }

  @Caching(
    put=@CachePut(key="#id"),
    evict=@CacheEvict(value="categories", key="#result.categoryId")
  )
  public Product updateProduct(Long id, ProductRequest productData) {
    var product = getProduct(id);
    // cannot change the category ID
    if(productData.name() != null)
      product.setName(productData.name());
    if(productData.description() != null)
      product.setDescription(productData.description());
    if(productData.price() != null)
      product.setPrice(productData.price());
    cacheService.deleteCatalogByOwner(categoryService.getCategory(product.getCategoryId()).getOwner());
    return productRepository.save(product);
  }

  @Caching(evict={
    @CacheEvict,
    @CacheEvict(value="categories", key="#result")
  })
  public Long deleteProduct(Long id) {
    var product = getProduct(id);
    var categoryId = product.getCategoryId();
    cacheService.deleteCatalogByOwner(categoryService.getCategory(product.getCategoryId()).getOwner());
    productRepository.delete(product);
    return categoryId;
  }
  
}