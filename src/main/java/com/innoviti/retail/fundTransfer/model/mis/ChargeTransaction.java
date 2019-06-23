package com.innoviti.retail.fundTransfer.model.mis;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "charge_transactions")
public class ChargeTransaction implements Serializable {

	private static final long serialVersionUID = 6136726258772562460L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "charge_txn_id")
	private BigInteger chargeTxnId;

	@Column(name = "charge_id")
	private BigInteger chargeId;

	@Column(name = "charged_amount")
	private Double chargedAmount;
	/*
	 * @Column(name = "remarks") private String remarks;
	 * 
	 * @Column(name = "who_paid") private char whoPaid;
	 */

	@Column(name = "charged_gst_amount")
	private Double chargedGstAmount;

	@Column(name = "charged_date")
	private Date chargedDate;

	@Column(name = "crtupd_dt")
	private Date crtupdDt;

	@Column(name = "crtupd_user")
	private String crtupdUser;

	@Column(name = "source_amount")
	private String sourceAmount;

	public BigInteger getChargeTxnId() {
		return chargeTxnId;
	}

	public void setChargeTxnId(BigInteger chargeTxnId) {
		this.chargeTxnId = chargeTxnId;
	}

	public BigInteger getChargeId() {
		return chargeId;
	}

	public void setChargeId(BigInteger chargeId) {
		this.chargeId = chargeId;
	}

	public Double getChargedAmount() {
		return chargedAmount;
	}

	public void setChargedAmount(Double chargedAmount) {
		this.chargedAmount = chargedAmount;
	}

	public Date getCrtupdDt() {
		return crtupdDt;
	}

	public void setCrtupdDt(Date crtupdDt) {
		this.crtupdDt = crtupdDt;
	}

	public String getCrtupdUser() {
		return crtupdUser;
	}

	public void setCrtupdUser(String crtupdUser) {
		this.crtupdUser = crtupdUser;
	}

	public Date getChargedDate() {
		return chargedDate;
	}

	public void setChargedDate(Date chargedDate) {
		this.chargedDate = chargedDate;
	}

	public String getSourceAmount() {
		return sourceAmount;
	}

	public void setSourceAmount(String sourceAmount) {
		this.sourceAmount = sourceAmount;
	}

	public Double getChargedGstAmount() {
		return chargedGstAmount;
	}

	public void setChargedGstAmount(Double chargedGstAmount) {
		this.chargedGstAmount = chargedGstAmount;
	}

}
