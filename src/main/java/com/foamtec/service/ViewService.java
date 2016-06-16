package com.foamtec.service;

import java.security.Principal;
import java.util.*;

import com.foamtec.dao.DocumentHistoryDao;
import com.foamtec.dao.FaRequestDao;
import com.foamtec.domain.AppUser;
import com.foamtec.domain.FaRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ViewService {

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private DocumentHistoryDao documentHistoryDao;

	@Autowired
	private FaRequestDao faRequestDao;
	
	public void addMenuAndName(ModelAndView model, Principal principal) {
		if(principal.getName().equals("user")) {
			model.addObject("name", principal.getName());
			model.addObject("roleName", "user");
		} else {
			AppUser appUser = appUserService.findByUsername(principal.getName());
			model.addObject("name", appUser.getName());
			model.addObject("roleName", appUser.getRoleName());
		}
		model.addObject("logout", "on");
	}
	
	public void addLogin(ModelAndView model) {
		model.addObject("login", "on");
	}

	public void addGraphFa(ModelAndView model) {

		model.addObject("qaRejectFirstShotJanuary", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 0), getDate("end", 0), "qaRejectFirstShot").size());
		model.addObject("qaRejectFirstShotFebruary", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 1), getDate("end", 1), "qaRejectFirstShot").size());
		model.addObject("qaRejectFirstShotMarch", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 2), getDate("end", 2), "qaRejectFirstShot").size());
		model.addObject("qaRejectFirstShotApril", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 3), getDate("end", 3), "qaRejectFirstShot").size());
		model.addObject("qaRejectFirstShotMay", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 4), getDate("end", 4), "qaRejectFirstShot").size());
		model.addObject("qaRejectFirstShotJune", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 5), getDate("end", 5), "qaRejectFirstShot").size());
		model.addObject("qaRejectFirstShotJuly", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 6), getDate("end", 6), "qaRejectFirstShot").size());
		model.addObject("qaRejectFirstShotAugust", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 7), getDate("end", 7), "qaRejectFirstShot").size());
		model.addObject("qaRejectFirstShotSeptember", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 8), getDate("end", 8), "qaRejectFirstShot").size());
		model.addObject("qaRejectFirstShotOctober", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 9), getDate("end", 9), "qaRejectFirstShot").size());
		model.addObject("qaRejectFirstShotNovember", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 10), getDate("end", 10), "qaRejectFirstShot").size());
		model.addObject("qaRejectFirstShotDecember", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 11), getDate("end", 11), "qaRejectFirstShot").size());

		model.addObject("qaApproveFirstShotJanuary", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 0), getDate("end", 0), "qaApproveFirstShot").size());
		model.addObject("qaApproveFirstShotFebruary", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 1), getDate("end", 1), "qaApproveFirstShot").size());
		model.addObject("qaApproveFirstShotMarch", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 2), getDate("end", 2), "qaApproveFirstShot").size());
		model.addObject("qaApproveFirstShotApril", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 3), getDate("end", 3), "qaApproveFirstShot").size());
		model.addObject("qaApproveFirstShotMay", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 4), getDate("end", 4), "qaApproveFirstShot").size());
		model.addObject("qaApproveFirstShotJune", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 5), getDate("end", 5), "qaApproveFirstShot").size());
		model.addObject("qaApproveFirstShotJuly", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 6), getDate("end", 6), "qaApproveFirstShot").size());
		model.addObject("qaApproveFirstShotAugust", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 7), getDate("end", 7), "qaApproveFirstShot").size());
		model.addObject("qaApproveFirstShotSeptember", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 8), getDate("end", 8), "qaApproveFirstShot").size());
		model.addObject("qaApproveFirstShotOctober", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 9), getDate("end", 9), "qaApproveFirstShot").size());
		model.addObject("qaApproveFirstShotNovember", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 10), getDate("end", 10), "qaApproveFirstShot").size());
		model.addObject("qaApproveFirstShotDecember", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 11), getDate("end", 11), "qaApproveFirstShot").size());

		model.addObject("qaRejectFinalJanuary", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 0), getDate("end", 0), "qaRejectFinal").size());
		model.addObject("qaRejectFinalFebruary", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 1), getDate("end", 1), "qaRejectFinal").size());
		model.addObject("qaRejectFinalMarch", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 2), getDate("end", 2), "qaRejectFinal").size());
		model.addObject("qaRejectFinalApril", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 3), getDate("end", 3), "qaRejectFinal").size());
		model.addObject("qaRejectFinalMay", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 4), getDate("end", 4), "qaRejectFinal").size());
		model.addObject("qaRejectFinalJune", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 5), getDate("end", 5), "qaRejectFinal").size());
		model.addObject("qaRejectFinalJuly", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 6), getDate("end", 6), "qaRejectFinal").size());
		model.addObject("qaRejectFinalAugust", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 7), getDate("end", 7), "qaRejectFinal").size());
		model.addObject("qaRejectFinalSeptember", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 8), getDate("end", 8), "qaRejectFinal").size());
		model.addObject("qaRejectFinalOctober", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 9), getDate("end", 9), "qaRejectFinal").size());
		model.addObject("qaRejectFinalNovember", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 10), getDate("end", 10), "qaRejectFinal").size());
		model.addObject("qaRejectFinalDecember", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 11), getDate("end", 11), "qaRejectFinal").size());

		model.addObject("qaApproveFinalJanuary", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 0), getDate("end", 0), "qaApproveFinal").size());
		model.addObject("qaApproveFinalFebruary", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 1), getDate("end", 1), "qaApproveFinal").size());
		model.addObject("qaApproveFinalMarch", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 2), getDate("end", 2), "qaApproveFinal").size());
		model.addObject("qaApproveFinalApril", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 3), getDate("end", 3), "qaApproveFinal").size());
		model.addObject("qaApproveFinalMay", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 4), getDate("end", 4), "qaApproveFinal").size());
		model.addObject("qaApproveFinalJune", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 5), getDate("end", 5), "qaApproveFinal").size());
		model.addObject("qaApproveFinalJuly", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 6), getDate("end", 6), "qaApproveFinal").size());
		model.addObject("qaApproveFinalAugust", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 7), getDate("end", 7), "qaApproveFinal").size());
		model.addObject("qaApproveFinalSeptember", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 8), getDate("end", 8), "qaApproveFinal").size());
		model.addObject("qaApproveFinalOctober", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 9), getDate("end", 9), "qaApproveFinal").size());
		model.addObject("qaApproveFinalNovember", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 10), getDate("end", 10), "qaApproveFinal").size());
		model.addObject("qaApproveFinalDecember", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 11), getDate("end", 11), "qaApproveFinal").size());

	}

	public void addGraphSale(ModelAndView model) {

		model.addObject("freeJanuary", faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", 0), getDate("end", 0)).size());
		model.addObject("freeFebruary", faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", 1), getDate("end", 1)).size());
		model.addObject("freeMarch", faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", 2), getDate("end", 2)).size());
		model.addObject("freeApril", faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", 3), getDate("end", 3)).size());
		model.addObject("freeMay", faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", 4), getDate("end", 4)).size());
		model.addObject("freeJune", faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", 5), getDate("end", 5)).size());
		model.addObject("freeJuly", faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", 6), getDate("end", 6)).size());
		model.addObject("freeAugust", faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", 7), getDate("end", 7)).size());
		model.addObject("freeSeptember", faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", 8), getDate("end", 8)).size());
		model.addObject("freeOctober", faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", 9), getDate("end", 9)).size());
		model.addObject("freeNovember", faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", 10), getDate("end", 10)).size());
		model.addObject("freeDecember", faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", 11), getDate("end", 11)).size());

		model.addObject("sellJanuary", faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", 0), getDate("end", 0)).size());
		model.addObject("sellFebruary", faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", 1), getDate("end", 1)).size());
		model.addObject("sellMarch", faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", 2), getDate("end", 2)).size());
		model.addObject("sellApril", faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", 3), getDate("end", 3)).size());
		model.addObject("sellMay", faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", 4), getDate("end", 4)).size());
		model.addObject("sellJune", faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", 5), getDate("end", 5)).size());
		model.addObject("sellJuly", faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", 6), getDate("end", 6)).size());
		model.addObject("sellAugust", faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", 7), getDate("end", 7)).size());
		model.addObject("sellSeptember", faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", 8), getDate("end", 8)).size());
		model.addObject("sellOctober", faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", 9), getDate("end", 9)).size());
		model.addObject("sellNovember", faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", 10), getDate("end", 10)).size());
		model.addObject("sellDecember", faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", 11), getDate("end", 11)).size());
	}

	public void addViewSaleSummaryFree(ModelAndView model, int m) {
		List<FaRequest> faRequests = faRequestDao.findByStartDateEndDateAndStatusQtyFree(getDate("start", m), getDate("end", m));
		Set<String> nameSaleOut = new HashSet<String>();
		for(FaRequest f : faRequests) {
			nameSaleOut.add(f.getSaleOut());
		}
		Map<String, List<FaRequest>> map = new HashMap<String, List<FaRequest>>();
		JSONArray jsonArray = new JSONArray();
		for(String s : nameSaleOut) {
			List<FaRequest>  faRequests1 = new ArrayList<FaRequest>();
			JSONObject jsonObject = new JSONObject();
			int i = 0;
			for(FaRequest f : faRequests) {
				if(s.equals(f.getSaleOut())) {
					faRequests1.add(f);
					i++;
				}
			}
			jsonObject.put(s, i);
			jsonArray.put(jsonObject);
			map.put(s, faRequests1);
		}
		model.addObject("saleSells", map);
		model.addObject("saleSellsJSON", jsonArray.toString());

		Set<String> nameCustomer = new HashSet<String>();
		for(FaRequest f : faRequests) {
			nameCustomer.add(f.getCustomer());
		}
		Map<String, List<FaRequest>> mapCustomers = new HashMap<String, List<FaRequest>>();
		JSONArray jsonArray2 = new JSONArray();
		for(String s : nameCustomer) {
			List<FaRequest>  faRequests2 = new ArrayList<FaRequest>();
			JSONObject jsonObject = new JSONObject();
			int i = 0;
			for(FaRequest f : faRequests) {
				if(s.equals(f.getCustomer())) {
					faRequests2.add(f);
					i++;
				}
			}
			jsonObject.put(s, i);
			jsonArray2.put(jsonObject);
			mapCustomers.put(s, faRequests2);
		}
		model.addObject("saleSellCustomers", mapCustomers);
		model.addObject("saleCustomersJSON", jsonArray2.toString());
	}

	public void addViewSaleSummarySell(ModelAndView model, int m) {
		List<FaRequest> faRequests = faRequestDao.findByStartDateEndDateAndStatusQtySell(getDate("start", m), getDate("end", m));
		Set<String> nameSaleOut = new HashSet<String>();
		for(FaRequest f : faRequests) {
			nameSaleOut.add(f.getSaleOut());
		}
		Map<String, List<FaRequest>> map = new HashMap<String, List<FaRequest>>();
		JSONArray jsonArray = new JSONArray();
		for(String s : nameSaleOut) {
			List<FaRequest>  faRequests1 = new ArrayList<FaRequest>();
			JSONObject jsonObject = new JSONObject();
			int i = 0;
			for(FaRequest f : faRequests) {
				if(s.equals(f.getSaleOut())) {
					faRequests1.add(f);
					i++;
				}
			}
			jsonObject.put(s, i);
			jsonArray.put(jsonObject);
			map.put(s, faRequests1);
		}
		model.addObject("saleSells", map);
		model.addObject("saleSellsJSON", jsonArray.toString());

		Set<String> nameCustomer = new HashSet<String>();
		for(FaRequest f : faRequests) {
			nameCustomer.add(f.getCustomer());
		}
		Map<String, List<FaRequest>> mapCustomers = new HashMap<String, List<FaRequest>>();
		JSONArray jsonArray2 = new JSONArray();
		for(String s : nameCustomer) {
			List<FaRequest>  faRequests2 = new ArrayList<FaRequest>();
			JSONObject jsonObject = new JSONObject();
			int i = 0;
			for(FaRequest f : faRequests) {
				if(s.equals(f.getCustomer())) {
					faRequests2.add(f);
					i++;
				}
			}
			jsonObject.put(s, i);
			jsonArray2.put(jsonObject);
			mapCustomers.put(s, faRequests2);
		}
		model.addObject("saleSellCustomers", mapCustomers);
		model.addObject("saleCustomersJSON", jsonArray2.toString());
	}

	public Date getDate(String month, int m) {

		Calendar calCurrent = Calendar.getInstance();
		calCurrent.setTime(new Date());
		int year = calCurrent.get(Calendar.YEAR);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);

		if(month.equals("start")) {
			cal.set(Calendar.MONTH, m);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			return cal.getTime();
		} else if(month.equals("end")) {
			cal.set(Calendar.MONTH, m);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			return cal.getTime();
		} else {
			return null;
		}
	}
}
