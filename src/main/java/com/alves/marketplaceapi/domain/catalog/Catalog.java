package com.alves.marketplaceapi.domain.catalog;

import java.io.Serializable;
import java.util.List;

import com.alves.marketplaceapi.domain.category.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Catalog implements Serializable {

  @Id
  private String owner;
  @OneToMany(mappedBy="owner", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
  private List<Category> categories;

}