package org.ruslan.dao;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ruslan.entity.Product;
import org.ruslan.util.HibernateSessionFactoryUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository extends RepositoryBase<Long,Product>   {
    public ProductRepository() {
        super(Product.class);
    }
    private static ProductRepository INSTANCE;
    ObjectMapper objectMapper;

//  //  private JsonReader() {
//        this.objectMapper = new ObjectMapper();
//    }


//    @Override
//    public List<Product> findAll() {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        List<Product> entity = session.createSelectionQuery("FROM " + Product.class.getSimpleName(), Product.class).list();
//        tx1.commit();
//        session.close();
//        return entity;
////    }
@Override
public List<Product> findAll() {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction tx1 = session.beginTransaction();
   // List<Product> entity = session.createSelectionQuery("FROM " + clazz.getSimpleName(), clazz).list();
    List<Product> entity = session.createSelectionQuery(" SELECT p FROM Product p " +
            "JOIN FETCH p.productPriceShops", Product.class).list();
    tx1.commit();
    session.close();
    return entity;
}




    public static ProductRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductRepository();
        }
        return INSTANCE;
    }
}
