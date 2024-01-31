package com.alves.marketplaceapi.services;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alves.marketplaceapi.domain.catalog.Catalog;
import com.alves.marketplaceapi.domain.catalog.CatalogRequest;
import com.alves.marketplaceapi.domain.catalog.exceptions.CatalogNotFoundException;
import com.alves.marketplaceapi.domain.catalog.exceptions.CatalogWithExistingOwnerException;
import com.alves.marketplaceapi.repositories.CatalogRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CacheConfig(cacheNames="catalogs")
@Service
public class CatalogService {

  private final CatalogRepository catalogRepository;
  private final CacheService cacheService;

  private Catalog convert(CatalogRequest catalogData) {
    var catalog = new Catalog();
    catalog.setOwner(catalogData.owner());
    return catalog;
  }

  @Cacheable
  public Catalog getCatalog(String owner) {
    var catalog = catalogRepository.findById(owner)
      .orElseThrow(() -> new CatalogNotFoundException("owner", owner));
    return catalog;
  }

  @CachePut(key="#result.owner")
  public Catalog createCatalog(CatalogRequest catalogData) {
    var catalog = convert(catalogData);
    catalogRepository.findById(catalog.getOwner())
      .ifPresent(s -> { throw new CatalogWithExistingOwnerException(catalog.getOwner()); });
    catalog.setCategories(List.of());
    return catalogRepository.save(catalog);
  }

  @CacheEvict
  public void deleteCatalog(String owner) {
    var catalog = getCatalog(owner);
    cacheService.deleteAllCategoriesByCatalog(catalog);
    catalogRepository.delete(catalog);
  }
  
}