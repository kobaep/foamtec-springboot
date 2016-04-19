package com.foamtec.service;

import java.security.Principal;
import org.springframework.web.servlet.ModelAndView;

public class ViewControllerService {
	
	public void addMenuAndName(ModelAndView model, Principal principal) {
//		AppUser appUser = appUserManager.findAppUserByName(principal.getName());
		model.addObject("name", principal.getName());
		model.addObject("logout", "on");
		model.addObject("createUser", "on");
		model.addObject("roleName", "testName");
	}
	
	public void addLogin(ModelAndView model) {
		model.addObject("login", "on");
	}
}
