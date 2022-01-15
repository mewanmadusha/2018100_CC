package com.demo.coinbase.dao.controller;

import com.demo.coinbase.dto.requests.AccountRequest;
import com.demo.coinbase.dto.responses.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class HelathController {

    @RequestMapping(method = RequestMethod.GET, path = "/health")
    public String helth() {
        return "This is coinbase API : Latest";
    }
}
