package com.foamtec.dao;

import com.foamtec.domain.MaterialType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.criterion.Order;
import java.util.List;

/**
 * Created by apichat on 4/20/2016 AD.
 */
@Repository
@Transactional
public class MaterialTypeDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(MaterialType materialType) {
        entityManager.persist(materialType);
    }

    public void delete(MaterialType materialType) {
        if (entityManager.contains(materialType)) {
            entityManager.remove(materialType);
        } else {
            entityManager.remove(entityManager.merge(materialType));
        }
    }

    public List<MaterialType> getAll() {
        return entityManager.createQuery("SELECT o FROM MaterialType o order by createDate", MaterialType.class).getResultList();
    }

    public MaterialType getById(long id) {
        return entityManager.find(MaterialType.class, id);
    }

    public void update(MaterialType materialType) {
        entityManager.merge(materialType);
        entityManager.flush();
    }

    public MaterialType findByTypeName(String typeName) {
        Criteria c = ((Session) entityManager.getDelegate()).createCriteria(MaterialType.class);
        c.add(Restrictions.eq("typeName", typeName));
        return (MaterialType)c.uniqueResult();
    }
}
