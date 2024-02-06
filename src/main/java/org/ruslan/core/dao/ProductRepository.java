package org.ruslan.core.dao;


import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.ruslan.core.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository extends RepositoryBase<Long, Product> {
    private final EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

      public ProductRepository(EntityManager entityManager) {
        super(Product.class);
          this.entityManager = entityManager;
      }


    @Override
    public List<Product> findAll() {
      return  getSession().createSelectionQuery(" SELECT p FROM Product p " +
                "JOIN FETCH p.categoryOfProducts", Product.class).list();
      }



    @Override
    public Optional<Product> findByName(String name) {
        Product pr = getSession()
                .createSelectionQuery("select p from Product p where p.name=:name", Product.class)
                .setParameter("name",name)
                .uniqueResult();
             return Optional.ofNullable(pr);
    }
}
