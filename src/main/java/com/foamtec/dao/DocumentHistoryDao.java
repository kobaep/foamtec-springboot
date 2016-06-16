package com.foamtec.dao;

import com.foamtec.domain.DocumentHistory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created by apichat on 6/14/2016 AD.
 */
@Repository
@Transactional
public class DocumentHistoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<DocumentHistory> findByStartDateEndDateAndStatus(Date start, Date end, String status) {
        Criteria c = ((Session)entityManager.getDelegate()).createCriteria(DocumentHistory.class);
        Criterion case1 = Restrictions.between("createDate", start, end);
        Criterion case2 = Restrictions.like("status", status);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        c.add(Restrictions.and(case1, case2));
        return c.list();
    }
}
