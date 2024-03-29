package org.ruslan.core.dao;


import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ruslan.core.entity.BaseEntity;
import org.ruslan.utils.HibernateSessionFactoryUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {

    private final Class<E> clazz;
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    @Override
    public E save(E entity) {
        Transaction tx1 = session.beginTransaction();
        session.persist(entity);
        tx1.commit();
        session.close();
        return entity;
    }

    @Override
    public void delete(K id) {
        Transaction tx1 = session.beginTransaction();
        session.remove(id);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(E entity) {
        Transaction tx1 = session.beginTransaction();
        session.merge(entity);
        tx1.commit();
        session.close();
    }


    @Override
    public Optional<E> findById(K id, Map<String, Object> properties) {
        Transaction tx1 = session.beginTransaction();
        var object = session.find(clazz, id, properties);
        tx1.commit();
        session.close();
        return Optional.ofNullable(object);
    }

    @Override
    public List<E> findAll() {
        Transaction tx1 = session.beginTransaction();
        List<E> entity = session.createSelectionQuery("FROM " + clazz.getSimpleName(), clazz).list();
        tx1.commit();
        session.close();
        return entity;
    }

    public Optional<E> findByName(String name) {
        Transaction tx1 = session.beginTransaction();
        var object = session.find(clazz, name);
        tx1.commit();
        session.close();
        return Optional.ofNullable(object);
    }

}
