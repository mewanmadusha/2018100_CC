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
 * Date : ${2018/08} 11:24 AM
 * Since : version 1.0
 * Description :
 * ***************
 **/
package com.demo.coinbase.dao.model;

import javax.persistence.*;
import java.util.List;

/**
 *
 *@Author P.K.M Madusha
 *
 */
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;
    private String accountNumber;
    private String amount;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    private String type;

    @OneToMany(mappedBy ="account",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<WalletTrans> walletaccTransList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<WalletTrans> getWalletaccTransList() {
        return walletaccTransList;
    }

    public void setWalletaccTransList(List<WalletTrans> walletaccTransList) {
        this.walletaccTransList = walletaccTransList;
    }
}
