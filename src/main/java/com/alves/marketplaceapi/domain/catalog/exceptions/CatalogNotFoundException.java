package com.alves.marketplaceapi.domain.catalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CatalogNotFoundException extends RuntimeException {

  public CatalogNotFoundException(String field, Object value) {
    super(String.format("Catalog with %s '%s' not found.", field, value));
  }
  
}