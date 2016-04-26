package com.foamtec.service;

import com.foamtec.dao.MaterialTypeDao;
import com.foamtec.dao.MatterDao;
import com.foamtec.domain.MaterialType;
import com.foamtec.domain.Matter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apichat on 4/21/2016 AD.
 */
public class MatterService {

    @Autowired
    private MatterDao matterDao;

    @Autowired
    private MaterialTypeDao materialTypeDao;

    public Matter findById(Long id) {
        return matterDao.getById(id);
    }

    public List<Matter> findAll() {
        return matterDao.getAll();
    }

}
