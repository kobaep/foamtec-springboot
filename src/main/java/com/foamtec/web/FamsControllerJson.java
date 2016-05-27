package com.foamtec.web;

import com.foamtec.domain.FaRequest;
import com.foamtec.service.FaRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by apichat on 5/18/2016 AD.
 */
@Controller
public class FamsControllerJson {

    @Autowired
    private FaRequestService faRequestService;

    @RequestMapping(value = "/fams/requestPrivate/create", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> create(@RequestParam("data") String data, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            faRequestService.create(data, principal);
            return new ResponseEntity<String>("{\"process\":\"success\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"process\":\"fail\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/fams/requestPrivate/saleUpdate", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> saleUpdate(@RequestParam("data") String data, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            faRequestService.update(data, principal);
            return new ResponseEntity<String>("{\"process\":\"success\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"process\":\"fail\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/fams/requestPrivate/saleCancel", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> saleCancel(@RequestParam("data") String data, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            faRequestService.cancel(data, principal);
            return new ResponseEntity<String>("{\"process\":\"success\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"process\":\"fail\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/fams/engineerPrivate/approve", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> engineerApprove(@RequestParam("data") String data, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            faRequestService.engineerApprove(data, principal);
            return new ResponseEntity<String>("{\"process\":\"success\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"process\":\"fail\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/fams/engineerPrivate/sendFirst", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> engineerSendFirst(@RequestParam("data") String data, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            faRequestService.engineerSendFirst(data, principal);
            return new ResponseEntity<String>("{\"process\":\"success\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"process\":\"fail\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/fams/engineerPrivate/cancel", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> engineerCancel(@RequestParam("data") String data, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            faRequestService.engineerCancel(data, principal);
            return new ResponseEntity<String>("{\"process\":\"success\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"process\":\"fail\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/fams/engineerPrivate/reject", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> engineerReject(@RequestParam("data") String data, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            faRequestService.engineerReject(data, principal);
            return new ResponseEntity<String>("{\"process\":\"success\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"process\":\"fail\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/fams/qaPrivate/approveFirst", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> qaApproveFirst(@RequestParam("data") String data, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            faRequestService.qaApproveFirst(data, principal);
            return new ResponseEntity<String>("{\"process\":\"success\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"process\":\"fail\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/fams/qaPrivate/rejectFirst", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> qaRejectFirst(@RequestParam("data") String data, Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            faRequestService.qaRejectFirst(data, principal);
            return new ResponseEntity<String>("{\"process\":\"success\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"process\":\"fail\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}