package org.ruslan.core.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ruslan.core.entity.ProductPriceShop;
import org.ruslan.utils.HibernateSessionFactoryUtil;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductPriceShopRepository extends RepositoryBase<Long, ProductPriceShop> {


    public ProductPriceShopRepository() {
        super(ProductPriceShop.class);
    }



    @Override
    public List<ProductPriceShop> findAll()    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        List<ProductPriceShop> entity = session.createSelectionQuery("SELECT p FROM ProductPriceShop p \n" +
                "inner join Shop s on s.id= p.shop.id\n" +
                " inner join Product pr on pr.id = p.product.id", ProductPriceShop.class).list();
                tx1.commit();
        session.close();
        return entity;
    }
}
