package com.foamtec.web;
import java.security.Principal;

import com.foamtec.SpringbootFoamtecApplication;
import com.foamtec.dao.AppUserDao;
import com.foamtec.domain.AppUser;
import com.foamtec.service.AppUserService;
import org.json.JSONObject;
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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootFoamtecApplication.class)
@WebAppConfiguration
public class AbstractTestController {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractTestController.class);

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private AppUserDao appUserDao;

    protected MockMvc mockMvc;

    protected Principal principal;

    @Before
    public void setup() throws Exception {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//        AppUser appUser = new AppUser();
//        appUser.setUsername("user");
//        appUser.setPassword("password");
//        appUser.setName("Apichat Eakwongsa");
//        appUser.setDepartment("qa");
//        appUser.setDepartmentCode("qa000");
//        appUser.setEmailAddress("apichat.kop@gmail.com");
//        appUser.setPhoneNumber("0800103329");
//        appUser.setRoleName("admin");
//
//        appUserDao.create(appUser);

        principal = new Principal() {
            @Override
            public String getName() {
                return "user";
            }

        };

        LOGGER.debug("-= test case stat =-");
    }

    @After
    public void after() {
        LOGGER.debug("-= test case stop =-");
    }
}
