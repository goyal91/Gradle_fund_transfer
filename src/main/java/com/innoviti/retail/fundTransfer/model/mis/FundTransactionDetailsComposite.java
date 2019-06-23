package com.innoviti.retail.fundTransfer.model.mis;

import java.io.Serializable;

import javax.persistence.Column;

public class FundTransactionDetailsComposite implements Serializable {

	private static final long serialVersionUID = -1013827249054598457L;

	@Column(name = "prim_id")
	private String primId;

	@Column(name = "utid")
	private String utid;

	@Column(name = "btid")
	private String btid;

	@Column(name = "batchsrlno")
	private String batchSrlNo;

	@Column(name = "acq_bank_code")
	private int acqBankCode;

	public String getPrimId() {
		return primId;
	}

	public void setPrimId(String primId) {
		this.primId = primId;
	}

	public String getUtid() {
		return utid;
	}

	public void setUtid(String utid) {
		this.utid = utid;
	}

	public String getBtid() {
		return btid;
	}

	public void setBtid(String btid) {
		this.btid = btid;
	}

	public String getBatchSrlNo() {
		return batchSrlNo;
	}

	public void setBatchSrlNo(String batchSrlNo) {
		this.batchSrlNo = batchSrlNo;
	}

	public int getAcqBankCode() {
		return acqBankCode;
	}

	public void setAcqBankCode(int acqBankCode) {
		this.acqBankCode = acqBankCode;
	}

}