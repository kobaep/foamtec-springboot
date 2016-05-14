package com.foamtec.web;

import com.foamtec.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Date;

/**
 * Created by apichat on 5/13/2016 AD.
 */
@Controller
public class CustomerController {

    @Autowired
    private ViewService viewService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ModelAndView homeMtms(ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        model.setViewName("CUSTOMER/home");
        return model;
    }
}
