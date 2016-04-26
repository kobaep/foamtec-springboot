package com.foamtec.web;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.foamtec.service.ViewService;

@Controller
public class MtmsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MtmsController.class);

	@Autowired
	private ViewService viewService;

	@RequestMapping(value = "/mtms", method = RequestMethod.GET)
	public ModelAndView homeMtms(ModelAndView model, Principal principal) {
		try {
			principal.getName();
			viewService.addMenuAndName(model, principal);
		} catch (Exception e) {
			viewService.addLogin(model);
		}
		viewService.addMaterialTypes(model);
		model.setViewName("MTMS/home");
		return model;
	}

	@RequestMapping(value = "/mtms/materialTypePrivate", params = "form", method = RequestMethod.GET)
	public ModelAndView createMaterialType(ModelAndView model, Principal principal) {
		viewService.addMenuAndName(model, principal);
		model.setViewName("MTMS/createMaterialType");
		return model;
	}

	@RequestMapping(value = "/mtms/materialTypePrivate/update", params = "list", method = RequestMethod.GET)
	public ModelAndView updateMaterialTypeList(ModelAndView model, Principal principal) {
		viewService.addMenuAndName(model, principal);
		viewService.addMaterialTypes(model);
		model.setViewName("MTMS/updateMaterialTypeList");
		return model;
	}

	@RequestMapping(value = "/mtms/materialTypePrivate/{id}", params = "update", method = RequestMethod.GET)
	public ModelAndView updateMaterialType(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
		viewService.addMenuAndName(model, principal);
		viewService.addMaterialType(model, id);
		model.setViewName("MTMS/updateMaterialType");
		return model;
	}

	@RequestMapping(value = "/mtms/material/{id}", params = "list", method = RequestMethod.GET)
	public ModelAndView matterList(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
		try {
			principal.getName();
			viewService.addMenuAndName(model, principal);
		} catch (Exception e) {
			viewService.addLogin(model);
		}
		viewService.addMaterialType(model, id);
		model.setViewName("MTMS/materialList");
		return model;
	}

	@RequestMapping(value = "/mtms/materialPrivate/{id}", params = "form", method = RequestMethod.GET)
	public ModelAndView createMaterial(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
		viewService.addMenuAndName(model, principal);
		viewService.addMaterialType(model, id);
		model.setViewName("MTMS/createMaterial");
		return model;
	}
}
