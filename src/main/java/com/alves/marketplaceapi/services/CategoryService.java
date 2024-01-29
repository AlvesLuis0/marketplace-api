package com.alves.marketplaceapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alves.marketplaceapi.domain.category.Category;
import com.alves.marketplaceapi.domain.category.CategoryRequest;
import com.alves.marketplaceapi.domain.category.exceptions.CategoryNotFoundException;
import com.alves.marketplaceapi.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  private Category convert(CategoryRequest categoryData) {
    var category = new Category();
    category.setName(categoryData.name());
    category.setDescription(categoryData.description());
    return category;
  }

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Category getCategory(Long id) {
    var category = categoryRepository.findById(id)
      .orElseThrow(() -> new CategoryNotFoundException("ID", id));
    return category;
  }

  public Category createCategory(CategoryRequest categoryData) {
    var category = convert(categoryData);
    category.setProducts(List.of());
    return categoryRepository.save(category);
  }

  public Category updateCategory(Long id, CategoryRequest categoryData) {
    var category = getCategory(id);
    if(categoryData.name() != null)
      category.setName(categoryData.name());
    if(categoryData.description() != null)
      category.setDescription(categoryData.description());
    return categoryRepository.save(category);
  }

  public void deleteCategory(Long id) {
    var category = getCategory(id);
    categoryRepository.delete(category);
  }
  
}