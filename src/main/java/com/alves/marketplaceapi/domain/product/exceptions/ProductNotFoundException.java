package com.alves.marketplaceapi.domain.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String field, Object value) {
    super(String.format("Product with %s '%s' not found.", field, value));
  }
  
}