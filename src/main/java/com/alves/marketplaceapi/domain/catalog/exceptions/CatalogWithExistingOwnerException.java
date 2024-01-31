package com.alves.marketplaceapi.domain.catalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CatalogWithExistingOwnerException extends RuntimeException {

  public CatalogWithExistingOwnerException(String owner) {
    super(String.format("There is already a catalog with the owner '%s'.", owner));
  }
  
}