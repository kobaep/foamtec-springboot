package com.foamtec.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.foamtec.dao.AppUserDao;
import com.foamtec.service.ViewService;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private AppUserDao appUserDao;
	
	@Autowired
	private ViewService viewService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView model, Principal principal) {
		LOGGER.debug("-= home page =-");
		try {
			principal.getName();
			viewService.addMenuAndName(model, principal);
		} catch (Exception e) {
			viewService.addLogin(model);
		}
		model.setViewName("home");
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
							  @RequestParam(value = "logout", required = false) String logout,
							  ModelAndView model) {
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		viewService.addLogin(model);
		model.setViewName("login");
		return model;
	}
}
