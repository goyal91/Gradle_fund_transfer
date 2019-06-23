package com.innoviti.retail.fundTransfer.model.mis;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "fund_txfr_aggr")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundTransferAggregate implements Serializable {

	private static final long serialVersionUID = 6013398083341848099L;

	@EmbeddedId
	private FundTransferAggregateComposite fundTransferAggregateComposite;

	@Column(name = "chain_id")
	private String chainId;

	@Column(name = "aggr_amount")
	private Double aggrAmount;

	@Column(name = "edc_amount")
	private Double edcAmount;

	@Column(name = "aggr_amount_to_txfer")
	private Double aggrAmountToTxnfer;

	@Column(name = "difference_in_amount")
	private Double differenceInAmount;

	@Column(name = "no_of_transactions")
	private int noOfTransactions;

	@Column(name = "aggr_mdr")
	private Double aggrMdr;

	@Column(name = "aggr_gst")
	private Double aggrGst;

	@Column(name = "campaign_aggr_amount_to_txfer")
	private Double campaignAggrAmountToTxnfer;

	@Column(name = "campaign_aggr_mdr")
	private Double campaignAggrMdr;

	@Column(name = "campaign_aggr_gst")
	private Double campaignAggrGst;

	@Column(name = "aggr_brand_txn_amt")
	private Double aggrBrandTxnAmt;

	@Column(name = "aggr_merchant_txn_amt")
	private Double aggrMerchantTxnAmt;

	@Column(name = "aggr_brand_mdr_amt")
	private Double aggrBrandMdrAmt;

	@Column(name = "aggr_merchant_mdr_amt")
	private Double aggrMerchantMdrAmt;

	@Column(name = "aggr_brand_gst_amt")
	private Double aggrBrandGstAmt;

	@Column(name = "aggr_merchant_gst_amt")
	private Double aggrMerchantGstAmt;

	@Column(name = "aggr_brand_txfer_amt")
	private Double aggrBrandTxferAmt;

	@Column(name = "aggr_merchant_txfer_amt")
	private Double aggrMerchantTxferAmt;

	@Column(name = "crtupd_user")
	private String crtupdUser;

	@Column(name = "crtupd_dt")
	private Date crtupdDt;

	@Column(name = "txn_timestamp")
	private Date txnTimestamp;

	public FundTransferAggregateComposite getFundTransferAggregateComposite() {
		return fundTransferAggregateComposite;
	}

	public void setFundTransferAggregateComposite(FundTransferAggregateComposite fundTransferAggregateComposite) {
		this.fundTransferAggregateComposite = fundTransferAggregateComposite;
	}

	public Double getAggrAmount() {
		return aggrAmount;
	}

	public void setAggrAmount(Double aggrAmount) {
		this.aggrAmount = aggrAmount;
	}

	public Double getAggrAmountToTxnfer() {
		return aggrAmountToTxnfer;
	}

	public void setAggrAmountToTxnfer(Double aggrAmountToTxnfer) {
		this.aggrAmountToTxnfer = aggrAmountToTxnfer;
	}

	public Double getAggrMdr() {
		return aggrMdr;
	}

	public void setAggrMdr(Double aggrMdr) {
		this.aggrMdr = aggrMdr;
	}

	public Double getAggrGst() {
		return aggrGst;
	}

	public void setAggrGst(Double aggrGst) {
		this.aggrGst = aggrGst;
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

	public Double getCampaignAggrAmountToTxnfer() {
		return campaignAggrAmountToTxnfer;
	}

	public void setCampaignAggrAmountToTxnfer(Double campaignAggrAmountToTxnfer) {
		this.campaignAggrAmountToTxnfer = campaignAggrAmountToTxnfer;
	}

	public Double getCampaignAggrMdr() {
		return campaignAggrMdr;
	}

	public void setCampaignAggrMdr(Double campaignAggrMdr) {
		this.campaignAggrMdr = campaignAggrMdr;
	}

	public Double getCampaignAggrGst() {
		return campaignAggrGst;
	}

	public void setCampaignAggrGst(Double campaignAggrGst) {
		this.campaignAggrGst = campaignAggrGst;
	}

	public int getNoOfTransactions() {
		return noOfTransactions;
	}

	public void setNoOfTransactions(int noOfTransactions) {
		this.noOfTransactions = noOfTransactions;
	}

	public Double getDifferenceInAmount() {
		return differenceInAmount;
	}

	public void setDifferenceInAmount(Double differenceInAmount) {
		this.differenceInAmount = differenceInAmount;
	}

	public Double getEdcAmount() {
		return edcAmount;
	}

	public void setEdcAmount(Double edcAmount) {
		this.edcAmount = edcAmount;
	}

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public Double getAggrBrandTxnAmt() {
		return aggrBrandTxnAmt;
	}

	public void setAggrBrandTxnAmt(Double aggrBrandTxnAmt) {
		this.aggrBrandTxnAmt = aggrBrandTxnAmt;
	}

	public Double getAggrMerchantTxnAmt() {
		return aggrMerchantTxnAmt;
	}

	public void setAggrMerchantTxnAmt(Double aggrMerchantTxnAmt) {
		this.aggrMerchantTxnAmt = aggrMerchantTxnAmt;
	}

	public Double getAggrBrandMdrAmt() {
		return aggrBrandMdrAmt;
	}

	public void setAggrBrandMdrAmt(Double aggrBrandMdrAmt) {
		this.aggrBrandMdrAmt = aggrBrandMdrAmt;
	}

	public Double getAggrMerchantMdrAmt() {
		return aggrMerchantMdrAmt;
	}

	public void setAggrMerchantMdrAmt(Double aggrMerchantMdrAmt) {
		this.aggrMerchantMdrAmt = aggrMerchantMdrAmt;
	}

	public Double getAggrBrandGstAmt() {
		return aggrBrandGstAmt;
	}

	public void setAggrBrandGstAmt(Double aggrBrandGstAmt) {
		this.aggrBrandGstAmt = aggrBrandGstAmt;
	}

	public Double getAggrMerchantGstAmt() {
		return aggrMerchantGstAmt;
	}

	public void setAggrMerchantGstAmt(Double aggrMerchantGstAmt) {
		this.aggrMerchantGstAmt = aggrMerchantGstAmt;
	}

	public Double getAggrBrandTxferAmt() {
		return aggrBrandTxferAmt;
	}

	public void setAggrBrandTxferAmt(Double aggrBrandTxferAmt) {
		this.aggrBrandTxferAmt = aggrBrandTxferAmt;
	}

	public Double getAggrMerchantTxferAmt() {
		return aggrMerchantTxferAmt;
	}

	public void setAggrMerchantTxferAmt(Double aggrMerchantTxferAmt) {
		this.aggrMerchantTxferAmt = aggrMerchantTxferAmt;
	}

	public Date getTxnTimestamp() {
		return txnTimestamp;
	}

	public void setTxnTimestamp(Date txnTimestamp) {
		this.txnTimestamp = txnTimestamp;
	}

}