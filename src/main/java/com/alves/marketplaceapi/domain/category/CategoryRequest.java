package com.alves.marketplaceapi.domain.category;

public record CategoryRequest(

  String owner,
  String name,
  String description

) {}