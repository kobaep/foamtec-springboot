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
        model.addObject("faRequestEngineerSendFirst", faRequestService.findByStatus("engSendFirst"));
        model.addObject("faRequestQaApproveFirst", faRequestService.findByStatus("qaApproveFirstShot"));
        model.addObject("faRequestQaRejectFirst", faRequestService.findByStatus("qaRejectFirstShot"));
        model.addObject("faRequestEngineerSendFinal", faRequestService.findByStatus("engSendFinal"));
        model.addObject("faRequestQaApproveFinal", faRequestService.findByStatus("qaApproveFinal"));
        model.addObject("faRequestQaRejectFinal", faRequestService.findByStatus("qaRejectFinal"));
        model.addObject("faRequestSaleCoSendItem", faRequestService.findByStatus("saleCoSendItem"));
        model.addObject("faRequestQaEngApproveDocument", faRequestService.findByStatus("documentApprove"));
        model.addObject("faRequestQaEngRejectDocument", faRequestService.findByStatus("documentReject"));
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

    @RequestMapping(value = "/fams/showEngApprove/{id}", params = "detail", method = RequestMethod.GET)
    public ModelAndView showEngApprove(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/showEngApprove");
        return model;
    }

    @RequestMapping(value = "/fams/showQaFirst/{id}", params = "detail", method = RequestMethod.GET)
    public ModelAndView showQaFirst(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/showQaFirst");
        return model;
    }

    @RequestMapping(value = "/fams/showSaleCo/{id}", params = "detail", method = RequestMethod.GET)
    public ModelAndView showSaleCo(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/showSaleCo");
        return model;
    }

    @RequestMapping(value = "/fams/saleSummaryFree/{id}", method = RequestMethod.GET)
    public ModelAndView saleSummaryFree(@PathVariable("id") int m, ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        viewService.addViewSaleSummaryFree(model, m);
        model.setViewName("FAMS/saleSummaryFree");
        return model;
    }

    @RequestMapping(value = "/fams/saleSummarySell/{id}", method = RequestMethod.GET)
    public ModelAndView saleSummarySell(@PathVariable("id") int m, ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        viewService.addViewSaleSummarySell(model, m);
        model.setViewName("FAMS/saleSummarySell");
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
        map.put("partName",faRequest.getPartNo());
        map.put("revision", faRequest.getRevision());
        map.put("requestBy", faRequest.getCreateBy().getName());
        map.put("saleOut", faRequest.getSaleOut());
        map.put("customer", faRequest.getCustomer());
        map.put("qwsNo", faRequest.getQwsNo());
        map.put("apqpNo", faRequest.getApqpNo());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        map.put("needDate", df.format(faRequest.getNeedDate()));
        map.put("requestDate", df.format(faRequest.getCreateDate()));
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
        map.put("cuttingType", faRequest.getCuttingType());
        map.put("process", faRequest.getProcess());
        map.put("typeOfRequest", faRequest.getTypeRequest());
        map.put("reSubmitDetail", faRequest.getReSubmitDetail());
        map.put("commitDate", df.format(faRequest.getEngCommitDate()));
        map.put("engReview", faRequest.getUpdateBy().getName());
        map.put("engCommitDate", df.format(faRequest.getUpdateDate()));
        JasperPrint jasperPrint = JasperFillManager.fillReport(fileName, map, ds);
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=" + faRequest.getFaNumber() +".pdf");
        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }

    @RequestMapping(value = "/fams/engineerPrivate", params = "engineerView", method = RequestMethod.GET)
    public ModelAndView engineerView(ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequestSaleCreates", faRequestService.findByStatus("saleCreate", "saleUpdate"));
        model.addObject("faRequestEngineerApproves", faRequestService.findByStatus("engApprove"));
        model.addObject("faRequestQaApproveFirst", faRequestService.findByStatus("qaApproveFirstShot"));
        model.addObject("faRequestQaRejectFirst", faRequestService.findByStatus("qaRejectFirstShot"));
        model.addObject("faRequestQaRejectFinal", faRequestService.findByStatus("qaRejectFinal"));
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

    @RequestMapping(value = "/fams/engineerPrivate/{id}", params = "sendItemFinal", method = RequestMethod.GET)
    public ModelAndView sendItemFinal(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/sendItemFinal");
        return model;
    }

    @RequestMapping(value = "/fams/engineerPrivate/{id}", params = "sendItemFirst", method = RequestMethod.GET)
    public ModelAndView engineerSendItemFirst(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/engineerSendItemFirst");
        return model;
    }

    @RequestMapping(value = "/fams/requestPrivate", params = "updateList", method = RequestMethod.GET)
    public ModelAndView updateListSale(ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequests", faRequestService.findByName(principal.getName()));
        model.setViewName("FAMS/updateListSale");
        return model;
    }

    @RequestMapping(value = "/fams/requestPrivate", params = "updateListSaleOut", method = RequestMethod.GET)
    public ModelAndView updateListSaleOut(ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequests", faRequestService.findByNameAndStatus(principal.getName(), "saleCoSendItem"));
        model.setViewName("FAMS/updateListSaleOut");
        return model;
    }

    @RequestMapping(value = "/fams/search", params = "search", method = RequestMethod.GET)
    public ModelAndView listCustomerResult(ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        model.setViewName("FAMS/listCustomerResult");
        return model;
    }

    @RequestMapping(value = "/fams/search", params = "faSummary", method = RequestMethod.GET)
    public ModelAndView faSummary(ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        viewService.addGraphFa(model);
        model.setViewName("FAMS/faSummary");
        return model;
    }

    @RequestMapping(value = "/fams/search", params = "saleSummary", method = RequestMethod.GET)
    public ModelAndView saleSummary(ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        viewService.addGraphSale(model);
        model.setViewName("FAMS/saleSummary");
        return model;
    }

    @RequestMapping(value = "/fams/requestPrivate/{id}", params = "update", method = RequestMethod.GET)
    public ModelAndView saleUpdate(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/saleUpdate");
        return model;
    }

    @RequestMapping(value = "/fams/requestPrivate/{id}", params = "updateCustomer", method = RequestMethod.GET)
    public ModelAndView saleUpdateCustomer(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/updateCustomer");
        return model;
    }

    @RequestMapping(value = "/fams/requestPrivate/{id}", params = "saleOutFollow", method = RequestMethod.GET)
    public ModelAndView saleOutFollow(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/saleOutFollow");
        return model;
    }

    @RequestMapping(value = "/fams/qaPrivate", params = "qaView", method = RequestMethod.GET)
    public ModelAndView qaView(ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequestEngineerSendFirst", faRequestService.findByStatus("engSendFirst"));
        model.addObject("faRequestEngineerSendFinal", faRequestService.findByStatus("engSendFinal"));
        model.addObject("faRequestQaEngRejectDocument", faRequestService.findByStatus("documentReject"));
        model.setViewName("FAMS/qaView");
        return model;
    }

    @RequestMapping(value = "/fams/qaPrivate/{id}", params = "reviewFirst", method = RequestMethod.GET)
    public ModelAndView qaReviewFirst(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/reviewFirst");
        return model;
    }

    @RequestMapping(value = "/fams/qaPrivate/{id}", params = "reviewFinal", method = RequestMethod.GET)
    public ModelAndView qaReviewFinal(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/reviewFinal");
        return model;
    }

    @RequestMapping(value = "/fams/qaPrivate", params = "reviewDocument", method = RequestMethod.GET)
    public ModelAndView reviewDocumentList(ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequestQaApproveFinal", faRequestService.findByStatus("qaApproveFinal"));
        model.setViewName("FAMS/reviewDocumentList");
        return model;
    }

    @RequestMapping(value = "/fams/showQaApproveFinal/{id}", params = "detail", method = RequestMethod.GET)
    public ModelAndView showQaApproveFinal(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        try {
            principal.getName();
            viewService.addMenuAndName(model, principal);
        } catch (Exception e) {
            viewService.addLogin(model);
        }
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/showQaApproveFinal");
        return model;
    }

    @RequestMapping(value = "/fams/qaPrivate/{id}", params = "reviewDocument", method = RequestMethod.GET)
    public ModelAndView reviewDocument(@PathVariable("id") Long id, ModelAndView model, Principal principal) {
        viewService.addMenuAndName(model, principal);
        model.addObject("faRequest", faRequestService.findById(id));
        model.setViewName("FAMS/reviewDocument");
        return model;
    }
}
