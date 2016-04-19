package com.foamtec.web;

import static org.hamcrest.Matchers.hasSize;
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
		this.mockMvc.perform(get("/appuser").principal(principal).param("form", ""))
		.andExpect(status().isOk())
		.andExpect(view().name("APPUSER/create"))
		.andExpect(model().attribute("name", notNullValue()))
		.andExpect(model().attribute("logout", "on"))
		.andExpect(model().attribute("roleName", notNullValue()));
	}

	@Test
	public void appUserPageShowListTest() throws Exception {
		this.mockMvc.perform(get("/appuser").principal(principal).param("list", ""))
				.andExpect(status().isOk())
				.andExpect(view().name("APPUSER/list"))
				.andExpect(model().attribute("name", notNullValue()))
				.andExpect(model().attribute("logout", "on"))
				.andExpect(model().attribute("roleName", notNullValue()))
				.andExpect(model().attribute("appUsers", notNullValue()));
	}

	@Test
	public void appUserPageUpdateTest() throws Exception {
		this.mockMvc.perform(get("/appuser/1").principal(principal).param("update", ""))
				.andExpect(status().isOk())
				.andExpect(view().name("APPUSER/update"))
				.andExpect(model().attribute("name", notNullValue()))
				.andExpect(model().attribute("logout", "on"))
				.andExpect(model().attribute("roleName", notNullValue()))
				.andExpect(model().attribute("appUser", notNullValue()));
	}

}
