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
public class MtmsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MtmsController.class);

	@Autowired
	private ViewControllerService viewControllerService;

	@RequestMapping(value = "/mtms", method = RequestMethod.GET)
	public ModelAndView homeMtms(ModelAndView model, Principal principal) {
		LOGGER.debug("-= home MTMS page =-");
		try {
			principal.getName();
			viewControllerService.addMenuAndName(model, principal);
		} catch (Exception e) {
			viewControllerService.addLogin(model);
		}
		model.setViewName("MTMS/home");
		return model;
	}
}
