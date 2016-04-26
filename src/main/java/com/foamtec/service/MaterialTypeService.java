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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by apichat on 4/20/2016 AD.
 */
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
