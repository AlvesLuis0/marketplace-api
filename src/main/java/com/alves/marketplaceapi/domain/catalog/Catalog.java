package com.alves.marketplaceapi.domain.catalog;

import java.util.List;

import com.alves.marketplaceapi.domain.category.Category;

public record Catalog(

  List<Category> categories

) {}