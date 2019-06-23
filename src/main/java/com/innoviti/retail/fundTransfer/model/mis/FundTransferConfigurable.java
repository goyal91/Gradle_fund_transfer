package com.innoviti.retail.fundTransfer.model.mis;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "txn_mdr_config")
public class FundTransferConfigurable implements Serializable {

	private static final long serialVersionUID = 809670257960721670L;
	
	@EmbeddedId
	private FundTransferConfigurableComposite fundTransferConfigurableComposite;

	@Column(name = "crtupd_user")
	private String crtupdUser;

	@Column(name = "crtupd_dt")
	private Date crtupdDt;

	public FundTransferConfigurableComposite getFundTransferConfigurableComposite() {
		return fundTransferConfigurableComposite;
	}

	public void setFundTransferConfigurableComposite(
			FundTransferConfigurableComposite fundTransferConfigurableComposite) {
		this.fundTransferConfigurableComposite = fundTransferConfigurableComposite;
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

}
