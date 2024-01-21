package org.ruslan.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ruslan.entity.ProductPriceShop;
import org.ruslan.util.HibernateSessionFactoryUtil;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductPriceShopRepository extends RepositoryBase<Long, ProductPriceShop> {

    public ProductPriceShopRepository() {
        super(ProductPriceShop.class);
    }

    private static ProductPriceShopRepository INSTANCE;
    public static ProductPriceShopRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductPriceShopRepository();
        }
        return INSTANCE;
    }
    //@Query("Select pps from ProductPriceShop pps inner join Product p on pps.product.id = p.id inner join Shop s on pps.shop.id=s.id")
    @Override
    public List<ProductPriceShop> findAll()    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
     //    List<ProductPriceShop> entity = session.createSelectionQuery("FROM " + ProductPriceShop.class.getSimpleName(), ProductPriceShop.class).list();
     //   List<ProductPriceShop> entity = session.createSelectionQuery(" SELECT p FROM ProductPriceShop p " +
       //         "inner join Shop s on s.id= p.shop.id inner join Product pr on pr.id = p.product.id", ProductPriceShop.class).list();

        List<ProductPriceShop> entity = session.createSelectionQuery("SELECT p FROM ProductPriceShop p \n" +
                "inner join Shop s on s.id= p.shop.id\n" +
                " inner join Product pr on pr.id = p.product.id", ProductPriceShop.class).list();
                tx1.commit();
        session.close();
        return entity;
    }
}
