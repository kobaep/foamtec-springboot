package com.foamtec.web;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.foamtec.domain.MaterialType;
import com.foamtec.service.MaterialTypeService;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MtmsControllerTest extends AbstractTestController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(MtmsControllerTest.class);

    @Autowired
    private MaterialTypeService materialTypeService;

	@Test
	public void homeMtmsNonTest() throws Exception {
		this.mockMvc.perform(get("/mtms")).andExpect(status().isOk())
				.andExpect(view().name("MTMS/home"))
				.andExpect(model().attribute("login", "on"));
	}

    @Test
    public void materialWaitApproveListNonLoginTest() throws Exception {
        this.mockMvc.perform(get("/mtms/material").param("waitApproveList", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("MTMS/waitApproveList"))
                .andExpect(model().attribute("materials", notNullValue()))
                .andExpect(model().attribute("login", "on"));
    }

    @Test
    public void materialWaitApproveListOnLoginTest() throws Exception {
        this.mockMvc.perform(get("/mtms/material").param("waitApproveList", "").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("MTMS/waitApproveList"))
                .andExpect(model().attribute("materials", notNullValue()))
                .andExpect(model().attribute("name", notNullValue()))
                .andExpect(model().attribute("logout", "on"))
                .andExpect(model().attribute("roleName", notNullValue()));
    }

	@Test
	public void homeMtmsOnLoginTest() throws Exception {

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("inputMaterialType", "FILM");

        this.mockMvc.perform(post("/mtms/materialTypePrivate/create").principal(principal).param("data", jsonObject1.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));

        this.mockMvc.perform(get("/mtms").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("MTMS/home"))
                .andExpect(model().attribute("name", notNullValue()))
                .andExpect(model().attribute("logout", "on"))
                .andExpect(model().attribute("materialTypes", notNullValue()))
                .andExpect(model().attribute("roleName", notNullValue()));
	}

    @Test
    public void createMaterialTypeTest() throws Exception {
        this.mockMvc.perform(get("/mtms/materialTypePrivate").principal(principal).param("form", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("MTMS/createMaterialType"))
                .andExpect(model().attribute("name", notNullValue()))
                .andExpect(model().attribute("logout", "on"))
                .andExpect(model().attribute("roleName", notNullValue()));
    }

    @Test
    public void updateMaterialTypeListTest() throws Exception {

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("inputMaterialType", "TEST1");

        this.mockMvc.perform(post("/mtms/materialTypePrivate/create").principal(principal).param("data", jsonObject1.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));

        this.mockMvc.perform(get("/mtms/materialTypePrivate/update").principal(principal).param("list",""))
                .andExpect(status().isOk())
                .andExpect(view().name("MTMS/updateMaterialTypeList"))
                .andExpect(model().attribute("name", notNullValue()))
                .andExpect(model().attribute("logout", "on"))
                .andExpect(model().attribute("materialTypes", notNullValue()))
                .andExpect(model().attribute("roleName", notNullValue()));
    }

    @Test
    public void updateMaterialTypeTest() throws Exception {

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("inputMaterialType", "TEST2");

        this.mockMvc.perform(post("/mtms/materialTypePrivate/create").principal(principal).param("data", jsonObject1.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));

        this.mockMvc.perform(get("/mtms/materialTypePrivate/" + materialTypeService.findByTypeName("TEST2").getId()).principal(principal).param("update", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("MTMS/updateMaterialType"))
                .andExpect(model().attribute("name", notNullValue()))
                .andExpect(model().attribute("materialType", notNullValue()))
                .andExpect(model().attribute("logout", "on"))
                .andExpect(model().attribute("roleName", notNullValue()));
    }

    @Test
    public void matterListNonLoginTest() throws Exception {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("inputMaterialType", "TEST3");

        this.mockMvc.perform(post("/mtms/materialTypePrivate/create").principal(principal).param("data", jsonObject1.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));

        this.mockMvc.perform(get("/mtms/material/" + materialTypeService.findByTypeName("TEST3").getId()).param("list", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("MTMS/materialList"))
                .andExpect(model().attribute("materialType", notNullValue()))
                .andExpect(model().attribute("login", "on"));
    }

    @Test
    public void matterListOnLoginTest() throws Exception {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("inputMaterialType", "TEST4");

        this.mockMvc.perform(post("/mtms/materialTypePrivate/create").principal(principal).param("data", jsonObject1.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));

        this.mockMvc.perform(get("/mtms/material/" + materialTypeService.findByTypeName("TEST4").getId()).principal(principal).param("list", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("MTMS/materialList"))
                .andExpect(model().attribute("name", notNullValue()))
                .andExpect(model().attribute("materialType", notNullValue()))
                .andExpect(model().attribute("logout", "on"))
                .andExpect(model().attribute("roleName", notNullValue()));
    }

    @Test
    public void createMaterialTest() throws Exception {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("inputMaterialType", "TEST5");

        this.mockMvc.perform(post("/mtms/materialTypePrivate/create").principal(principal).param("data", jsonObject1.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));

        this.mockMvc.perform(get("/mtms/materialPrivate/" + materialTypeService.findByTypeName("TEST5").getId()).principal(principal).param("form", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("MTMS/createMaterial"))
                .andExpect(model().attribute("name", notNullValue()))
                .andExpect(model().attribute("materialType", notNullValue()))
                .andExpect(model().attribute("logout", "on"))
                .andExpect(model().attribute("roleName", notNullValue()));
    }

    @Test
    public void materialUpdateListNonLoginTest() throws Exception {

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("inputMaterialType", "TEST6");

        this.mockMvc.perform(post("/mtms/materialTypePrivate/create").principal(principal).param("data", jsonObject1.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));

        this.mockMvc.perform(get("/mtms/material/" + materialTypeService.findByTypeName("TEST6").getId()).param("updateList", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("materialType", notNullValue()))
                .andExpect(view().name("MTMS/materialUpdateList"))
                .andExpect(model().attribute("login", "on"));
    }

    @Test
    public void materialUpdateListOnLoginTest() throws Exception {

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("inputMaterialType", "TEST7");

        this.mockMvc.perform(post("/mtms/materialTypePrivate/create").principal(principal).param("data", jsonObject1.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.process", containsString("success")));

        this.mockMvc.perform(get("/mtms/material/" + materialTypeService.findByTypeName("TEST7").getId()).principal(principal).param("updateList", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("MTMS/materialUpdateList"))
                .andExpect(model().attribute("materialType", notNullValue()))
                .andExpect(model().attribute("name", notNullValue()))
                .andExpect(model().attribute("logout", "on"))
                .andExpect(model().attribute("roleName", notNullValue()));
    }

}
