package com.foamtec.service;

import com.foamtec.dao.MaterialTypeDao;
import com.foamtec.dao.MatterDao;
import com.foamtec.domain.AppUser;
import com.foamtec.domain.DocumentHistory;
import com.foamtec.domain.MaterialType;
import com.foamtec.domain.Matter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
public class MatterService {

    @Autowired
    private MatterDao matterDao;

    @Autowired
    private AppUserService appUserService;

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

        AppUser appUser = appUserService.findByUsername(principal.getName());

        matter.setCreateBy(appUser);
        matter.setUpdateDate(new Date());
        matter.setStatus("UPDATE");
        matter.setFolw("QA");

        Set<DocumentHistory> documentHistories = matter.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setCreateBy(appUser);
        documentHistory.setCreateDate(new Date());
        documentHistory.setActionType("UPDATE");
        documentHistory.setRemark("update material");
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

    public void approveOrReject(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        Matter matter = matterDao.getById(jsonObject.getLong("inputId"));
        String action = jsonObject.getString("action");
        String reason = jsonObject.getString("reason");

        AppUser appUser = appUserService.findByUsername(principal.getName());

        matter.setStatus(action);
        if (action.equals("APPROVE")) {
            matter.setFolw("PUBLIC");
        } else {
            matter.setFolw("CREATOR");
        }
        Set<DocumentHistory> documentHistories = matter.getDocumentHistorys();
        DocumentHistory documentHistory = new DocumentHistory();
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

}
