package org.ruslan.core.dao;


import org.ruslan.core.entity.CategoryOfShop;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryOfShopRepository extends RepositoryBase<Long, CategoryOfShop>   {
    public CategoryOfShopRepository() {
        super( CategoryOfShop.class);
    }


}
