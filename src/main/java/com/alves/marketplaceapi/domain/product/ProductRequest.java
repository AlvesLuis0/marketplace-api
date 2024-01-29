package com.alves.marketplaceapi.domain.product;

import java.math.BigDecimal;

public record ProductRequest(

  Long categoryId,
  String name,
  String description,
  BigDecimal price

) {}