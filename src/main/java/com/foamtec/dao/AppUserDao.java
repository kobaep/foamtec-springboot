package com.foamtec.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foamtec.domain.AppUser;

import antlr.collections.List;

@Repository
@Transactional
public class AppUserDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void create(AppUser appUser) {
		entityManager.persist(appUser);
	}

	public void delete(AppUser appUser) {
		if (entityManager.contains(appUser)) {
			entityManager.remove(appUser);
		} else {
			entityManager.remove(entityManager.merge(appUser));
		}
	}

	public List getAll() {
		return (List) entityManager.createQuery("from AppUser").getResultList();
	}

	public AppUser getById(long id) {
		return entityManager.find(AppUser.class, id);
	}

	public void update(AppUser appUser) {
		entityManager.merge(appUser);
	}

}
