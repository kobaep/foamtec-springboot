package com.foamtec.web;

import com.foamtec.dao.MaterialTypeDao;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.http.MediaType;

import java.io.File;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
/**
 * Created by apichat on 4/20/2016 AD.
 */
public class MtmsControllerJsonTest extends AbstractTestController {

    private final Logger LOGGER = LoggerFactory.getLogger(MtmsControllerJsonTest.class);

    @Autowired
    private MaterialTypeDao materialTypeDao;

    @Test
    public void materialTypeCreateTest() throws Exception {

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("inputMaterialType", "FOAM");

        this.mockMvc.perform(post("/mtms/materialTypePrivate/create").principal(principal).param("data", jsonObject1.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));
    }

    @Test
    public void materialTypeUpdateTest() throws Exception {

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("inputMaterialType", "GULE");

        this.mockMvc.perform(post("/mtms/materialTypePrivate/create").principal(principal).param("data", jsonObject1.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("inputId", materialTypeDao.findByTypeName("GULE").getId());
        jsonObject2.put("inputMaterialType", "HARJ");

        this.mockMvc.perform(post("/mtms/materialTypePrivate/update").principal(principal).param("data", jsonObject2.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));
    }

    @Test
    public void materialTypeDeleteTest() throws Exception {

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("inputMaterialType", "HAHA");

        this.mockMvc.perform(post("/mtms/materialTypePrivate/create").principal(principal).param("data", jsonObject1.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("inputId", materialTypeDao.findByTypeName("HAHA").getId());

        this.mockMvc.perform(post("/mtms/materialTypePrivate/delete").principal(principal).param("data", jsonObject2.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));
    }
}
