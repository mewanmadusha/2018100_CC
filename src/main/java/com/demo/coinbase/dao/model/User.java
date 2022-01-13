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
 * Date : ${2018/08} 11:33 AM
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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "mobile_no")
    private String mobileNo;
    @JsonIgnore
    private String mpin;
    private String email;
    private String firstName;
    private String lastName;
    @ManyToOne
    @JoinColumn(name="user_type_id", nullable=false)
    private UserType userType;
    @JsonIgnore
    private String createdOn;
    @JsonIgnore
    private String status;
    @JsonIgnore
    private String nic;
    @JsonIgnore
    private String gender;
    @JsonIgnore
    private  String securityCode;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Wallet wallet;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMpin() {
        return mpin;
    }

    public void setMpin(String mpin) {
        this.mpin = mpin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }


    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
