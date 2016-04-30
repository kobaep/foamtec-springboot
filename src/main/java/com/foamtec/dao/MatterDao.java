package com.foamtec.dao;

import com.foamtec.domain.Matter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by apichat on 4/21/2016 AD.
 */
@Repository
@Transactional
public class MatterDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MaterialTypeDao materialTypeDao;

    public Matter getById(long id) {
        return entityManager.find(Matter.class, id);
    }

    public List<Matter> getAll() {
        return entityManager.createQuery("SELECT o FROM Matter o order by createDate", Matter.class).getResultList();
    }

    public void update(Matter matter) {
        entityManager.merge(matter);
        entityManager.flush();
    }
}
