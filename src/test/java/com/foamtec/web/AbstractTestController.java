package com.foamtec.web;
import java.security.Principal;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.foamtec.SpringbootFoamtecApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootFoamtecApplication.class)
@WebAppConfiguration
public class AbstractTestController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(AbstractTestController.class);
	
	@Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;
    
    protected Principal principal;

    @Before
    public void setup() {
    	principal = new Principal() {
            @Override
            public String getName() {
                return "user";
            }

        };
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        LOGGER.debug("-= test case stat =-");
    }

    @After
    public void after() {
    	LOGGER.debug("-= test case stop =-");
    }
}
