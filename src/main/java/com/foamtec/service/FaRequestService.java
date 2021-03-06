package com.foamtec.service;

import com.foamtec.dao.AppUserDao;
import com.foamtec.dao.DocumentHistoryDao;
import com.foamtec.dao.FaRequestDao;
import com.foamtec.domain.AppUser;
import com.foamtec.domain.DocumentHistory;
import com.foamtec.domain.FaRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by apichat on 5/20/2016 AD.
 */
@Service
public class FaRequestService {

    @Autowired
    private FaRequestDao faRequestDao;

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private DocumentHistoryDao documentHistoryDao;

    public void create(MultipartHttpServletRequest multipartHttpServletRequest, Principal principal) throws IOException {
        JSONObject jsonObject = new JSONObject(multipartHttpServletRequest.getParameter("data"));
        String inputCustomer = jsonObject.getString("inputCustomer");
        String inputProductGroup = jsonObject.getString("inputProductGroup");
        String inputPartNo = jsonObject.getString("inputPartNo");
        String inputRevision = jsonObject.getString("inputRevision");
        String inputSaleOut = jsonObject.getString("inputSaleOut");
        String inputQwsNo = jsonObject.getString("inputQwsNo");
        String inputApqpNo = jsonObject.getString("inputApqpNo");
        String inputNeedDate = jsonObject.getString("inputNeedDate");
        String inputFaApprove = jsonObject.getString("inputFaApprove");
        String inputFaForSell = jsonObject.getString("inputFaForSell");
        String inputSampleTest = jsonObject.getString("inputSampleTest");
        String inputSamplePcc = jsonObject.getString("inputSamplePcc");
        String inputMat1 = jsonObject.getString("inputMat1");
        String inputMat2 = jsonObject.getString("inputMat2");
        String inputMat3 = jsonObject.getString("inputMat3");
        String inputMat4 = jsonObject.getString("inputMat4");
        String inputMat5 = jsonObject.getString("inputMat5");
        String inputMat6 = jsonObject.getString("inputMat6");
        String inputDocumentRequest = jsonObject.getString("inputDocumentRequest");
        String inputTools = jsonObject.getString("inputTools");
        String inputRemark = jsonObject.getString("inputRemark");
        AppUser appUser = appUserDao.getByUsername(principal.getName());

        MultipartFile drawing = multipartHttpServletRequest.getFile("inputFile1");
        MultipartFile other = multipartHttpServletRequest.getFile("inputFile2");

        String path = multipartHttpServletRequest.getRealPath("./resources/filePDFFARequest/");

        FaRequest faRequest = new FaRequest();
        faRequestDao.create(faRequest);
        faRequest.setFaNumber("FA" + String.format("%06d", faRequest.getId()));

        if(drawing != null) {
            String url = "/" + faRequest.getFaNumber() + "/" + drawing.getOriginalFilename();
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            faRequest.setFileDrawing(url);
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(drawing.getBytes());
            fos.close();
        }

        if(other != null) {
            String url = "/" +faRequest.getFaNumber() + "/" + other.getOriginalFilename();
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            faRequest.setFileOther(url);
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(other.getBytes());
            fos.close();
        }

        faRequest.setCreateBy(appUser);
        faRequest.setUpdateBy(appUser);
        faRequest.setCreateDate(new Date());
        faRequest.setUpdateDate(new Date());
        faRequest.setCustomer(inputCustomer);
        faRequest.setProductGroup(inputProductGroup);
        faRequest.setPartNo(inputPartNo);
        faRequest.setRevision(inputRevision);
        faRequest.setSaleOut(inputSaleOut);
        faRequest.setQwsNo(inputQwsNo);
        faRequest.setApqpNo(inputApqpNo);
        faRequest.setNeedDate(convertToDate(inputNeedDate));
        if(inputFaApprove.length() > 0) {
            faRequest.setFaApproveQty(Integer.parseInt(inputFaApprove));
        } else {
            faRequest.setFaApproveQty(0);
        }
        if(inputFaForSell.length() > 0) {
            faRequest.setFaForSellQty(Integer.parseInt(inputFaForSell));
        } else {
            faRequest.setFaForSellQty(0);
        }
        if(inputSampleTest.length() > 0) {
            faRequest.setSamplTestQty(Integer.parseInt(inputSampleTest));
        } else {
            faRequest.setSamplTestQty(0);
        }
        if(inputSamplePcc.length() > 0) {
            faRequest.setSamplePccQty(Integer.parseInt(inputSamplePcc));
        } else {
            faRequest.setSamplePccQty(0);
        }
        faRequest.setMaterial1(inputMat1);
        if(inputMat2.length() > 0) {
            faRequest.setMaterial2(inputMat2);
        }
        if(inputMat3.length() > 0) {
            faRequest.setMaterial3(inputMat3);
        }
        if(inputMat4.length() > 0) {
            faRequest.setMaterial4(inputMat4);
        }
        if(inputMat5.length() > 0) {
            faRequest.setMaterial5(inputMat5);
        }
        if(inputMat6.length() > 0) {
            faRequest.setMaterial6(inputMat6);
        }
        faRequest.setDocumentRequest(inputDocumentRequest);
        faRequest.setTool(inputTools);
        if(inputRemark.length() > 0) {
            faRequest.setRemark(inputRemark);
        }
        faRequest.setStatus("saleCreate");

        Set<DocumentHistory> documentHistories = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setStatus("saleCreate");
        documentHistory.setActionType("create");
        documentHistory.setRemark("Sale create FA request.");
        documentHistory.setFaRequest(faRequest);
        documentHistories.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistories);
        faRequestDao.update(faRequest);
    }

    public void update(MultipartHttpServletRequest multipartHttpServletRequest, Principal principal) throws IOException {
        JSONObject jsonObject = new JSONObject(multipartHttpServletRequest.getParameter("data"));
        Long id = jsonObject.getLong("id");
        String inputCustomer = jsonObject.getString("inputCustomer");
        String inputProductGroup = jsonObject.getString("inputProductGroup");
        String inputPartNo = jsonObject.getString("inputPartNo");
        String inputRevision = jsonObject.getString("inputRevision");
        String inputSaleOut = jsonObject.getString("inputSaleOut");
        String inputQwsNo = jsonObject.getString("inputQwsNo");
        String inputApqpNo = jsonObject.getString("inputApqpNo");
        String inputNeedDate = jsonObject.getString("inputNeedDate");
        String inputFaApprove = jsonObject.getString("inputFaApprove");
        String inputFaForSell = jsonObject.getString("inputFaForSell");
        String inputSampleTest = jsonObject.getString("inputSampleTest");
        String inputSamplePcc = jsonObject.getString("inputSamplePcc");
        String inputMat1 = jsonObject.getString("inputMat1");
        String inputMat2 = jsonObject.getString("inputMat2");
        String inputMat3 = jsonObject.getString("inputMat3");
        String inputMat4 = jsonObject.getString("inputMat4");
        String inputMat5 = jsonObject.getString("inputMat5");
        String inputMat6 = jsonObject.getString("inputMat6");
        String inputDocumentRequest = jsonObject.getString("inputDocumentRequest");
        String inputTools = jsonObject.getString("inputTools");
        String inputRemark = jsonObject.getString("inputRemark");
        AppUser appUser = appUserDao.getByUsername(principal.getName());

        MultipartFile drawing = multipartHttpServletRequest.getFile("inputFile1");
        MultipartFile other = multipartHttpServletRequest.getFile("inputFile2");

        String path = multipartHttpServletRequest.getRealPath("./resources/filePDFFARequest/");

        FaRequest faRequest = faRequestDao.getById(id);

        faRequest.setFileDrawing(null);
        faRequest.setFileOther(null);

        if(drawing != null) {
            String url = "/" + faRequest.getFaNumber() + "/" + drawing.getOriginalFilename();
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            faRequest.setFileDrawing(url);
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(drawing.getBytes());
            fos.close();
        }

        if(other != null) {
            String url = "/" +faRequest.getFaNumber() + "/" + other.getOriginalFilename();
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            faRequest.setFileOther(url);
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(other.getBytes());
            fos.close();
        }

        faRequest.setUpdateBy(appUser);
        faRequest.setUpdateDate(new Date());
        faRequest.setCustomer(inputCustomer);
        faRequest.setProductGroup(inputProductGroup);
        faRequest.setPartNo(inputPartNo);
        faRequest.setRevision(inputRevision);
        faRequest.setSaleOut(inputSaleOut);
        faRequest.setQwsNo(inputQwsNo);
        faRequest.setApqpNo(inputApqpNo);
        faRequest.setNeedDate(convertToDate(inputNeedDate));
        if(inputFaApprove.length() > 0) {
            faRequest.setFaApproveQty(Integer.parseInt(inputFaApprove));
        } else {
            faRequest.setFaApproveQty(0);
        }
        if(inputFaForSell.length() > 0) {
            faRequest.setFaForSellQty(Integer.parseInt(inputFaForSell));
        } else {
            faRequest.setFaForSellQty(0);
        }
        if(inputSampleTest.length() > 0) {
            faRequest.setSamplTestQty(Integer.parseInt(inputSampleTest));
        } else {
            faRequest.setSamplTestQty(0);
        }
        if(inputSamplePcc.length() > 0) {
            faRequest.setSamplePccQty(Integer.parseInt(inputSamplePcc));
        } else {
            faRequest.setSamplePccQty(0);
        }
        faRequest.setMaterial1(inputMat1);
        faRequest.setMaterial2(null);
        if(inputMat2.length() > 0) {
            faRequest.setMaterial2(inputMat2);
        }
        faRequest.setMaterial3(null);
        if(inputMat3.length() > 0) {
            faRequest.setMaterial3(inputMat3);
        }
        faRequest.setMaterial4(null);
        if(inputMat4.length() > 0) {
            faRequest.setMaterial4(inputMat4);
        }
        faRequest.setMaterial5(null);
        if(inputMat5.length() > 0) {
            faRequest.setMaterial5(inputMat5);
        }
        faRequest.setMaterial6(null);
        if(inputMat6.length() > 0) {
            faRequest.setMaterial6(inputMat6);
        }
        faRequest.setDocumentRequest(inputDocumentRequest);
        faRequest.setTool(inputTools);
        if(inputRemark.length() > 0) {
            faRequest.setRemark(inputRemark);
        }
        faRequest.setStatus("saleUpdate");

        Set<DocumentHistory> documentHistories = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setStatus("saleUpdate");
        documentHistory.setActionType("saleUpdate");
        documentHistory.setRemark("Sale update FA request.");
        documentHistory.setFaRequest(faRequest);
        documentHistories.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistories);
        faRequestDao.update(faRequest);
    }

    public void cancel(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        AppUser appUser = appUserDao.getByUsername(principal.getName());

        FaRequest faRequest = faRequestDao.getById(id);

        faRequest.setUpdateBy(appUser);
        faRequest.setUpdateDate(new Date());
        faRequest.setStatus("saleCancel");

        Set<DocumentHistory> documentHistories = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setStatus("saleCancel");
        documentHistory.setActionType("saleCancel");
        documentHistory.setRemark("Sale saleCancel FA request.");
        documentHistory.setFaRequest(faRequest);
        documentHistories.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistories);
        faRequestDao.update(faRequest);
    }

    public Date convertToDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    public List<FaRequest> findByStatus(String status) {
        return faRequestDao.findByStatus(status);
    }

    public List<FaRequest> findByStatus(String status1, String status2) {
        return faRequestDao.findByStatus(status1, status2);
    }

    public FaRequest findById(Long id) {
        return faRequestDao.getById(id);
    }

    public void engineerApprove(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        String action = jsonObject.getString("action");
        String commitDate = jsonObject.getString("inputCommitDate");
        String process = jsonObject.getString("inputProcess");
        String batch1 = jsonObject.getString("inputBatch1");
        String batch2 = jsonObject.getString("inputBatch2");
        String batch3 = jsonObject.getString("inputBatch3");
        String batch4 = jsonObject.getString("inputBatch4");
        String batch5 = jsonObject.getString("inputBatch5");
        String batch6 = jsonObject.getString("inputBatch6");
        AppUser appUser = appUserDao.getByUsername(principal.getName());
        FaRequest faRequest = faRequestDao.getById(id);
        faRequest.setStatus(action);
        faRequest.setEngCommitDate(convertToDate(commitDate));
        faRequest.setProcess(process);
        faRequest.setUpdateDate(new Date());
        faRequest.setUpdateBy(appUser);
        faRequest.setBatch1(batch1);
        if (!batch2.equals("noNo")) {
            faRequest.setBatch2(batch2);
        }
        if (!batch3.equals("noNo")) {
            faRequest.setBatch3(batch3);
        }
        if (!batch4.equals("noNo")) {
            faRequest.setBatch4(batch4);
        }
        if (!batch5.equals("noNo")) {
            faRequest.setBatch5(batch5);
        }
        if (!batch6.equals("noNo")) {
            faRequest.setBatch6(batch6);
        }
        Set<DocumentHistory> documentHistorys = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionType(action);
        documentHistory.setRemark("Engineer review approve");
        documentHistory.setStatus(action);
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setFaRequest(faRequest);
        documentHistorys.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistorys);
        faRequestDao.update(faRequest);
    }

    public void engineerReject(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        String action = jsonObject.getString("action");
        String reason = jsonObject.getString("inputReason");
        AppUser appUser = appUserDao.getByUsername(principal.getName());
        FaRequest faRequest = faRequestDao.getById(id);
        faRequest.setStatus(action);
        faRequest.setUpdateDate(new Date());
        faRequest.setUpdateBy(appUser);
        Set<DocumentHistory> documentHistorys = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionType(action);
        documentHistory.setRemark(reason);
        documentHistory.setStatus(action);
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setFaRequest(faRequest);
        documentHistorys.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistorys);
        faRequestDao.update(faRequest);
    }

    public List<FaRequest> findByName(String name) {
        AppUser appUser = appUserDao.getByUsername(name);
        return faRequestDao.findByName(appUser);
    }

    public List<FaRequest> findByNameAndStatus(String name, String status) {
        AppUser appUser = appUserDao.getByUsername(name);
        return faRequestDao.findByNameAndStatus(appUser.getName(), status);
    }

    public void engineerSendFirst(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        String inputTooling = jsonObject.getString("inputTooling");
        Integer inputQty = jsonObject.getInt("inputQty");
        AppUser appUser = appUserDao.getByUsername(principal.getName());
        FaRequest faRequest = faRequestDao.getById(id);
        faRequest.setMethodFirst(inputTooling);
        faRequest.setQtyFirst(inputQty);
        faRequest.setStatus("engSendFirst");
        faRequest.setUpdateDate(new Date());
        faRequest.setUpdateBy(appUser);
        Set<DocumentHistory> documentHistorys = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionType("engSendFirst");
        documentHistory.setRemark("Engineer send item first shot.");
        documentHistory.setStatus("engSendFirst");
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setMethodFirst(inputTooling);
        documentHistory.setQtyFirst(inputQty);
        documentHistory.setFaRequest(faRequest);
        documentHistorys.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistorys);
        faRequestDao.update(faRequest);
    }

    public void engineerSendFinal(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        String inputTooling = jsonObject.getString("inputTooling");
        Integer inputQty = jsonObject.getInt("inputQty");
        AppUser appUser = appUserDao.getByUsername(principal.getName());
        FaRequest faRequest = faRequestDao.getById(id);
        faRequest.setMethodFirst(inputTooling);
        faRequest.setQtyFirst(inputQty);
        faRequest.setStatus("engSendFinal");
        faRequest.setUpdateDate(new Date());
        faRequest.setUpdateBy(appUser);
        Set<DocumentHistory> documentHistorys = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionType("engSendFinal");
        documentHistory.setRemark("Engineer send item final.");
        documentHistory.setStatus("engSendFinal");
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setMethodFirst(inputTooling);
        documentHistory.setQtyFirst(inputQty);
        documentHistory.setFaRequest(faRequest);
        documentHistorys.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistorys);
        faRequestDao.update(faRequest);
    }

    public void engineerCancel(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        String inputReason = jsonObject.getString("inputReason");
        AppUser appUser = appUserDao.getByUsername(principal.getName());
        FaRequest faRequest = faRequestDao.getById(id);
        faRequest.setStatus("engCancel");
        faRequest.setUpdateDate(new Date());
        faRequest.setUpdateBy(appUser);
        Set<DocumentHistory> documentHistorys = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionType("engCancel");
        documentHistory.setRemark(inputReason);
        documentHistory.setStatus("engCancel");
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setFaRequest(faRequest);
        documentHistorys.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistorys);
        faRequestDao.update(faRequest);
    }

    public void qaApproveFirst(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        AppUser appUser = appUserDao.getByUsername(principal.getName());
        FaRequest faRequest = faRequestDao.getById(id);
        faRequest.setStatus("qaApproveFirstShot");
        faRequest.setUpdateDate(new Date());
        faRequest.setUpdateBy(appUser);
        Set<DocumentHistory> documentHistorys = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionType("qaApproveFirstShot");
        documentHistory.setRemark("QA approve first shot.");
        documentHistory.setStatus("qaApproveFirstShot");
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setFaRequest(faRequest);
        documentHistorys.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistorys);
        faRequestDao.update(faRequest);
    }

    public void qaRejectFirst(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        String inputReason = jsonObject.getString("inputReason");
        AppUser appUser = appUserDao.getByUsername(principal.getName());
        FaRequest faRequest = faRequestDao.getById(id);
        faRequest.setStatus("qaRejectFirstShot");
        faRequest.setUpdateDate(new Date());
        faRequest.setUpdateBy(appUser);
        Set<DocumentHistory> documentHistorys = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionType("qaRejectFirstShot");
        documentHistory.setRemark(inputReason);
        documentHistory.setStatus("qaRejectFirstShot");
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setFaRequest(faRequest);
        documentHistorys.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistorys);
        faRequestDao.update(faRequest);
    }

    public void qaApproveFinal(MultipartHttpServletRequest multipartHttpServletRequest, Principal principal) throws IOException {
        JSONObject jsonObject = new JSONObject(multipartHttpServletRequest.getParameter("data"));
        Long id = jsonObject.getLong("inputId");
        AppUser appUser = appUserDao.getByUsername(principal.getName());
        FaRequest faRequest = faRequestDao.getById(id);

        MultipartFile data1 = multipartHttpServletRequest.getFile("inputFile1");
        MultipartFile data2 = multipartHttpServletRequest.getFile("inputFile2");

        String path = multipartHttpServletRequest.getRealPath("./resources/filePDFFARequest/");

        faRequest.setFileData1(null);
        faRequest.setFileData2(null);

        if(data1 != null) {
            String url = "/" + faRequest.getFaNumber() + "/" + data1.getOriginalFilename();
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            faRequest.setFileData1(url);
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(data1.getBytes());
            fos.close();
        }

        if(data2 != null) {
            String url = "/" +faRequest.getFaNumber() + "/" + data2.getOriginalFilename();
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            faRequest.setFileData2(url);
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(data2.getBytes());
            fos.close();
        }

        faRequest.setStatus("qaApproveFinal");
        faRequest.setUpdateDate(new Date());
        faRequest.setUpdateBy(appUser);
        Set<DocumentHistory> documentHistorys = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionType("qaApproveFinal");
        documentHistory.setRemark("QA approve final.");
        documentHistory.setStatus("qaApproveFinal");
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setFaRequest(faRequest);
        documentHistorys.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistorys);
        faRequestDao.update(faRequest);
    }

    public void qaRejectFinal(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        String inputReason = jsonObject.getString("inputReason");
        AppUser appUser = appUserDao.getByUsername(principal.getName());
        FaRequest faRequest = faRequestDao.getById(id);
        faRequest.setStatus("qaRejectFinal");
        faRequest.setUpdateDate(new Date());
        faRequest.setUpdateBy(appUser);
        Set<DocumentHistory> documentHistorys = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionType("qaRejectFinal");
        documentHistory.setRemark(inputReason);
        documentHistory.setStatus("qaRejectFinal");
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setFaRequest(faRequest);
        documentHistorys.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistorys);
        faRequestDao.update(faRequest);
    }

    public void saleCoSendItem(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        String contract = jsonObject.getString("inputContract");
        String invoice = jsonObject.getString("inputInvoice");
        AppUser appUser = appUserDao.getByUsername(principal.getName());

        FaRequest faRequest = faRequestDao.getById(id);

        faRequest.setUpdateBy(appUser);
        faRequest.setUpdateDate(new Date());
        faRequest.setSaleCoContract(contract);
        faRequest.setInvoiceNo(invoice);
        faRequest.setStatus("saleCoSendItem");

        Set<DocumentHistory> documentHistories = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setStatus("saleCoSendItem");
        documentHistory.setActionType("saleCoSendItem");
        documentHistory.setRemark("Sale Co send item to customer");
        documentHistory.setFaRequest(faRequest);
        documentHistories.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistories);
        faRequestDao.update(faRequest);
    }

    public void customerApprove(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        AppUser appUser = appUserDao.getByUsername(principal.getName());

        FaRequest faRequest = faRequestDao.getById(id);

        faRequest.setUpdateBy(appUser);
        faRequest.setUpdateDate(new Date());
        faRequest.setStatus("customerApprove");

        Set<DocumentHistory> documentHistories = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setStatus("customerApprove");
        documentHistory.setActionType("customerApprove");
        documentHistory.setRemark("Customer Approve Part");
        documentHistory.setFaRequest(faRequest);
        documentHistories.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistories);
        faRequestDao.update(faRequest);
    }

    public void customerReject(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        String inputReason = jsonObject.getString("inputReason");
        AppUser appUser = appUserDao.getByUsername(principal.getName());

        FaRequest faRequest = faRequestDao.getById(id);

        faRequest.setUpdateBy(appUser);
        faRequest.setUpdateDate(new Date());
        faRequest.setStatus("customerReject");

        Set<DocumentHistory> documentHistories = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setStatus("customerReject");
        documentHistory.setActionType("customerReject");
        documentHistory.setRemark(inputReason);
        documentHistory.setFaRequest(faRequest);
        documentHistories.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistories);
        faRequestDao.update(faRequest);
    }

    public void engApproveDocument(MultipartHttpServletRequest multipartHttpServletRequest, Principal principal) {
        JSONObject jsonObject = new JSONObject(multipartHttpServletRequest.getParameter("data"));
        Long id = jsonObject.getLong("inputId");
        AppUser appUser = appUserDao.getByUsername(principal.getName());
        FaRequest faRequest = faRequestDao.getById(id);
        faRequest.setStatus("documentApprove");
        faRequest.setUpdateDate(new Date());
        faRequest.setUpdateBy(appUser);
        Set<DocumentHistory> documentHistorys = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionType("documentApprove");
        documentHistory.setStatus("documentApprove");
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setFaRequest(faRequest);
        documentHistorys.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistorys);
        faRequestDao.update(faRequest);
    }

    public void engRejectDocument(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Long id = jsonObject.getLong("inputId");
        String inputReason = jsonObject.getString("inputReason");
        AppUser appUser = appUserDao.getByUsername(principal.getName());
        FaRequest faRequest = faRequestDao.getById(id);
        faRequest.setStatus("documentReject");
        faRequest.setUpdateDate(new Date());
        faRequest.setUpdateBy(appUser);
        Set<DocumentHistory> documentHistorys = faRequest.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionType("documentReject");
        documentHistory.setStatus("documentReject");
        documentHistory.setRemark(inputReason);
        documentHistory.setCreateDate(new Date());
        documentHistory.setCreateBy(appUser);
        documentHistory.setFaRequest(faRequest);
        documentHistorys.add(documentHistory);
        faRequest.setDocumentHistorys(documentHistorys);
        faRequestDao.update(faRequest);
    }

    public JSONArray getDataFaSummary() {

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("January", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 0), getDate("end", 0), "qaRejectFirstShot").size());
        jsonObject.put("February", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 1), getDate("end", 1), "qaRejectFirstShot").size());
        jsonObject.put("March", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 2), getDate("end", 2), "qaRejectFirstShot").size());
        jsonObject.put("April", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 3), getDate("end", 3), "qaRejectFirstShot").size());
        jsonObject.put("May", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 4), getDate("end", 4), "qaRejectFirstShot").size());
        jsonObject.put("June", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 5), getDate("end", 5), "qaRejectFirstShot").size());
        jsonObject.put("July", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 6), getDate("end", 6), "qaRejectFirstShot").size());
        jsonObject.put("August", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 7), getDate("end", 7), "qaRejectFirstShot").size());
        jsonObject.put("September", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 8), getDate("end", 8), "qaRejectFirstShot").size());
        jsonObject.put("October", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 9), getDate("end", 9), "qaRejectFirstShot").size());
        jsonObject.put("November", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 10), getDate("end", 10), "qaRejectFirstShot").size());
        jsonObject.put("December", documentHistoryDao.findByStartDateEndDateAndStatus(getDate("start", 11), getDate("end", 11), "qaRejectFirstShot").size());

        jsonArray.put(jsonObject);
        return jsonArray;
    }

    public JSONArray findByStartDateEndDateStatus(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            String startDateSt = jsonObject.getString("startDate");
            String endDateSt = jsonObject.getString("endDate");

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = df.parse(startDateSt);

            Calendar endDateCalendar = Calendar.getInstance();
            endDateCalendar.setTime(df.parse(endDateSt));
            endDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            endDateCalendar.set(Calendar.MINUTE, 59);
            endDateCalendar.set(Calendar.SECOND, 59);
            Date endDate = endDateCalendar.getTime();

            String[] partNumber = jsonObject.getString("status").split("_");
            String statusSearch = "%";
            if(partNumber.length == 2) {
                statusSearch = "%" + partNumber[1] + "%";
            }
            JSONArray dataAllForSend = new JSONArray();
            List<FaRequest> faRequests = faRequestDao.findByStartDateEndDateAndStatusByUser(startDate, endDate, statusSearch);
            int i = 1;
            for(FaRequest faRequest : faRequests) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("id", faRequest.getId());
                jsonObject1.put("no", i);
                jsonObject1.put("faNo", faRequest.getFaNumber());
                jsonObject1.put("customer",faRequest.getCustomer());
                jsonObject1.put("partNo",faRequest.getPartNo());
                jsonObject1.put("status", faRequest.getStatus());
                dataAllForSend.put(jsonObject1);
                i++;
            }
            return dataAllForSend;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
