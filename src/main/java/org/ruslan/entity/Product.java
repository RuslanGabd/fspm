package org.ruslan.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    private String name;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonUnwrapped
    private List<ProductPriceShop> productPriceShops;

//    @ManyToMany
//    @JoinTable(name = "product_category",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id"))
//    @ToString.Exclude
//    private List<CategoryOfProduct> categoryOfProducts;


    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<ProductPriceShop> getProductPriceShops() {
//        return productPriceShops;
//    }
//
//    public void setProductPriceShops(Set<ProductPriceShop> productPriceShops) {
//        this.productPriceShops = productPriceShops;
//    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
