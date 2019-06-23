package com.innoviti.retail.fundTransfer.model.mis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FundTransferConfigurableComposite implements Serializable {

	private static final long serialVersionUID = 975028237440644694L;

	@Column(name = "prim_id")
	private String property;

	@Column(name = "value")
	private String value;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String end_date;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

}
