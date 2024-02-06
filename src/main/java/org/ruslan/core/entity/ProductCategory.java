package org.ruslan.core.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Table(name = "product_category")
public class ProductCategory {

  @ManyToOne
  @JoinColumn (name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn (name = "category_id")
  private CategoryOfProduct categoryOfProduct;
}
