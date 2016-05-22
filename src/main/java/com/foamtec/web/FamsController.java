package com.foamtec.web;

import com.foamtec.domain.FaRequest;
import com.foamtec.service.FaRequestService;
import com.foamtec.service.ViewService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apichat on 5/9/2016 AD.
 */
@Controller
public class FamsController {

    @Autowired
    private ViewService viewService;

    @Autowired
    private FaRequestService faRequestService;

    @RequestMapping(value = "/fams", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        model.addObject("faRequestSaleCreates", faRequestService.findByStatus("saleCreate", "saleUpdate"));
        model.addObject("faRequestEngineerApproves", faRequestService.findByStatus("engApprove"));
        model.addObject("faRequestEngineerRejects", faRequestService.findByStatus("engReject"));
        model.setViewName("FAMS/home");
        return model;
    }

    @RequestMapping(value = "/fams/requestPrivate", params = "form", method = RequestMethod.GET)
    public ModelAndView createMaterialType(ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.setViewName("FAMS/createFa");
        return model;
    }

    @RequestMapping(value = "/fams/show/{id}", params = "detail", method = RequestMethod.GET)
    public ModelAndView showDetail(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/showDetail");
        return model;
    }

    @RequestMapping(value = "/fams/generate/file/{id}", params = "pdf", method = RequestMethod.GET)
    @ResponseBody
    public void generateReport(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRealPath("./resources/core/report/");
        JRCsvDataSource ds = new JRCsvDataSource(new File(path + "/data.csv"));
        ds.setColumnNames(new String[] {"id", "name", "salary"});
        String fileName = path + "/FaRequest.jasper";
        FaRequest faRequest = faRequestService.findById(id);
        Map map = new HashMap();
        map.put("faNumber", faRequest.getFaNumber());
        map.put("productGroup",faRequest.getProductGroup());
        map.put("partNo",faRequest.getPartNo());
        map.put("revision", faRequest.getRevision());
        map.put("requestBy", faRequest.getCreateBy().getName());
        map.put("saleOut", faRequest.getSaleOut());
        map.put("customer", faRequest.getCustomer());
        map.put("qwsNo", faRequest.getQwsNo());
        map.put("apqpNo", faRequest.getApqpNo());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        map.put("needDate", df.format(faRequest.getNeedDate()));
        map.put("faApprove", faRequest.getFaApproveQty() + "");
        map.put("faForSell", faRequest.getFaForSellQty() + "");
        map.put("sampleTest", faRequest.getSamplTestQty() + "");
        map.put("samplePcc", faRequest.getSamplePccQty() + "");
        map.put("mat1", faRequest.getMaterial1());
        map.put("mat2", faRequest.getMaterial2());
        map.put("mat3", faRequest.getMaterial3());
        map.put("mat4", faRequest.getMaterial4());
        map.put("mat5", faRequest.getMaterial5());
        map.put("mat6", faRequest.getMaterial6());
        map.put("documentRequest", faRequest.getDocumentRequest());
        map.put("tool", faRequest.getTool());
        map.put("remark", faRequest.getRemark());
        JasperPrint jasperPrint = JasperFillManager.fillReport(fileName, map, ds);
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=FaRequest.pdf");
        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }

    @RequestMapping(value = "/fams/engineerPrivate", params = "engineerView", method = RequestMethod.GET)
    public ModelAndView engineerView(ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequestSaleCreates", faRequestService.findByStatus("saleCreate", "saleUpdate"));
        model.addObject("faRequestEngineerApproves", faRequestService.findByStatus("engApprove"));
        model.addObject("faRequestEngineerRejects", faRequestService.findByStatus("engReject"));
        model.setViewName("FAMS/engineerView");
        return model;
    }

    @RequestMapping(value = "/fams/engineerPrivate/{id}", params = "review", method = RequestMethod.GET)
    public ModelAndView engineerReview(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/engineerReview");
        return model;
    }
}
