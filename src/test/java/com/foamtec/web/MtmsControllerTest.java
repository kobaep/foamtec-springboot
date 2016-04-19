package com.foamtec.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MtmsControllerTest extends AbstractTestController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(MtmsControllerTest.class);

	@Test
	public void homeMtmsTest() throws Exception {
		LOGGER.debug("-= homeMtmsTest =-");
		this.mockMvc.perform(get("/mtms")).andExpect(status().isOk())
		.andExpect(view().name("MTMS/home"));
	}
}
