package org.ruslan.user.userDao;


import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.ruslan.core.dao.RepositoryBase;
import org.ruslan.user.userEntity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepository extends RepositoryBase<Long, Role> {

    private final EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public RoleRepository(EntityManager entityManager) {
        super(Role.class);
        this.entityManager = entityManager;
    }

    public Optional<Role> findByName(String name) {
        Role role = getSession().createQuery("select name" +
                        " from Role o where name=:roleName", Role.class)
                .setParameter("roleName", name).uniqueResult();
        return Optional.ofNullable(role);
    }

}
