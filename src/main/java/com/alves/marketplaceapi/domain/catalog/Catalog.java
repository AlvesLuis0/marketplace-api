package com.alves.marketplaceapi.domain.catalog;

import java.io.Serializable;
import java.util.List;

import com.alves.marketplaceapi.domain.category.Category;

public record Catalog(

  List<Category> categories

) implements Serializable {}