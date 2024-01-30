package com.alves.marketplaceapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.marketplaceapi.domain.catalog.Catalog;
import com.alves.marketplaceapi.services.CatalogService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/catalog")
@RestController
public class CatalogController {

  private final CatalogService catalogService;

  @GetMapping
  public ResponseEntity<Catalog> getCatalog() {
    var catalog = catalogService.getCatalog();
    return ResponseEntity.ok(catalog);
  }
  
}