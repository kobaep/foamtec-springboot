package com.foamtec.service;

import com.foamtec.dao.AppUserDao;
import com.foamtec.dao.MaterialTypeDao;
import com.foamtec.dao.MatterDao;
import com.foamtec.domain.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by apichat on 4/21/2016 AD.
 */
@Service
public class MatterService {

    @Autowired
    private MatterDao matterDao;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private FileBackupService fileBackupService;

    public Matter findById(Long id) {
        return matterDao.getById(id);
    }

    public List<Matter> findAll() {
        return matterDao.getAll();
    }

    public List<Matter> findByStatus(String status1, String status2) {
        return matterDao.findByStatus(status1, status2);
    }

    public List<Matter> findByStatus(String status) {
        return matterDao.findByStatus(status);
    }

    public void updateMaterialFileUrlPath(MultipartHttpServletRequest multipartHttpServletRequest, Principal principal, HttpServletRequest request) throws IOException, ParseException {
        MultipartFile spec = multipartHttpServletRequest.getFile("inputSpec");
        MultipartFile rohs = multipartHttpServletRequest.getFile("inputRoHs");
        MultipartFile msds = multipartHttpServletRequest.getFile("inputMSDS");
        MultipartFile halogen = multipartHttpServletRequest.getFile("inputHalogen");
        MultipartFile guaranteeLetter = multipartHttpServletRequest.getFile("inputGuarantee");
        MultipartFile redPhosphorus = multipartHttpServletRequest.getFile("inputRedPhosphorus");

        String dateRohs = multipartHttpServletRequest.getParameter("inputDateRoHs");
        String dateHalogen = multipartHttpServletRequest.getParameter("inputDateHF");

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        Matter matter = matterDao.getById(Long.parseLong(multipartHttpServletRequest.getParameter("id")));

        matter.setMaterialName(multipartHttpServletRequest.getParameter("inputMaterialName"));
        matter.setUlNumber(multipartHttpServletRequest.getParameter("inputUlNumber"));
        matter.setManufacturing(multipartHttpServletRequest.getParameter("inputManufacturing"));

        String path = request.getRealPath("./resources/filePDF/");

        if(spec != null) {
            String url = "/" + matter.getMaterialType().getTypeName() +"/SPEC/" + matter.getMaterialName() + "/" + matter.getMaterialName() + "_SPEC.pdf";
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            matter.setSpecUrl(url);
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(spec.getBytes());
            fos.close();
        }
        if(rohs != null) {
            String url = "/" + matter.getMaterialType().getTypeName() + "/ROHS/" + matter.getMaterialName() + "/" + matter.getMaterialName() + "_ROHS.pdf";
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            matter.setRohsUrl(url);

            Date date = df.parse(dateRohs);
            matter.setRohsDateTest(date);
            cal.setTime(date);
            cal.add(Calendar.YEAR, 1);
            matter.setRohsEndDateTest(cal.getTime());
            cal.add(Calendar.MONTH, -3);
            matter.setRohsAlertDateTest(cal.getTime());

            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(rohs.getBytes());
            fos.close();
        }
        if(msds != null) {
            String url = "/" + matter.getMaterialType().getTypeName() + "/MSDS/" + matter.getMaterialName() + "/" + matter.getMaterialName() + "_MSDS.pdf";
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            matter.setMsdsUrl(url);

            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(msds.getBytes());
            fos.close();
        }
        if(halogen != null) {
            String url = "/" + matter.getMaterialType().getTypeName() + "/HALOGEN/" + matter.getMaterialName() + "/" + matter.getMaterialName() + "_HALOGEN.pdf";
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            matter.setHalogenUrl(url);

            Date date = df.parse(dateHalogen);
            matter.setHalogenDateTest(date);
            cal.setTime(date);
            cal.add(Calendar.YEAR, 1);
            matter.setHalogenEndDateTest(cal.getTime());
            cal.add(Calendar.MONTH, -3);
            matter.setHalogenAlertDateTest(cal.getTime());

            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(halogen.getBytes());
            fos.close();
        }
        if(guaranteeLetter != null) {
            String url = "/" + matter.getMaterialType().getTypeName() +"/guaranteeLetter/" + matter.getMaterialName() + "/" + matter.getMaterialName() + "_guaranteeLetter.pdf";
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            matter.setGuaranteeLetterUrl(url);

            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(guaranteeLetter.getBytes());
            fos.close();
        }

        if(redPhosphorus != null) {
            String url = "/" + matter.getMaterialType().getTypeName() +"/redPhosphorus/" + matter.getMaterialName() + "/" + matter.getMaterialName() + "_redPhosphorus.pdf";
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            matter.setRedPhosphorusUrl(url);

            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(redPhosphorus.getBytes());
            fos.close();
        }

        AppUser appUser = appUserService.findByUsername(principal.getName());

        Set<DocumentHistory> documentHistories = matter.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();

        if(matter.getSpecUrl() == null || matter.getRohsUrl() == null || matter.getMsdsUrl() == null ) {
            matter.setStatus("REQUESTDOC");
            matter.setFolw("CREATOR");
            documentHistory.setRemark("Request Document");
        } else {
            matter.setStatus("UPDATE");
            matter.setFolw("QA");
            documentHistory.setRemark("update material");
        }

        matter.setCreateBy(appUser);
        matter.setUpdateDate(new Date());

        documentHistory.setCreateBy(appUser);
        documentHistory.setCreateDate(new Date());
        documentHistory.setActionType("UPDATE");
        documentHistory.setStatus("UPDATE");
        documentHistory.setMatter(matter);
        documentHistories.add(documentHistory);

        matter.setDocumentHistorys(documentHistories);

        matterDao.update(matter);
    }

    public void remove(String data) {
        JSONObject jsonObject = new JSONObject(data);
        Matter matter = matterDao.getById(jsonObject.getLong("inputId"));
        matterDao.delete(matter);
    }

    public void approveOrReject(String data,HttpServletRequest request, Principal principal) throws IOException {
        JSONObject jsonObject = new JSONObject(data);
        Matter matter = matterDao.getById(jsonObject.getLong("inputId"));
        String action = jsonObject.getString("action");
        String reason = jsonObject.getString("reason");

        AppUser appUser = appUserService.findByUsername(principal.getName());

        Set<DocumentHistory> documentHistories = matter.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();

        matter.setStatus(action);
        if (action.equals("APPROVE")) {
            matter.setFolw("PUBLIC");
            fileBackupService.createFileBackup(matter, request, documentHistory);
        } else {
            matter.setFolw("CREATOR");
        }
        documentHistory.setActionType(action);
        documentHistory.setStatus(action);
        documentHistory.setRemark(reason);
        documentHistory.setCreateBy(appUser);
        documentHistory.setCreateDate(new Date());
        documentHistory.setMatter(matter);

        documentHistories.add(documentHistory);
        matter.setDocumentHistorys(documentHistories);
        matterDao.update(matter);
    }

    public List<Matter> findAllMaterialGe(Date date) {
        return matterDao.findAllMaterialGe(date);
    }

    public List<Matter> findExpiredList() {

        List<Matter> matters = findAllMaterialGe(new Date());
        List<Matter> mattersOut = new ArrayList<Matter>();
        for (Matter m : matters) {
            Calendar calcu = Calendar.getInstance();
            calcu.setTime(new Date());

            if (m.getRohsAlertDateTest() != null) {
                Calendar calex3 = Calendar.getInstance();
                calex3.setTime(m.getRohsAlertDateTest());
                if(calcu.compareTo(calex3) > 0) {
                    m.setRohsUrl(null);
                }
            }

            if (m.getHalogenAlertDateTest() != null) {
                Calendar calex4 = Calendar.getInstance();
                calex4.setTime(m.getHalogenAlertDateTest());
                if(calcu.compareTo(calex4) > 0) {
                    m.setHalogenUrl(null);
                }
            }
            mattersOut.add(m);
        }
        return mattersOut;
    }

    public void createSapCode(String data, Principal principal) {
        AppUser appUser = appUserService.findByUsername(principal.getName());
        JSONObject jsonObject = new JSONObject(data);
        Matter matter = matterDao.getById(jsonObject.getLong("inputMaterialId"));
        Set<MaterialCode> materialCodes = matter.getMaterialCodes();
        MaterialCode materialCode = new MaterialCode();
        materialCode.setCodeName(jsonObject.getString("inputSapCode"));
        materialCode.setCreateDate(new Date());
        materialCode.setMatter(matter);
        materialCode.setUpdateBy(appUser);
        materialCodes.add(materialCode);
        matter.setMaterialCodes(materialCodes);
        matterDao.update(matter);
    }

    public JSONArray getAllJson() {
        // List<Matter> matters = matterDao.findByStatus("APPROVE");
        List<Matter> matters = matterDao.getAll();
        JSONArray jsonArray = new JSONArray();
        for (Matter m : matters) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", m.getMaterialName());
            jsonArray.put(jsonObject);
            Set<MaterialCode> materialCodes = m.getMaterialCodes();
            for (MaterialCode materialCode : materialCodes) {
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("name", materialCode.getCodeName());
                jsonArray.put(jsonObject2);
            }
        }
        return jsonArray;
    }
}
