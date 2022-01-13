/**
 * PROJECT : ${Wallet}
 * PRODUCT : ${Com.demo.wallet}
 * **************************************************************
 * Copyright(C) 2018 Fortunaglobal (PRIVATE) LIMITED
 * All rights reserved.
 * **************************************************************
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF
 * Fortunaglobal(PRIVATE) LIMITED.
 * **************************************************************
 * This copy of the Source Code is intended for Fortunaglobal (PRIVATE) LIMITED 's internal use only
 * and is
 * intended for view by persons duly authorized by the management of Fortunaglobal (PRIVATE)
 * LIMITED. No
 * part of this file may be reproduced or distributed in any form or by any
 * means without the written approval of the Management of Fortunaglobal (PRIVATE) LIMITED.
 * **************************************************************
 * **************************************************************
 * Author : P.K.M Madusha
 * Date : ${2018/08} 10:25 AM
 * Since : version 1.0
 * Description :
 * ***************
 **/
package com.demo.coinbase.dao.controller;

import com.demo.coinbase.dto.requests.ActivateRequest;
import com.demo.coinbase.dto.requests.LoginRequest;
import com.demo.coinbase.dto.responses.ActivateResponse;
import com.demo.coinbase.dto.responses.LoginResponse;
import com.demo.coinbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author P.K.M Madusha
 */

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

//    login user
    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public LoginResponse generate(@Valid @RequestBody final LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
// Activate user
    @RequestMapping(method = RequestMethod.POST, path = "/activate")
    public ActivateResponse generate(@Valid @RequestBody final ActivateRequest activateRequest) {
        return userService.activate(activateRequest);
    }
}
