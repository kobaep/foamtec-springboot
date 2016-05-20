package com.foamtec.service;

import com.foamtec.dao.AppUserDao;
import com.foamtec.dao.FaRequestDao;
import com.foamtec.domain.AppUser;
import com.foamtec.domain.DocumentHistory;
import com.foamtec.domain.FaRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public void create(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
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

        FaRequest faRequest = new FaRequest();
        faRequest.setCreateBy(appUser);
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
        }
        if(inputFaForSell.length() > 0) {
            faRequest.setFaForSellQty(Integer.parseInt(inputFaForSell));
        }
        if(inputSampleTest.length() > 0) {
            faRequest.setSamplTestQty(Integer.parseInt(inputSampleTest));
        }
        if(inputSamplePcc.length() > 0) {
            faRequest.setSamplePccQty(Integer.parseInt(inputSamplePcc));
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
        faRequestDao.create(faRequest);
        faRequest.setFaNumber("FA" + String.format("%07d", faRequest.getId()));
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
}
