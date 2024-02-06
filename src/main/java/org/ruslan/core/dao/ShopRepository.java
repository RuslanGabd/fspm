package org.ruslan.core.dao;


import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.ruslan.core.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ShopRepository extends RepositoryBase<Long, Shop> {


    private final EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public ShopRepository(EntityManager entityManager) {
        super(Shop.class);
        this.entityManager = entityManager;
    }

    @Override
    public List<Shop> findAll() {
        return getSession().createSelectionQuery(" SELECT s FROM Shop s " +
                "JOIN FETCH s.categoryOfShop", Shop.class).list();
    }

    @Override
    public Optional<Shop> findByName(String name) {
        Shop sh = getSession()
                .createSelectionQuery("select s from Shop s where s.name=:name", Shop.class)
                .setParameter("name", name)
                .uniqueResult();
        return Optional.ofNullable(sh);
    }
}
