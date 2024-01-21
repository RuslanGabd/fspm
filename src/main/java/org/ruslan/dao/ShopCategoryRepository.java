package org.ruslan.dao;

import org.ruslan.entity.ShopCategory;
import org.springframework.stereotype.Repository;

@Repository
public class ShopCategoryRepository extends RepositoryBase<Long, ShopCategory> {
    public ShopCategoryRepository() {
        super(ShopCategory.class);
    }

}
