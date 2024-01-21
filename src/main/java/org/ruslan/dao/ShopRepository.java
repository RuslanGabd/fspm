package org.ruslan.dao;



import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ruslan.entity.Product;
import org.ruslan.entity.Shop;
import org.ruslan.util.HibernateSessionFactoryUtil;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public class ShopRepository extends RepositoryBase<Long, Shop> {


    public ShopRepository() {
        super(Shop.class);
    }
    private static ShopRepository INSTANCE;
    public static ShopRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShopRepository();
        }
        return INSTANCE;
    }

    @Override
    public List<Shop> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        // List<Product> entity = session.createSelectionQuery("FROM " + clazz.getSimpleName(), clazz).list();
        List<Shop> entity = session.createSelectionQuery(" SELECT s FROM Shop s " +
                "JOIN FETCH s.productPriceShops", Shop.class).list();
        tx1.commit();
        session.close();
        return entity;
    }
//    public List<Order> getCompletedOrdersForPeriod(LocalDate minusMonths, LocalDate now) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        List<Order> ordersCompleted = session
//                .createQuery("select o from Order o where status='completed' " +
//                        "and dateExecution BETWEEN :firstDate and :secondDate", Order.class)
//                .setParameter("firstDate", minusMonths)
//                .setParameter("secondDate", now)
//                .list();
//        session.close();
//        return ordersCompleted;
//
//    }
}
