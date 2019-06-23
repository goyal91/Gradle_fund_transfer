package com.innoviti.retail.fundTransfer.model.staging;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "merchant_account_details")
public class MerchantAccountDetails implements Serializable {

	private static final long serialVersionUID = -5631951118471106515L;

	@EmbeddedId
	private MerchantAccountDetailsComposite merchantAccountDetailsComposite;

	@Column(name = "mer_id", nullable = false)
	private String merchantId;

	@Column(name = "chain_id", nullable = false)
	private String chainId;

	@Column(name = "beneficiary_name", nullable = false)
	private String beneficiaryName;

	@Column(name = "crtupd_reason", columnDefinition = "VARCHAR(60)")
	private String crtupdReason;

	@Column(name = "crtupd_status", columnDefinition = "CHAR(1)")
	private String crtupdStatus;

	@Column(name = "crtupd_dt")
	private Date crtupdDate;

	@Column(name = "crtupd_user", columnDefinition = "VARCHAR(50) DEFAULT 'System'")
	private String crtupdUser;

	@Column(name = "pan_number", columnDefinition = "VARCHAR(20)")
	private String panNumber;

	@Column(name = "gst_number", columnDefinition = "VARCHAR(20)")
	private String gstNumber;

	public MerchantAccountDetailsComposite getMerchantAccountDetailsComposite() {
		return merchantAccountDetailsComposite;
	}

	public void setMerchantAccountDetailsComposite(MerchantAccountDetailsComposite merchantAccountDetailsComposite) {
		this.merchantAccountDetailsComposite = merchantAccountDetailsComposite;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getCrtupdReason() {
		return crtupdReason;
	}

	public void setCrtupdReason(String crtupdReason) {
		this.crtupdReason = crtupdReason;
	}

	public String getCrtupdStatus() {
		return crtupdStatus;
	}

	public void setCrtupdStatus(String crtupdStatus) {
		this.crtupdStatus = crtupdStatus;
	}

	public Date getCrtupdDate() {
		return crtupdDate;
	}

	public void setCrtupdDate(Date crtupdDate) {
		this.crtupdDate = crtupdDate;
	}

	public String getCrtupdUser() {
		return crtupdUser;
	}

	public void setCrtupdUser(String crtupdUser) {
		this.crtupdUser = crtupdUser;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

}