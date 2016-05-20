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
}
