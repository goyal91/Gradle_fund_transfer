package com.innoviti.retail.fundTransfer.model.config;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class ContactDetails implements Serializable {

	private static final long serialVersionUID = 3781531657601017528L;

	@EmbeddedId
	private ContactComposite contactComposite;

	@Column(name = "cont_name")
	private String contactName;

	@Column(name = "cont_email")
	private String email;

	@Column(name = "cont_mobphn")
	private String mobileNo;

	@Column(name = "cont_wrkphn")
	private String workPhone;

	@Column(name = "crtupd_user")
	private String crtupdUser;

	@Column(name = "cont_department")
	private String contactDepart;

	@Column(name = "cont_title")
	private String contactTitle;

	@Column(name = "cont_desg")
	private String contactDesgn;

	@Column(name = "cont_hmphn")
	private String homePhone;

	@Column(name = "crtupd_status")
	private String crtupdStatus;

	@Column(name = "field_A")
	private String fieldA;

	@Column(name = "field_B")
	private String fieldB;

	@Column(name = "cont_fax")
	private String fax;

	@Column(name = "crtupd_dt")
	private Date crtupdDt;

	public ContactComposite getContactComposite() {
		return contactComposite;
	}

	public void setContactComposite(ContactComposite contactComposite) {
		this.contactComposite = contactComposite;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getCrtupdUser() {
		return crtupdUser;
	}

	public void setCrtupdUser(String crtupdUser) {
		this.crtupdUser = crtupdUser;
	}

	public String getContactDepart() {
		return contactDepart;
	}

	public void setContactDepart(String contactDepart) {
		this.contactDepart = contactDepart;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public String getContactDesgn() {
		return contactDesgn;
	}

	public void setContactDesgn(String contactDesgn) {
		this.contactDesgn = contactDesgn;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getCrtupdStatus() {
		return crtupdStatus;
	}

	public void setCrtupdStatus(String crtupdStatus) {
		this.crtupdStatus = crtupdStatus;
	}

	public String getFieldA() {
		return fieldA;
	}

	public void setFieldA(String fieldA) {
		this.fieldA = fieldA;
	}

	public String getFieldB() {
		return fieldB;
	}

	public void setFieldB(String fieldB) {
		this.fieldB = fieldB;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Date getCrtupdDt() {
		return crtupdDt;
	}

	public void setCrtupdDt(Date crtupdDt) {
		this.crtupdDt = crtupdDt;
	}

}
