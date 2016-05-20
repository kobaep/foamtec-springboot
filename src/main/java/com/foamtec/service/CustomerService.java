package com.foamtec.service;

import com.foamtec.dao.CustomerDao;
import com.foamtec.domain.Customer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

/**
 * Created by apichat on 5/16/2016 AD.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public void create(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Customer customer = new Customer();
        customer.setName(jsonObject.getString("inputCustomer"));
        customerDao.create(customer);
    }

    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    public JSONArray getAllJson() {
        List<Customer> customers = getAll();
        JSONArray jsonArray = new JSONArray();
        for (Customer c : customers) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", c.getName());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
}
