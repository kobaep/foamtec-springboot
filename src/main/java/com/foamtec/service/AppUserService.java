package com.foamtec.service;

import com.foamtec.dao.AppUserDao;
import com.foamtec.domain.AppUser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

/**
 * Created by apichat on 4/19/2016 AD.
 */
@Service
public class AppUserService {

    private final Logger LOGGER = LoggerFactory.getLogger(AppUserService.class);

    @Autowired
    private AppUserDao appUserDao;

    public AppUser create(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        AppUser appUser = new AppUser();
        appUser.setUsername(jsonObject.getString("inputUser"));
        appUser.setPassword(jsonObject.getString("inputPassword"));
        appUser.setName(jsonObject.getString("inputName"));
        appUser.setDepartment(jsonObject.getString("inputDepartment"));
        appUser.setDepartmentCode(jsonObject.getString("inputDepartmentCode"));
        appUser.setEnabled(1);
        appUser.setEmailAddress(jsonObject.getString("inputEmail"));
        appUser.setPhoneNumber(jsonObject.getString("inputTelephoneNumber"));
        appUser.setRoleName(jsonObject.getString("inputRoleName"));
        appUserDao.create(appUser);
        return appUser;
    }

    public AppUser update(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        AppUser appUser = findById(jsonObject.getLong("inputId"));
        appUser.setUsername(jsonObject.getString("inputUser"));
        appUser.setPassword(jsonObject.getString("inputPassword"));
        appUser.setName(jsonObject.getString("inputName"));
        appUser.setDepartment(jsonObject.getString("inputDepartment"));
        appUser.setDepartmentCode(jsonObject.getString("inputDepartmentCode"));
        appUser.setEnabled(1);
        appUser.setEmailAddress(jsonObject.getString("inputEmail"));
        appUser.setPhoneNumber(jsonObject.getString("inputTelephoneNumber"));
        appUser.setRoleName(jsonObject.getString("inputRoleName"));
        appUserDao.update(appUser);
        return appUser;
    }

    public void delete(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        AppUser appUser = findById(jsonObject.getLong("inputId"));
        appUserDao.delete(appUser);
    }

    public List<AppUser> findAll() {
        return appUserDao.getAll();
    }

    public List<AppUser> findAllSaleOut() {
        return appUserDao.getAllSaleOut();
    }

    public AppUser findById(Long id) {
        return appUserDao.getById(id);
    }

    public AppUser findByUsername(String username) {
        return appUserDao.getByUsername(username);
    }

    public JSONArray getAllSaleOutJson() {
        List<AppUser> appUsers = findAllSaleOut();
        JSONArray jsonArray = new JSONArray();
        for (AppUser a : appUsers) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", a.getName());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
}
