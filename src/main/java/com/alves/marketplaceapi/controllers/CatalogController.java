package com.alves.marketplaceapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.marketplaceapi.domain.catalog.Catalog;
import com.alves.marketplaceapi.domain.catalog.CatalogRequest;
import com.alves.marketplaceapi.services.CatalogService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/catalog")
@RestController
public class CatalogController {

  private final CatalogService catalogService;

  @GetMapping("/{owner}")
  public ResponseEntity<Catalog> getCatalog(@PathVariable String owner) {
    var catalog = catalogService.getCatalog(owner);
    return ResponseEntity.ok(catalog);
  }

  @PostMapping
  public ResponseEntity<Catalog> createCatalog(@RequestBody CatalogRequest catalogData) {
    var catalog = catalogService.createCatalog(catalogData);
    return ResponseEntity.ok(catalog);
  }

  @DeleteMapping("/{owner}")
  public ResponseEntity<Catalog> deleteCatalog(@PathVariable String owner) {
    catalogService.deleteCatalog(owner);
    return ResponseEntity.noContent().build();
  }
  
}