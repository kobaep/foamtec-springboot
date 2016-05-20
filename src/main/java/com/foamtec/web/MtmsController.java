package com.foamtec.web;

import java.security.Principal;
import java.util.Date;

import com.foamtec.service.MaterialTypeService;
import com.foamtec.service.MatterService;
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

	@Autowired
	private MaterialTypeService materialTypeService;

	@Autowired
	private MatterService matterService;

	@RequestMapping(value = "/mtms", method = RequestMethod.GET)
	public ModelAndView homeMtms(ModelAndView model, Principal principal) {
		try {
			principal.getName();
			viewService.addMenuAndName(model, principal);
		} catch (Exception e) {
			viewService.addLogin(model);
		}
		model.addObject("materialTypes", materialTypeService.findAll());
		model.addObject("materials", matterService.findByStatus("CREATE","UPDATE"));
		model.addObject("materialsReject", matterService.findByStatus("REJECT", "REQUESTDOC"));
		model.addObject("materialExpired", matterService.findAllMaterialGe(new Date()));
		model.setViewName("MTMS/home");
		return model;
	}

	@RequestMapping(value = "/mtms/material", params = "waitApproveList", method = RequestMethod.GET)
	public ModelAndView waitApproveList(ModelAndView model, Principal principal) {
		try {
			principal.getName();
			viewService.addMenuAndName(model, principal);
		} catch (Exception e) {
			viewService.addLogin(model);
		}
		model.addObject("materials", matterService.findByStatus("CREATE","UPDATE"));
		model.setViewName("MTMS/waitApproveList");
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
		model.addObject("materialTypes", materialTypeService.findAll());
		model.setViewName("MTMS/updateMaterialTypeList");
		return model;
	}

	@RequestMapping(value = "/mtms/materialTypePrivate/{id}", params = "update", method = RequestMethod.GET)
	public ModelAndView updateMaterialType(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
		viewService.addMenuAndName(model, principal);
		model.addObject("materialType", materialTypeService.findById(id));
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
		model.addObject("materialType", materialTypeService.findById(id));
		model.setViewName("MTMS/materialList");
		return model;
	}

	@RequestMapping(value = "/mtms/material/{id}", params = "updateList", method = RequestMethod.GET)
	public ModelAndView matterUpdateList(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
		try {
			principal.getName();
			viewService.addMenuAndName(model, principal);
		} catch (Exception e) {
			viewService.addLogin(model);
		}
		model.addObject("materialType", materialTypeService.findById(id));
		model.setViewName("MTMS/materialUpdateList");
		return model;
	}

	@RequestMapping(value = "/mtms/materialPrivate/{id}", params = "form", method = RequestMethod.GET)
	public ModelAndView createMaterial(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
		viewService.addMenuAndName(model, principal);
		model.addObject("materialType", materialTypeService.findById(id));
		model.setViewName("MTMS/createMaterial");
		return model;
	}

	@RequestMapping(value = "/mtms/materialPrivate/{id}", params = "update", method = RequestMethod.GET)
	public ModelAndView updateMaterial(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
		viewService.addMenuAndName(model, principal);
		model.addObject("material", matterService.findById(id));
		model.setViewName("MTMS/updateMaterial");
		return model;
	}

	@RequestMapping(value = "/mtms/materialPrivate/{id}", params = "approve", method = RequestMethod.GET)
	public ModelAndView approveMaterial(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
		viewService.addMenuAndName(model, principal);
		model.addObject("material", matterService.findById(id));
		model.setViewName("MTMS/approveMaterial");
		return model;
	}

	@RequestMapping(value = "/mtms/material", params = "rejectList", method = RequestMethod.GET)
	public ModelAndView rejectListMaterial(ModelAndView model, Principal principal) {
		try {
			principal.getName();
			viewService.addMenuAndName(model, principal);
		} catch (Exception e) {
			viewService.addLogin(model);
		}
		model.addObject("materialsReject", matterService.findByStatus("REJECT", "REQUESTDOC"));
		model.setViewName("MTMS/rejectListMaterial");
		return model;
	}

	@RequestMapping(value = "/mtms/material/{id}", params = "approvel", method = RequestMethod.GET)
	public ModelAndView materialsApprovel(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
		try {
			principal.getName();
			viewService.addMenuAndName(model, principal);
		} catch (Exception e) {
			viewService.addLogin(model);
		}
		model.addObject("material", matterService.findById(id));
		model.setViewName("MTMS/materialApprovel");
		return model;
	}

	@RequestMapping(value = "/mtms/material", params = "expiredList", method = RequestMethod.GET)
	public ModelAndView materialExpired(ModelAndView model, Principal principal) {
		try {
			principal.getName();
			viewService.addMenuAndName(model, principal);
		} catch (Exception e) {
			viewService.addLogin(model);
		}
		model.addObject("materialExpired", matterService.findExpiredList());
		model.setViewName("MTMS/expiredList");
		return model;
	}

	@RequestMapping(value = "/mtms/materialSapPrivate/{id}", params = "form", method = RequestMethod.GET)
	public ModelAndView createSap(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
		viewService.addMenuAndName(model, principal);
		model.addObject("materialType", materialTypeService.findById(id));
		model.setViewName("MTMS/createSap");
		return model;
	}

}
