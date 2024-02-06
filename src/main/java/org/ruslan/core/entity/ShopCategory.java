package org.ruslan.core.entity;

import jakarta.persistence.*;


@Table(name = "shop_category")
public class ShopCategory extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name="shop_id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryOfShop categoryOfShop;
}
