package com.foamtec.dao;

import com.foamtec.domain.AppUser;
import com.foamtec.domain.Customer;
import com.foamtec.domain.FaRequest;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
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

    public List<FaRequest> findByName(AppUser appUser) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FaRequest> cq = builder.createQuery(FaRequest.class);
        Root<FaRequest> root = cq.from(FaRequest.class);
        cq.where(
                builder.equal(root.get("createBy"), appUser)
        );
        cq.orderBy(builder.asc(root.get("updateDate")));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<FaRequest> findByNameAndStatus(String name, String status) {
        Session session = ((Session) entityManager.getDelegate());
        org.hibernate.Query query = session.createQuery("SELECT o FROM FaRequest o WHERE o.saleOut = :name and  o.status = :status order by updateDate");
        query.setParameter("name", name);
        query.setParameter("status", status);
        return query.list();
    }

    public List<FaRequest> findByStartDateEndDateAndStatusByUser(Date start, Date end, String statusSearch) {
        Criteria c = ((Session)entityManager.getDelegate()).createCriteria(FaRequest.class);
        Criterion case1 = Restrictions.between("updateDate", start, end);
        Criterion case2 = Restrictions.like("customer", statusSearch);
        Criterion case3 = Restrictions.like("partNo", statusSearch);
        Criterion case4 = Restrictions.like("faNumber", statusSearch);
        Criterion case5 = Restrictions.or(case2, case3, case4);

        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        c.add(Restrictions.and(case1, case5));
        return c.list();
    }

}
