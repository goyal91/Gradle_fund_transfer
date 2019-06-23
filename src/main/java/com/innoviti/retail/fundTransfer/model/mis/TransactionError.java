package com.innoviti.retail.fundTransfer.model.mis;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "txn_error")
public class TransactionError implements Serializable {

	private static final long serialVersionUID = -5758453124464485288L;

	@Id
	@Column(name = "prim_id")
	private String primId;

	@Column(name = "trigger_name")
	private String triggeName;

	@Column(name = "crtupd_user")
	private String crtupdUser;

	@Column(name = "crtupd_dt")
	private Date crtupdDt;

	@Column(name = "error_code")
	private Double errorCode;

	@Column(name = "error_message")
	private String errorMessage;

	public String getPrimId() {
		return primId;
	}

	public void setPrimId(String primId) {
		this.primId = primId;
	}

	public String getTriggeName() {
		return triggeName;
	}

	public void setTriggeName(String triggeName) {
		this.triggeName = triggeName;
	}

	public String getCrtupdUser() {
		return crtupdUser;
	}

	public void setCrtupdUser(String crtupdUser) {
		this.crtupdUser = crtupdUser;
	}

	public Date getCrtupdDt() {
		return crtupdDt;
	}

	public void setCrtupdDt(Date crtupdDt) {
		this.crtupdDt = crtupdDt;
	}

	public Double getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Double errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}