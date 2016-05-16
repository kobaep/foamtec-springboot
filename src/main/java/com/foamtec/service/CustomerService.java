package com.foamtec.service;

import com.foamtec.dao.CustomerDao;
import com.foamtec.domain.Customer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.List;

/**
 * Created by apichat on 5/16/2016 AD.
 */
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
}
