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
package com.demo.coinbase.service;


import com.demo.coinbase.dto.requests.TopupRequest;
import com.demo.coinbase.dto.responses.Response;

/**
 *
 *@Author P.K.M Madusha
 *
 */

public interface WalletService {
    Response topupWallet(TopupRequest topupRequest);
}
