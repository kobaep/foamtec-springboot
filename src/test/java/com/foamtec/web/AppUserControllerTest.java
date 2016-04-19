package com.foamtec.web;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppUserControllerTest extends AbstractTestController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(AppUserControllerTest.class);
	
	@Test
	public void appUserPageCreateTest() throws Exception {
		this.mockMvc.perform(get("/appuser").principal(principal).param("form", "create"))
		.andExpect(status().isOk())
		.andExpect(view().name("APPUSER/create"))
		.andExpect(model().attribute("name", notNullValue()))
		.andExpect(model().attribute("logout", "on"))
		.andExpect(model().attribute("createUser", "on"))
		.andExpect(model().attribute("roleName", notNullValue()));
	}

}
