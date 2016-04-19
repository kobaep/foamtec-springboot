package com.foamtec.web;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.foamtec.service.ViewControllerService;

@Controller
public class AppUserController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(AppUserController.class);
	
	@Autowired
	private ViewControllerService viewControllerService;
	
	@RequestMapping(value = "/appuser", params = "form", method = RequestMethod.GET)
	public ModelAndView appUserPageCreate(ModelAndView model, Principal principal) {
		LOGGER.debug("-= AppUser Page Create =-");
		viewControllerService.addMenuAndName(model, principal);
		model.setViewName("APPUSER/create");
		return model;
	}

}
