package com.innoviti.retail.fundTransfer.model.mis;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "charge_schedule")
public class ChargeSchedule implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "charge_id")
	private BigInteger chargeId;

	@Column(name = "chain_id")
	private String chainId;

	@Column(name = "utid")
	private String utid;

	@Column(name = "deduction_date")
	private Date deductionDate;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "gst_amount")
	private Double gstAmount;

	@Column(name = "outstanding_amount")
	private Double outstandingAmount;

	@Column(name = "gst_outstanding_amount")
	private Double gstOutstandingAmount;

	@Column(name = "priority")
	private int priority;

	@Column(name = "who_pays")
	private char bearer;

	@Column(name = "whom_to_pay")
	private char receiver;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "create_dt")
	private Date createDate;

	@Column(name = "update_dt")
	private Date updateDate;

	@Column(name = "crtupd_user")
	private String crtupdUser;

	public BigInteger getChargeId() {
		return chargeId;
	}

	public void setChargeId(BigInteger chargeId) {
		this.chargeId = chargeId;
	}

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public String getUtid() {
		return utid;
	}

	public void setUtid(String utid) {
		this.utid = utid;
	}

	public Date getDeductionDate() {
		return deductionDate;
	}

	public void setDeductionDate(Date deductionDate) {
		this.deductionDate = deductionDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public char getBearer() {
		return bearer;
	}

	public void setBearer(char bearer) {
		this.bearer = bearer;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCrtupdUser() {
		return crtupdUser;
	}

	public void setCrtupdUser(String crtupdUser) {
		this.crtupdUser = crtupdUser;
	}

	public Double getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(Double gstAmount) {
		this.gstAmount = gstAmount;
	}

	public Double getGstOutstandingAmount() {
		return gstOutstandingAmount;
	}

	public void setGstOutstandingAmount(Double gstOutstandingAmount) {
		this.gstOutstandingAmount = gstOutstandingAmount;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public char getReceiver() {
		return receiver;
	}

	public void setReceiver(char receiver) {
		this.receiver = receiver;
	}

}