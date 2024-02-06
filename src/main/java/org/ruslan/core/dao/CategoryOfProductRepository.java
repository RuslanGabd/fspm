package org.ruslan.core.dao;


import org.ruslan.core.entity.CategoryOfProduct;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryOfProductRepository extends RepositoryBase<Long, CategoryOfProduct>   {
    public CategoryOfProductRepository() {
        super( CategoryOfProduct.class);
    }

    private static CategoryOfProductRepository INSTANCE;
    public static CategoryOfProductRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryOfProductRepository();
        }
        return INSTANCE;
    }

}
