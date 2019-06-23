package com.innoviti.retail.fundTransfer.model.staging;

import java.io.Serializable;

import javax.persistence.Column;

public class MerchantAccountDetailsComposite implements Serializable {

	private static final long serialVersionUID = 3216799237129983231L;

	@Column(name = "account_no", nullable = false)
	private String accountNumber;

	@Column(name = "ifsc_code", nullable = false)
	private String ifscCode;

	@Column(name = "store_code", nullable = false)
	private String storeCode;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
}
