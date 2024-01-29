package com.alves.marketplaceapi.domain.category.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

  public CategoryNotFoundException(String field, Object value) {
    super(String.format("Category with %s '%s' not found.", field, value));
  }
  
}