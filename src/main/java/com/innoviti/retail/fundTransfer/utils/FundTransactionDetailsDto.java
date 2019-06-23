package com.innoviti.retail.fundTransfer.utils;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FundTransactionDetailsDto implements Serializable {

	private static final long serialVersionUID = 3934970122588274566L;

	private Double ciAmount;

	private Double boAmount;

	private Double difference;

	private String ciInnoProcessingcode;

	private String transactionType;

	private Double inputRate;

	private Double outputRate;

	private Double campaignOutputRate;

	private Double inputRateAmount;

	private Double outputRateAmount;

	private Double campaignOutputRateAmount;

	private Double txnAmountBeforeTax;

	private Double txnAmountAfterTax;

	private Double campaignTxnAmountBeforeTax;

	private Double campaignTxnAmountAfterTax;

	private Date txnTimestamp;

	private Date crtupdDt;

	private String crtupdUser;

	public Double getCiAmount() {
		return ciAmount;
	}

	public void setCiAmount(Double ciAmount) {
		this.ciAmount = ciAmount;
	}

	public Double getBoAmount() {
		return boAmount;
	}

	public void setBoAmount(Double boAmount) {
		this.boAmount = boAmount;
	}

	public Double getDifference() {
		return difference;
	}

	public void setDifference(Double difference) {
		this.difference = difference;
	}

	public String getCiInnoProcessingcode() {
		return ciInnoProcessingcode;
	}

	public void setCiInnoProcessingcode(String ciInnoProcessingcode) {
		this.ciInnoProcessingcode = ciInnoProcessingcode;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getInputRate() {
		return inputRate;
	}

	public void setInputRate(Double inputRate) {
		this.inputRate = inputRate;
	}

	public Double getOutputRate() {
		return outputRate;
	}

	public void setOutputRate(Double outputRate) {
		this.outputRate = outputRate;
	}

	public Double getCampaignOutputRate() {
		return campaignOutputRate;
	}

	public void setCampaignOutputRate(Double campaignOutputRate) {
		this.campaignOutputRate = campaignOutputRate;
	}

	public Double getInputRateAmount() {
		return inputRateAmount;
	}

	public void setInputRateAmount(Double inputRateAmount) {
		this.inputRateAmount = inputRateAmount;
	}

	public Double getOutputRateAmount() {
		return outputRateAmount;
	}

	public void setOutputRateAmount(Double outputRateAmount) {
		this.outputRateAmount = outputRateAmount;
	}

	public Double getCampaignOutputRateAmount() {
		return campaignOutputRateAmount;
	}

	public void setCampaignOutputRateAmount(Double campaignOutputRateAmount) {
		this.campaignOutputRateAmount = campaignOutputRateAmount;
	}

	public Double getTxnAmountBeforeTax() {
		return txnAmountBeforeTax;
	}

	public void setTxnAmountBeforeTax(Double txnAmountBeforeTax) {
		this.txnAmountBeforeTax = txnAmountBeforeTax;
	}

	public Double getTxnAmountAfterTax() {
		return txnAmountAfterTax;
	}

	public void setTxnAmountAfterTax(Double txnAmountAfterTax) {
		this.txnAmountAfterTax = txnAmountAfterTax;
	}

	public Double getCampaignTxnAmountBeforeTax() {
		return campaignTxnAmountBeforeTax;
	}

	public void setCampaignTxnAmountBeforeTax(Double campaignTxnAmountBeforeTax) {
		this.campaignTxnAmountBeforeTax = campaignTxnAmountBeforeTax;
	}

	public Double getCampaignTxnAmountAfterTax() {
		return campaignTxnAmountAfterTax;
	}

	public void setCampaignTxnAmountAfterTax(Double campaignTxnAmountAfterTax) {
		this.campaignTxnAmountAfterTax = campaignTxnAmountAfterTax;
	}

	public Date getTxnTimestamp() {
		return txnTimestamp;
	}

	public void setTxnTimestamp(Date txnTimestamp) {
		this.txnTimestamp = txnTimestamp;
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

}
