/**
 * PROJECT : ${Govipola}
 * PRODUCT : ${Com.fg.krushi}
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
 * Date : ${2018/08} 10:27 AM
 * Since : version 1.0
 * Description :
 * ***************
 **/
package com.demo.coinbase.service.impl;

import com.demo.coinbase.dao.model.User;
import com.demo.coinbase.dao.model.Wallet;
import com.demo.coinbase.dao.repository.UserRepository;
import com.demo.coinbase.dao.repository.UserTypeRepository;
import com.demo.coinbase.dao.repository.WalletRepository;
import com.demo.coinbase.dto.requests.ActivateRequest;
import com.demo.coinbase.dto.requests.LoginRequest;
import com.demo.coinbase.dto.requests.RegisterRequest;
import com.demo.coinbase.dto.responses.ActivateResponse;
import com.demo.coinbase.dto.responses.LoginResponse;
import com.demo.coinbase.dto.responses.RegisterResponse;
import com.demo.coinbase.enums.Responses;
import com.demo.coinbase.enums.UserStatus;
import com.demo.coinbase.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author P.K.M Madusha
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private WalletRepository walletRepository;


    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));
        User user = userRepository.findByMobileNo(registerRequest.getMobileNo());

        if (user != null) {
            logger.error("Couldn't register user : " + registerRequest.getMobileNo() + " already exists");

            return new RegisterResponse(false, Responses.REGISTER_UNSUCCESS.name(), Responses.REGISTER_UNSUCCESS.getString(), user.getId());

        } else {
            try {
                user = new User();
                user.setCreatedOn(sdf.format(date));
                user.setEmail(registerRequest.getEmail());
                user.setFirstName(registerRequest.getFirstname());
                user.setLastname(registerRequest.getLastname());

                String encryptedPassword = passwordEncoder.encode("1212");
                System.out.println(encryptedPassword);
                user.setSecurityCode(encryptedPassword);
                user.setUserType(userTypeRepository.findById(registerRequest.getUserType()));
                user.setStatus(String.valueOf(UserStatus.PENDING));
                user.setMobileNo(registerRequest.getMobileNo());
                user.setNic(registerRequest.getNicNo());
                user.setGender(registerRequest.getGender());

                userRepository.save(user);


            } catch (Exception e) {
                logger.error(String.valueOf(e));
            }
            logger.info("New user registered, user id: " + user.getId());
            return new RegisterResponse(true, Responses.REGISTER_SUCCESS.name(), Responses.REGISTER_SUCCESS.getString(), user.getId());

        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User foundUser = userRepository.findByMobileNo(loginRequest.getMobileNo());
        if (foundUser == null) {
            logger.error("Couldn't determine logging user: " + loginRequest.getMobileNo());
            return new LoginResponse(false, Responses.LOGIN_UNSUCCESS_1.name(), Responses.LOGIN_UNSUCCESS_1.getString(), null);

        } else if (foundUser != null && foundUser.getStatus().equals("PENDING")) {

            logger.error("not active user " + loginRequest.getMobileNo());
            return new LoginResponse(false, Responses.LOGIN_UNSUCCESS_2.name(), Responses.LOGIN_UNSUCCESS_2.getString(), foundUser.getId());

        } else {
            boolean passwordMatches = passwordEncoder.matches(loginRequest.getMpin(), foundUser.getMpin());
            if (passwordMatches) {

                User loggedInUser = userRepository.findByMobileNo(foundUser.getMobileNo());

                userRepository.save(foundUser);
                logger.info("user " + foundUser.getMobileNo() + " logged in");

                return new LoginResponse(true, Responses.LOGIN_SUCCESS.name(), Responses.LOGIN_SUCCESS.getString(), foundUser.getId());
            }
            logger.error("Password mismatch for user : " + foundUser.getMobileNo());
            return new LoginResponse(false, Responses.LOGIN_UNSUCCESS_3.name(), Responses.LOGIN_UNSUCCESS_3.getString(), foundUser.getId());

        }
    }

    @Override
    public ActivateResponse activate(ActivateRequest activateRequest) {
        User foundUser = userRepository.findByMobileNo(activateRequest.getMobileNo());

        if (foundUser == null) {
            logger.error("Couldn't determine user: " + activateRequest.getMobileNo());
            return new ActivateResponse(false, Responses.ACTIVATE_UNSUCCESS_1.name(), Responses.ACTIVATE_UNSUCCESS_1.getString(), null);

        }

        if (foundUser != null) {

            boolean passwordMatches = passwordEncoder.matches(activateRequest.getSecurityCode(), foundUser.getSecurityCode());

            if (passwordMatches) {
                foundUser.setSecurityCode(null);
                String encryptedmpin = passwordEncoder.encode(activateRequest.getMpin());
                foundUser.setMpin(encryptedmpin);
                foundUser.setStatus(String.valueOf(UserStatus.ACTIVE));
                userRepository.save(foundUser);

                Wallet wallet = new Wallet();
                wallet.setUser(foundUser);
                wallet.setWalletAccNumber("1010" + foundUser.getId());
                wallet.setBalance("1000");

                walletRepository.save(wallet);

                return new ActivateResponse(true, Responses.ACTIVATE_SUCCESS.name(), Responses.ACTIVATE_SUCCESS.getString(), foundUser.getId());

            }
            logger.error("Wrong Security code");
            return new ActivateResponse(false, Responses.ACTIVATE_UNSUCCESS_2.name(), Responses.ACTIVATE_UNSUCCESS_2.getString(), null);

        }


        return null;

    }
}
