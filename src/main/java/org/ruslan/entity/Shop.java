package org.ruslan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shop")
@AllArgsConstructor
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

    public Shop() {

    }
}
