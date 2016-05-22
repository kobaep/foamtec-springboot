package com.foamtec.dao;

import com.foamtec.domain.AppUser;
import com.foamtec.domain.Customer;
import com.foamtec.domain.FaRequest;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by apichat on 5/20/2016 AD.
 */
@Repository
@Transactional
public class FaRequestDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(FaRequest faRequest) {
        entityManager.persist(faRequest);
    }

    public List<FaRequest> getAll() {
        Criteria c = ((Session) entityManager.getDelegate()).createCriteria(FaRequest.class);
        return c.list();
    }

    public FaRequest getById(long id) {
        return entityManager.find(FaRequest.class, id);
    }

    public void update(FaRequest faRequest) {
        entityManager.merge(faRequest);
    }

    public List<FaRequest> findByStatus(String status) {
        Session session = ((Session) entityManager.getDelegate());
        org.hibernate.Query query = session.createQuery("SELECT o FROM FaRequest o WHERE o.status = :status order by updateDate");
        query.setParameter("status", status);
        return query.list();
    }

    public List<FaRequest> findByStatus(String status1, String status2) {
        Session session = ((Session) entityManager.getDelegate());
        org.hibernate.Query query = session.createQuery("SELECT o FROM FaRequest o WHERE o.status = :status1 or  o.status = :status2 order by updateDate");
        query.setParameter("status1", status1);
        query.setParameter("status2", status2);
        return query.list();
    }

}
