package org.ruslan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;




@Table(name = "product_category")
public class ProductCategory {

  @ManyToOne
  @JoinColumn (name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn (name = "category_id")
  private CategoryOfProduct categoryOfProduct;
}
