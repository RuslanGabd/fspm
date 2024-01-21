package org.ruslan.dao;


import org.ruslan.entity.CategoryOfProduct;
import org.ruslan.entity.ProductCategory;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryOfProductRepository extends RepositoryBase<Long, CategoryOfProduct>   {
    public CategoryOfProductRepository() {
        super( CategoryOfProduct.class);
    }


}
