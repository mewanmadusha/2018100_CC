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
 * Date : ${2018/08} 1:25 PM
 * Since : version 1.0
 * Description :
 * ***************
 **/
package com.demo.coinbase.dto.requests;

import javax.validation.constraints.NotNull;

/**
 *
 *@Author P.K.M Madusha
 *
 */

public class AccountRequest {
    @NotNull
    private String accountNo;
    @NotNull
    private String type;
    @NotNull
    private String mobileNo;
    @NotNull
    private String mpin;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMpin() {
        return mpin;
    }

    public void setMpin(String mpin) {
        this.mpin = mpin;
    }
}
