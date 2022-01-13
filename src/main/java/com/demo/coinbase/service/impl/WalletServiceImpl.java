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
 * Date : ${2018/08} 10:14 AM
 * Since : version 1.0
 * Description :
 * ***************
 **/
package com.demo.coinbase.service.impl;

import com.demo.coinbase.dao.model.Account;
import com.demo.coinbase.dao.model.User;
import com.demo.coinbase.dao.model.Wallet;
import com.demo.coinbase.dao.model.WalletTrans;
import com.demo.coinbase.dao.repository.AccountRepository;
import com.demo.coinbase.dao.repository.UserRepository;
import com.demo.coinbase.dao.repository.WalletRepository;
import com.demo.coinbase.dao.repository.WalletTransRepository;
import com.demo.coinbase.dto.requests.TopupRequest;
import com.demo.coinbase.dto.responses.Response;
import com.demo.coinbase.enums.Responses;
import com.demo.coinbase.enums.TransferMethod;
import com.demo.coinbase.enums.TransferStatus;
import com.demo.coinbase.service.WalletService;
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
 *
 *@Author P.K.M Madusha
 *
 */
@Service
public class WalletServiceImpl implements WalletService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    WalletTransRepository walletTransRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public Response topupWallet(TopupRequest topupRequest) {
        final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));


        User foundUser = userRepository.findByMobileNo(topupRequest.getMobileNo());
        if (foundUser == null) {
            logger.error("Couldn't determine user: " + topupRequest.getMobileNo());
            return new Response( false, Responses.WALLET_CREDIT_UNSUCCESS.name(), Responses.WALLET_CREDIT_UNSUCCESS.getString());

        }

        if(foundUser !=null ){

            boolean passwordMatches = passwordEncoder.matches(topupRequest.getMpin(), foundUser.getMpin());

            if(passwordMatches){

                Account foundAccount=accountRepository.findByAccountNumber(topupRequest.getFromAccountNumber());
                if (foundAccount ==null){

                    return new Response( false, Responses.WALLET_CREDIT_UNSUCCESS.name(), Responses.WALLET_CREDIT_UNSUCCESS.getString());

                }
                if (foundAccount!=null && (foundAccount.getUser().getId()==foundUser.getId())){

                    try {
                        Wallet foundWallet = walletRepository.findByUser(foundUser);
                        WalletTrans walletTrans=new WalletTrans();

                        String currentAccAmount = foundAccount.getAmount();
                        String topupAmount = topupRequest.getAmount();
                        String currentWalletAmount = foundUser.getWallet().getBalance();

                        foundAccount.setAmount(String.valueOf(Integer.valueOf(currentAccAmount) - Integer.valueOf(topupAmount)));
                        foundWallet.setBalance(String.valueOf(Integer.valueOf(currentWalletAmount) + Integer.valueOf(topupAmount)));

                        accountRepository.save(foundAccount);
                        walletRepository.save(foundWallet);

                        walletTrans.setAccount(foundAccount);
                        walletTrans.setAmount(topupAmount);
                        walletTrans.setTransferMethod(String.valueOf(TransferMethod.CREDIT));
                        walletTrans.setCreatedOn(sdf.format(date));
                        walletTrans.setTransferStatus(String.valueOf(TransferStatus.SUCCESS));
                        walletTrans.setWallet(foundWallet);
                        walletTrans.setAccount(foundAccount);

                        walletTransRepository.save(walletTrans);


                        logger.info("top up success");
                        return new Response(true, Responses.WALLET_CREDIT_SUCCESS.name(), Responses.WALLET_CREDIT_SUCCESS.getString());
                    }catch (Exception e){


                        e.printStackTrace();
                    }
                }


            }
            logger.error("password mismatch");
            return new Response( false, Responses.WALLET_CREDIT_UNSUCCESS.name(), Responses.WALLET_CREDIT_UNSUCCESS.getString());

        }
        return null;
    }
}
