package com.foamtec.dao;

import com.foamtec.domain.FileBackup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by apichat on 5/5/2016 AD.
 */
@Repository
@Transactional
public class FileBackupDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(FileBackup fileBackup) {
        entityManager.persist(fileBackup);
    }

    public void update(FileBackup fileBackup) {
        entityManager.merge(fileBackup);
    }
}
