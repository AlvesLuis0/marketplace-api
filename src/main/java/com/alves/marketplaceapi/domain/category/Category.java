package com.alves.marketplaceapi.domain.category;

import java.util.List;

import com.alves.marketplaceapi.domain.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  @OneToMany(mappedBy = "categoryId", cascade = CascadeType.REMOVE)
  private List<Product> products;
  
}