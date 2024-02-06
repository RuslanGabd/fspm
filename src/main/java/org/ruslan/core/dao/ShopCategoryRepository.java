package org.ruslan.core.dao;

import org.ruslan.core.entity.ShopCategory;
import org.springframework.stereotype.Repository;

@Repository
public class ShopCategoryRepository extends RepositoryBase<Long, ShopCategory> {
    public ShopCategoryRepository() {
        super(ShopCategory.class);
    }

}
