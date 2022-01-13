/**
 * PROJECT : ${Wallet}
 * PRODUCT : ${com.demo.wallet}
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
 * Date : ${2018/08} 1:23 PM
 * Since : version 1.0
 * Description :
 * ***************
 **/
package com.demo.coinbase.service.impl;

import com.demo.coinbase.dao.model.Account;
import com.demo.coinbase.dao.model.User;
import com.demo.coinbase.dao.repository.AccountRepository;
import com.demo.coinbase.dao.repository.UserRepository;
import com.demo.coinbase.dto.requests.AccountRequest;
import com.demo.coinbase.dto.responses.Response;
import com.demo.coinbase.enums.AccountType;
import com.demo.coinbase.enums.Responses;
import com.demo.coinbase.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 *@Author P.K.M Madusha
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Response linkaccount(AccountRequest accountRequest) {


        User founduser =userRepository.findByMobileNo(accountRequest.getMobileNo());
        Account account=accountRepository.findByAccountNumber(accountRequest.getAccountNo());

        if (founduser ==null){
            logger.error("Couldn't determine user: " + accountRequest.getMobileNo());
            return new Response( false, Responses.LINKACC_UNSUCCESS_1.name(), Responses.LINKACC_UNSUCCESS_1.getString());

        }else if(founduser!=null && account !=null){
            logger.error("account already have");
            return new Response( false, Responses.LINKACC_UNSUCCESS_3.name(), Responses.LINKACC_UNSUCCESS_3.getString());
        }else if (founduser !=null && account==null ){
            boolean passwordMatches = passwordEncoder.matches(accountRequest.getMpin(), founduser.getMpin());
            if (passwordMatches && accountRequest.getAccountNo()!=null){

                Account createaccount=new Account();
                createaccount.setAccountNumber(accountRequest.getAccountNo());
                createaccount.setUser(founduser);
                createaccount.setType(String.valueOf(AccountType.SAVINGS));
                accountRepository.save(createaccount);


                logger.info("account connected");
                return new Response( true, Responses.LINKACC_SUCCESS.name(), Responses.LINKACC_SUCCESS.getString());

            }
            logger.error("password mismatch or account number issue");
            return new Response( false, Responses.LINKACC_UNSUCCESS_2.name(), Responses.LINKACC_UNSUCCESS_2.getString());

        }

        return null;


    }
}
