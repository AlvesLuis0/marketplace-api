package com.alves.marketplaceapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.marketplaceapi.domain.category.Category;
import com.alves.marketplaceapi.domain.category.CategoryRequest;
import com.alves.marketplaceapi.services.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/category")
@RestController
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategory(@PathVariable Long id) {
    var category = categoryService.getCategory(id);
    return ResponseEntity.ok(category);
  }

  @PostMapping
  public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryData) {
    var category = categoryService.createCategory(categoryData);
    return ResponseEntity.ok(category);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryData) {
    var category = categoryService.updateCategory(id, categoryData);
    return ResponseEntity.ok(category);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }
  
}