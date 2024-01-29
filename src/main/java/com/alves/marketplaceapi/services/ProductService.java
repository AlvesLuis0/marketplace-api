package com.alves.marketplaceapi.services;

import org.springframework.stereotype.Service;

import com.alves.marketplaceapi.domain.product.Product;
import com.alves.marketplaceapi.domain.product.ProductRequest;
import com.alves.marketplaceapi.domain.product.exceptions.ProductNotFoundException;
import com.alves.marketplaceapi.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

  private final ProductRepository productRepository;

  private Product convert(ProductRequest productData) {
    var product = new Product();
    product.setCategoryId(productData.categoryId());
    product.setName(productData.name());
    product.setDescription(productData.description());
    product.setPrice(productData.price());
    return product;
  }

  public Product getProduct(Long id) {
    var product = productRepository.findById(id)
      .orElseThrow(() -> new ProductNotFoundException("ID", id));
    return product;
  }

  public Product createProduct(ProductRequest productData) {
    var product = convert(productData);
    return productRepository.save(product);
  }

  public Product updateProduct(Long id, ProductRequest productData) {
    var product = getProduct(id);
    if(productData.categoryId() != null)
      product.setCategoryId(productData.categoryId());
    if(productData.name() != null)
      product.setName(productData.name());
    if(productData.description() != null)
      product.setDescription(productData.description());
    if(productData.price() != null)
      product.setPrice(productData.price());
    return productRepository.save(product);
  }

  public void deleteProduct(Long id) {
    var product = getProduct(id);
    productRepository.delete(product);
  }
  
}