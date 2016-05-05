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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

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

    public List<Matter> findByStatus(String status) {
        Session session = ((Session) entityManager.getDelegate());
        org.hibernate.Query query = session.createQuery("SELECT o FROM Matter o WHERE o.status = :status order by createDate");
        query.setParameter("status", status);
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

    public List<Matter> findAllMaterialGe(Date date) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Matter> cq = builder.createQuery(Matter.class);
        Root<Matter> root = cq.from(Matter.class);
        cq.where(
                builder.or(
                        builder.lessThanOrEqualTo(root.<Date>get("rohsAlertDateTest"), date),
                        builder.lessThanOrEqualTo(root.<Date>get("halogenAlertDateTest"), date)
                )
        );
        return entityManager.createQuery(cq).getResultList();
    }
}
