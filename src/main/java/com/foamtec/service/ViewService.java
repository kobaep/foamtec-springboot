package com.foamtec.service;

import java.security.Principal;

import com.foamtec.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class ViewService {

	@Autowired
	private AppUserService appUserService;
	
	public void addMenuAndName(ModelAndView model, Principal principal) {
		if(principal.getName().equals("user")) {
			model.addObject("name", principal.getName());
			model.addObject("roleName", "testName");
		} else {
			AppUser appUser = appUserService.findByName(principal.getName());
			model.addObject("name", appUser.getName());
			model.addObject("roleName", appUser.getRoleName());
		}
		model.addObject("logout", "on");
	}
	
	public void addLogin(ModelAndView model) {
		model.addObject("login", "on");
	}
}
