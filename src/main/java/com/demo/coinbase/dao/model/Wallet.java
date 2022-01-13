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
 * Date : ${2018/08} 4:10 PM
 * Since : version 1.0
 * Description :
 * ***************
 **/
package com.demo.coinbase.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 *
 *@Author P.K.M Madusha
 *
 */
@Entity
@Table(name = "wallet_account")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wallet_id")
    private Long id;
    @Column(name = "wallet_account_number")
    private String walletAccNumber;
    private String balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user",unique = true)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy ="wallet",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<WalletTrans> walletTransList;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<WalletTrans> getWalletTransList() {
        return walletTransList;
    }

    public void setWalletTransList(List<WalletTrans> walletTransList) {
        this.walletTransList = walletTransList;
    }

    public String getWalletAccNumber() {
        return walletAccNumber;
    }

    public void setWalletAccNumber(String walletAccNumber) {
        this.walletAccNumber = walletAccNumber;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
