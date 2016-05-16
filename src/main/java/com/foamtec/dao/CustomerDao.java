package com.foamtec.dao;

import com.foamtec.domain.AppUser;
import com.foamtec.domain.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by apichat on 5/16/2016 AD.
 */
@Repository
@Transactional
public class CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Customer customer) {
        entityManager.persist(customer);
    }

    public List<Customer> getAll() {
        Criteria c = ((Session) entityManager.getDelegate()).createCriteria(Customer.class);
        return c.list();
    }
}
