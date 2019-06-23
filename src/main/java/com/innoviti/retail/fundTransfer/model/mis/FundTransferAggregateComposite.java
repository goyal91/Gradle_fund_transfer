package com.innoviti.retail.fundTransfer.model.mis;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundTransferAggregateComposite implements Serializable {

	private static final long serialVersionUID = -800916023095179840L;

	@Column(name = "utid")
	private String utid;

	@Column(name = "acq_bank_code")
	private int acqBankCode;

	@Column(name = "aggr_start_time")
	private Date aggrStartTime;

	@Column(name = "aggr_end_time")
	private Date aggrEndTime;

	public String getUtid() {
		return utid;
	}

	public void setUtid(String utid) {
		this.utid = utid;
	}

	public Date getAggrStartTime() {
		return aggrStartTime;
	}

	public void setAggrStartTime(Date aggrStartTime) {
		this.aggrStartTime = aggrStartTime;
	}

	public Date getAggrEndTime() {
		return aggrEndTime;
	}

	public void setAggrEndTime(Date aggrEndTime) {
		this.aggrEndTime = aggrEndTime;
	}

	public int getAcqBankCode() {
		return acqBankCode;
	}

	public void setAcqBankCode(int acqBankCode) {
		this.acqBankCode = acqBankCode;
	}

}