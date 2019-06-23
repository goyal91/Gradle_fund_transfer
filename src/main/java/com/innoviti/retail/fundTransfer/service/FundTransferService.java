/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innoviti.retail.fundTransfer.service;

import java.util.Date;
import java.util.List;

import com.innoviti.retail.fundTransfer.model.mis.ChargeSchedule;

public interface FundTransferService {

	void generateFundTransferFile(Date startDate, Date endDate, String emailCheck);

	void chargesToBeDeductedOnDailyBasis(Date startDate, Date endDate);

	void chargesToBeDeducted(Date startDate, Date endDate, List<ChargeSchedule> chargeScheduled, Double aggrAmount,
			boolean merchant, boolean brand);

}
