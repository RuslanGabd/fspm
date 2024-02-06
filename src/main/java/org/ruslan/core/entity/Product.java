package org.ruslan.core.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product extends BaseEntity<Long> implements Serializable {


    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonUnwrapped
    private List<ProductPriceShop> productPriceShops;

    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @ToString.Exclude
    private List<CategoryOfProduct> categoryOfProducts;


    private String description;

    public Product(String name, String description, List<CategoryOfProduct> categoryOfProduct) {
        this.name = name;
        this.description = description;
        this.categoryOfProducts=categoryOfProduct;
    }

}
