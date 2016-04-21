package com.foamtec.web;

import com.foamtec.dao.MaterialTypeDao;
import com.foamtec.service.MaterialTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by apichat on 4/20/2016 AD.
 */
@Controller
public class MtmsControllerJson {

    private final Logger LOGGER = LoggerFactory.getLogger(MtmsControllerJson.class);

    @Autowired
    private MaterialTypeService materialTypeService;

    @RequestMapping(value = "/mtms/materialTypePrivate/create", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> materialTypeCreate(@RequestParam("data") String data, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            materialTypeService.create(data, principal);
            return new ResponseEntity<String>("{\"process\":\"success\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"process\":\"fail\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/mtms/materialTypePrivate/update", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> materialTypeUpdate(@RequestParam("data") String data, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            materialTypeService.update(data, principal);
            return new ResponseEntity<String>("{\"process\":\"success\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"process\":\"fail\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/mtms/materialTypePrivate/delete", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> materialTypeDelete(@RequestParam("data") String data, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            materialTypeService.delete(data);
            return new ResponseEntity<String>("{\"process\":\"success\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"process\":\"fail\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
