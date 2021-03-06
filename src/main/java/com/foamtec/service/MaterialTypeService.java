package com.foamtec.service;

import com.foamtec.dao.MaterialTypeDao;
import com.foamtec.domain.AppUser;
import com.foamtec.domain.DocumentHistory;
import com.foamtec.domain.MaterialType;
import com.foamtec.domain.Matter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
 * Created by apichat on 4/20/2016 AD.
 */
@Service
public class MaterialTypeService {

    private final Logger LOGGER = LoggerFactory.getLogger(MaterialTypeService.class);

    @Autowired
    private MaterialTypeDao materialTypeDao;

    @Autowired
    private AppUserService appUserService;

    public MaterialType create(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        AppUser appUser = appUserService.findByUsername(principal.getName());
        MaterialType materialType = new MaterialType();
        materialType.setCreateDate(new Date());
        materialType.setCreateBy(appUser);
        materialType.setTypeName(jsonObject.getString("inputMaterialType"));
        materialTypeDao.create(materialType);
        return materialType;
    }

    public MaterialType update(String data, Principal principal) {
        JSONObject jsonObject = new JSONObject(data);
        AppUser appUser = appUserService.findByUsername(principal.getName());
        MaterialType materialType = materialTypeDao.getById(jsonObject.getLong("inputId"));
        materialType.setUpdateDate(new Date());
        materialType.setCreateBy(appUser);
        materialType.setTypeName(jsonObject.getString("inputMaterialType"));
        materialTypeDao.create(materialType);
        return materialType;
    }

    public void delete(String data) {
        JSONObject jsonObject = new JSONObject(data);
        MaterialType materialType = findById(jsonObject.getLong("inputId"));
        materialTypeDao.delete(materialType);
    }

    public void createMaterial(MultipartHttpServletRequest multipartHttpServletRequest, Principal principal) throws IOException, ParseException {
        MultipartFile spec = multipartHttpServletRequest.getFile("inputSpec");
        MultipartFile rohs = multipartHttpServletRequest.getFile("inputRoHs");
        MultipartFile msds = multipartHttpServletRequest.getFile("inputMSDS");
        MultipartFile halogen = multipartHttpServletRequest.getFile("inputHalogen");

        String dateRohs = multipartHttpServletRequest.getParameter("inputDateRoHs");
        String dateHalogen = multipartHttpServletRequest.getParameter("inputDateHF");

        MaterialType materialType = materialTypeDao.getById(Long.parseLong(multipartHttpServletRequest.getParameter("id")));

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        Matter matter = new Matter();

        matter.setMaterialName(multipartHttpServletRequest.getParameter("inputMaterialName"));
        matter.setUlNumber(multipartHttpServletRequest.getParameter("inputUlNumber"));
        matter.setManufacturing(multipartHttpServletRequest.getParameter("inputManufacturing"));

        if(spec != null) {
            matter.setSpec(spec.getBytes());
        }
        if(rohs != null) {
            matter.setRohs(rohs.getBytes());
            Date date = df.parse(dateRohs);
            matter.setRohsDateTest(date);
            cal.setTime(date);
            cal.add(Calendar.YEAR, 1);
            matter.setRohsEndDateTest(cal.getTime());
            cal.add(Calendar.MONTH, -3);
            matter.setRohsAlertDateTest(cal.getTime());
        }
        if(msds != null) {
            matter.setMsds(msds.getBytes());
        }
        if(halogen != null) {
            matter.setHalogen(halogen.getBytes());
            Date date = df.parse(dateHalogen);
            matter.setHalogenDateTest(date);
            cal.setTime(date);
            cal.add(Calendar.YEAR, 1);
            matter.setHalogenEndDateTest(cal.getTime());
            cal.add(Calendar.MONTH, -3);
            matter.setHalogenAlertDateTest(cal.getTime());
        }

        AppUser appUser = appUserService.findByUsername(principal.getName());

        matter.setCreateBy(appUser);
        matter.setCreateDate(new Date());
        matter.setStatus("CREATE");
        matter.setFolw("qa");

        Set<DocumentHistory> documentHistories = new HashSet<DocumentHistory>();
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setCreateBy(appUser);
        documentHistory.setCreateDate(new Date());
        documentHistory.setActionType("CREATE");
        documentHistory.setRemark("create material");
        documentHistory.setStatus("CREATE");
        documentHistory.setMatter(matter);
        documentHistories.add(documentHistory);

        matter.setDocumentHistorys(documentHistories);
        matter.setMaterialType(materialType);

        Set<Matter> matters = materialType.getMatters();

        matters.add(matter);

        materialType.setMatters(matters);

        materialTypeDao.update(materialType);
    }

    public void createMaterialFileUrlPath(MultipartHttpServletRequest multipartHttpServletRequest, Principal principal, HttpServletRequest request) throws IOException, ParseException {
        MultipartFile spec = multipartHttpServletRequest.getFile("inputSpec");
        MultipartFile rohs = multipartHttpServletRequest.getFile("inputRoHs");
        MultipartFile msds = multipartHttpServletRequest.getFile("inputMSDS");
        MultipartFile halogen = multipartHttpServletRequest.getFile("inputHalogen");
        MultipartFile guaranteeLetter = multipartHttpServletRequest.getFile("inputGuarantee");
        MultipartFile redPhosphorus = multipartHttpServletRequest.getFile("inputRedPhosphorus");

        String dateRohs = multipartHttpServletRequest.getParameter("inputDateRoHs");
        String dateHalogen = multipartHttpServletRequest.getParameter("inputDateHF");

        MaterialType materialType = materialTypeDao.getById(Long.parseLong(multipartHttpServletRequest.getParameter("id")));

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        Matter matter = new Matter();

        matter.setMaterialName(multipartHttpServletRequest.getParameter("inputMaterialName"));
        matter.setUlNumber(multipartHttpServletRequest.getParameter("inputUlNumber"));
        matter.setManufacturing(multipartHttpServletRequest.getParameter("inputManufacturing"));

        String path = request.getRealPath("./resources/filePDF/");

        if(spec != null) {
            String url = "/" +materialType.getTypeName() +"/SPEC/" + matter.getMaterialName() + "/" + matter.getMaterialName() + "_SPEC.pdf";
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            matter.setSpecUrl(url);
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(spec.getBytes());
            fos.close();
        }
        if(rohs != null) {
            String url = "/" +materialType.getTypeName() +"/ROHS/" + matter.getMaterialName() + "/" + matter.getMaterialName() + "_ROHS.pdf";
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
            String url = "/" +materialType.getTypeName() +"/MSDS/" + matter.getMaterialName() + "/" + matter.getMaterialName() + "_MSDS.pdf";
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            matter.setMsdsUrl(url);

            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(msds.getBytes());
            fos.close();
        }
        if(halogen != null) {
            String url = "/" +materialType.getTypeName() +"/HALOGEN/" + matter.getMaterialName() + "/" + matter.getMaterialName() + "_HALOGEN.pdf";
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
            String url = "/" +materialType.getTypeName() +"/guaranteeLetter/" + matter.getMaterialName() + "/" + matter.getMaterialName() + "_guaranteeLetter.pdf";
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            matter.setGuaranteeLetterUrl(url);

            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(guaranteeLetter.getBytes());
            fos.close();
        }

        if(redPhosphorus != null) {
            String url = "/" +materialType.getTypeName() +"/redPhosphorus/" + matter.getMaterialName() + "/" + matter.getMaterialName() + "_redPhosphorus.pdf";
            File convFile = new File(path + url);
            convFile.getParentFile().mkdirs();
            matter.setRedPhosphorusUrl(url);

            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(redPhosphorus.getBytes());
            fos.close();
        }

        AppUser appUser = appUserService.findByUsername(principal.getName());

        matter.setCreateBy(appUser);
        matter.setCreateDate(new Date());

        Set<DocumentHistory> documentHistories = new HashSet<DocumentHistory>();
        DocumentHistory documentHistory = new DocumentHistory();

        if(spec == null || rohs == null || msds == null ) {
            matter.setStatus("REQUESTDOC");
            matter.setFolw("CREATOR");
            documentHistory.setRemark("Request Document");
        } else {
            matter.setStatus("CREATE");
            matter.setFolw("qa");
            documentHistory.setRemark("create material");
        }

        documentHistory.setCreateBy(appUser);
        documentHistory.setCreateDate(new Date());
        documentHistory.setActionType("CREATE");
        documentHistory.setStatus("CREATE");
        documentHistory.setMatter(matter);
        documentHistories.add(documentHistory);

        matter.setDocumentHistorys(documentHistories);
        matter.setMaterialType(materialType);

        Set<Matter> matters = materialType.getMatters();

        matters.add(matter);

        materialType.setMatters(matters);

        materialTypeDao.update(materialType);
    }

    public List<MaterialType> findAll() {
        return materialTypeDao.getAll();
    }

    public MaterialType findByTypeName(String typeName) {
        return materialTypeDao.findByTypeName(typeName);
    }

    public MaterialType findById(Long id) {
        return materialTypeDao.getById(id);
    }

}
