package org.ruslan.dao;


import org.ruslan.entity.CategoryOfShop;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryOfShopRepository extends RepositoryBase<Long, CategoryOfShop>   {
    public CategoryOfShopRepository() {
        super( CategoryOfShop.class);
    }


}
