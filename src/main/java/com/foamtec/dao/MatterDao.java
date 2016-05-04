package com.foamtec.dao;

import com.foamtec.domain.Matter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<Matter> findByStatus(String status1, String status2) {
        Session session = ((Session) entityManager.getDelegate());
        org.hibernate.Query query = session.createQuery("SELECT o FROM Matter o WHERE o.status = :status1 or  o.status = :status2 order by createDate");
        query.setParameter("status1", status1);
        query.setParameter("status2", status2);
        return query.list();
    }

    public void update(Matter matter) {
        entityManager.merge(matter);
        entityManager.flush();
    }

    public void delete(Matter matter) {
        try {
            Session session = ((Session) entityManager.getDelegate());
            org.hibernate.SQLQuery query1 = session.createSQLQuery("DELETE FROM DocumentHistory c WHERE c.matter = :matterId");
            query1.setParameter("matterId", matter.getId());
            query1.executeUpdate();
            org.hibernate.Query query2 = session.createQuery("DELETE FROM Matter c WHERE c.id = :id");
            query2.setParameter("id", matter.getId());
            query2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
