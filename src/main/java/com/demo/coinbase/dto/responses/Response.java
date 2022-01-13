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
 * Date : ${2018/08} 11:14 AM
 * Since : version 1.0
 * Description :
 * ***************
 **/
package com.demo.coinbase.dto.responses;

/**
 *
 *@Author P.K.M Madusha
 *
 */

public class Response {
    private boolean statusSuccess;
    private String message;
    private String tag;

    public Response(boolean statusSuccess, String message, String tag) {
        this.statusSuccess = statusSuccess;
        this.message = message;
        this.tag = tag;
    }

    public boolean isStatusSuccess() {
        return statusSuccess;
    }

    public void setStatusSuccess(boolean statusSuccess) {
        this.statusSuccess = statusSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
