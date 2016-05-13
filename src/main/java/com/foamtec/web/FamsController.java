package com.foamtec.web;

import com.foamtec.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by apichat on 5/9/2016 AD.
 */
@Controller
public class FamsController {

    @Autowired
    private ViewService viewService;

    @RequestMapping(value = "/fams", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        model.setViewName("FAMS/home");
        return model;
    }

    @RequestMapping(value = "/fams/requestPrivate", params = "form", method = RequestMethod.GET)
    public ModelAndView createMaterialType(ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.setViewName("FAMS/createFa");
        return model;
    }
}
