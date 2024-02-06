package org.ruslan.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "shop")
@AllArgsConstructor
@NoArgsConstructor
public class Shop extends BaseEntity<Long> {
    private String name;
    private String address;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<ProductPriceShop> productPriceShops;

    @ManyToMany
    @JoinTable(name = "shop_category",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @ToString.Exclude
    private List<CategoryOfShop> categoryOfShop;

    public Shop(String name, String address, List<CategoryOfShop> categoryOfShop) {
        this.name = name;
        this.address = address;
        this.categoryOfShop = categoryOfShop;
    }
}
