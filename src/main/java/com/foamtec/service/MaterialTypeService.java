package com.foamtec.service;

import com.foamtec.dao.MaterialTypeDao;
import com.foamtec.domain.AppUser;
import com.foamtec.domain.MaterialType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by apichat on 4/20/2016 AD.
 */
public class MaterialTypeService {

    private final Logger LOGGER = LoggerFactory.getLogger(MaterialTypeService.class);

    @Autowired
    private MaterialTypeDao materialTypeDao;

    @Autowired
    private AppUserService appUserService;

    public MaterialType create(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        AppUser appUser = appUserService.findByUsername(principal.getName());
        MaterialType materialType = new MaterialType();
        materialType.setCreateDate(new Date());
        materialType.setCreateBy(appUser);
        materialType.setTypeName(jsonObject.getString("inputMaterialType"));
        materialTypeDao.create(materialType);
        return materialType;
    }

    public MaterialType update(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        AppUser appUser = appUserService.findByUsername(principal.getName());
        MaterialType materialType = materialTypeDao.getById(jsonObject.getLong("inputId"));
        materialType.setCreateDate(new Date());
        materialType.setCreateBy(appUser);
        materialType.setTypeName(jsonObject.getString("inputMaterialType"));
        materialTypeDao.create(materialType);
        return materialType;
    }

    public void delete(String data) {
        JSONObject jsonObject = new JSONObject(data);
        MaterialType materialType = findById(jsonObject.getLong("inputId"));
        materialTypeDao.delete(materialType);
    }

    public List<MaterialType> findAll() {
        return materialTypeDao.getAll();
    }

    public MaterialType findByTypeName(String typeName) {
        return materialTypeDao.findByTypeName(typeName);
    }

    public MaterialType findById(Long id) {
        return materialTypeDao.getById(id);
    }

}
