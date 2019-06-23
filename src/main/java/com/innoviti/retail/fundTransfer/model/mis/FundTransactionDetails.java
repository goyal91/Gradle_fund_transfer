package com.innoviti.retail.fundTransfer.model.mis;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ftx_txn_mdr")
public class FundTransactionDetails implements Serializable {

	private static final long serialVersionUID = 5932424464408927050L;

	@EmbeddedId
	private FundTransactionDetailsComposite fundTransactionDetailsComposite;

	@Column(name = "chain_id")
	private String chainId;

	@Column(name = "bi_bank_merchant_id")
	private String biBankMerchantId;

	@Column(name = "bo_rrn_number")
	private String boRrnNumber;

	@Column(name = "bo_approval_code")
	private String boApprovalCode;

	@Column(name = "ci_mask_card_number")
	private String ciMaskCardNumber;

	@Column(name = "card_type")
	private String cardType;

	@Column(name = "ci_amount")
	private Double ciAmount;

	@Column(name = "bo_amount")
	private Double boAmount;

	@Column(name = "scheme_code")
	private String schemeCode;

	@Column(name = "difference_in_amount")
	private Double differenceInAmount;

	@Column(name = "ci_inno_processingcode")
	private String ciInnoProcessingcode;

	@Column(name = "tender_mode")
	private String tenderMode;

	@Column(name = "logo")
	private String logo;

	@Column(name = "input_rate")
	private Double inputRate;

	@Column(name = "mdr_rate")
	private Double mdrRate;

	@Column(name = "campaign_mdr_rate")
	private Double campaignMdrRate;

	@Column(name = "input_rate_amount")
	private Double inputRateAmount;

	@Column(name = "mdr_rate_amount")
	private Double mdrRateAmount;

	@Column(name = "campaign_mdr_rate_amount")
	private Double campaignMdrRateAmount;

	@Column(name = "gst_on_mdr")
	private Double gstOnMdr;

	@Column(name = "campaign_gst_on_mdr")
	private Double campaignGstOnMdr;

	@Column(name = "txn_amount_to_txfer")
	private Double txnAmountToTxfer;

	@Column(name = "campaign_txn_amount_to_txfer")
	private Double campaignTxnAmountToTxfer;

	@Column(name = "brand_commission_scheme")
	private String brandCommissionScheme;

	@Column(name = "brand_txn_comm_rate")
	private Double brandTxnCommRate;

	@Column(name = "brand_txn_amt")
	private Double brandTxnAmt;

	@Column(name = "merchant_txn_amt")
	private Double merchantTxnAmt;

	@Column(name = "brand_mdr_comm_rate")
	private Double brandMdrCommRate;

	@Column(name = "brand_mdr_amt")
	private Double brandMdrAmt;

	@Column(name = "merchant_mdr_amt")
	private Double merchantMdrAmt;

	@Column(name = "brand_gst_amt")
	private Double brandGstAmt;

	@Column(name = "merchant_gst_amt")
	private Double merchantGstAmt;

	@Column(name = "brand_txfer_amt")
	private Double brandTxferAmt;

	@Column(name = "merchant_txfer_amt")
	private Double merchantTxferAmt;

	@Column(name = "txn_timestamp")
	private Date txnTimestamp;

	@Column(name = "crtupd_dt")
	private Date crtupdDt;

	@Column(name = "crtupd_user")
	private String crtupdUser;

	@Column(name = "upi_txn_id")
	private String upiTxnId;

	public FundTransactionDetailsComposite getFundTransactionDetailsComposite() {
		return fundTransactionDetailsComposite;
	}

	public void setFundTransactionDetailsComposite(FundTransactionDetailsComposite fundTransactionDetailsComposite) {
		this.fundTransactionDetailsComposite = fundTransactionDetailsComposite;
	}

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

	public Double getDifferenceInAmount() {
		return differenceInAmount;
	}

	public void setDifferenceInAmount(Double differenceInAmount) {
		this.differenceInAmount = differenceInAmount;
	}

	public Double getGstOnMdr() {
		return gstOnMdr;
	}

	public void setGstOnMdr(Double gstOnMdr) {
		this.gstOnMdr = gstOnMdr;
	}

	public Double getCampaignGstOnMdr() {
		return campaignGstOnMdr;
	}

	public void setCampaignGstOnMdr(Double campaignGstOnMdr) {
		this.campaignGstOnMdr = campaignGstOnMdr;
	}

	public String getCiInnoProcessingcode() {
		return ciInnoProcessingcode;
	}

	public void setCiInnoProcessingcode(String ciInnoProcessingcode) {
		this.ciInnoProcessingcode = ciInnoProcessingcode;
	}

	public Double getInputRate() {
		return inputRate;
	}

	public void setInputRate(Double inputRate) {
		this.inputRate = inputRate;
	}

	public Double getInputRateAmount() {
		return inputRateAmount;
	}

	public void setInputRateAmount(Double inputRateAmount) {
		this.inputRateAmount = inputRateAmount;
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

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public String getBiBankMerchantId() {
		return biBankMerchantId;
	}

	public void setBiBankMerchantId(String biBankMerchantId) {
		this.biBankMerchantId = biBankMerchantId;
	}

	public String getBoRrnNumber() {
		return boRrnNumber;
	}

	public void setBoRrnNumber(String boRrnNumber) {
		this.boRrnNumber = boRrnNumber;
	}

	public String getBoApprovalCode() {
		return boApprovalCode;
	}

	public void setBoApprovalCode(String boApprovalCode) {
		this.boApprovalCode = boApprovalCode;
	}

	public String getCiMaskCardNumber() {
		return ciMaskCardNumber;
	}

	public void setCiMaskCardNumber(String ciMaskCardNumber) {
		this.ciMaskCardNumber = ciMaskCardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Double getMdrRate() {
		return mdrRate;
	}

	public void setMdrRate(Double mdrRate) {
		this.mdrRate = mdrRate;
	}

	public Double getCampaignMdrRate() {
		return campaignMdrRate;
	}

	public void setCampaignMdrRate(Double campaignMdrRate) {
		this.campaignMdrRate = campaignMdrRate;
	}

	public Double getMdrRateAmount() {
		return mdrRateAmount;
	}

	public void setMdrRateAmount(Double mdrRateAmount) {
		this.mdrRateAmount = mdrRateAmount;
	}

	public Double getCampaignMdrRateAmount() {
		return campaignMdrRateAmount;
	}

	public void setCampaignMdrRateAmount(Double campaignMdrRateAmount) {
		this.campaignMdrRateAmount = campaignMdrRateAmount;
	}

	public Double getTxnAmountToTxfer() {
		return txnAmountToTxfer;
	}

	public void setTxnAmountToTxfer(Double txnAmountToTxfer) {
		this.txnAmountToTxfer = txnAmountToTxfer;
	}

	public Double getCampaignTxnAmountToTxfer() {
		return campaignTxnAmountToTxfer;
	}

	public void setCampaignTxnAmountToTxfer(Double campaignTxnAmountToTxfer) {
		this.campaignTxnAmountToTxfer = campaignTxnAmountToTxfer;
	}

	public String getBrandCommissionScheme() {
		return brandCommissionScheme;
	}

	public void setBrandCommissionScheme(String brandCommissionScheme) {
		this.brandCommissionScheme = brandCommissionScheme;
	}

	public Double getBrandTxnCommRate() {
		return brandTxnCommRate;
	}

	public void setBrandTxnCommRate(Double brandTxnCommRate) {
		this.brandTxnCommRate = brandTxnCommRate;
	}

	public Double getBrandTxnAmt() {
		return brandTxnAmt;
	}

	public void setBrandTxnAmt(Double brandTxnAmt) {
		this.brandTxnAmt = brandTxnAmt;
	}

	public Double getMerchantTxnAmt() {
		return merchantTxnAmt;
	}

	public void setMerchantTxnAmt(Double merchantTxnAmt) {
		this.merchantTxnAmt = merchantTxnAmt;
	}

	public Double getBrandMdrCommRate() {
		return brandMdrCommRate;
	}

	public void setBrandMdrCommRate(Double brandMdrCommRate) {
		this.brandMdrCommRate = brandMdrCommRate;
	}

	public Double getBrandMdrAmt() {
		return brandMdrAmt;
	}

	public void setBrandMdrAmt(Double brandMdrAmt) {
		this.brandMdrAmt = brandMdrAmt;
	}

	public Double getMerchantMdrAmt() {
		return merchantMdrAmt;
	}

	public void setMerchantMdrAmt(Double merchantMdrAmt) {
		this.merchantMdrAmt = merchantMdrAmt;
	}

	public Double getBrandGstAmt() {
		return brandGstAmt;
	}

	public void setBrandGstAmt(Double brandGstAmt) {
		this.brandGstAmt = brandGstAmt;
	}

	public Double getMerchantGstAmt() {
		return merchantGstAmt;
	}

	public void setMerchantGstAmt(Double merchantGstAmt) {
		this.merchantGstAmt = merchantGstAmt;
	}

	public Double getBrandTxferAmt() {
		return brandTxferAmt;
	}

	public void setBrandTxferAmt(Double brandTxferAmt) {
		this.brandTxferAmt = brandTxferAmt;
	}

	public Double getMerchantTxferAmt() {
		return merchantTxferAmt;
	}

	public void setMerchantTxferAmt(Double merchantTxferAmt) {
		this.merchantTxferAmt = merchantTxferAmt;
	}

	public String getTenderMode() {
		return tenderMode;
	}

	public void setTenderMode(String tenderMode) {
		this.tenderMode = tenderMode;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getUpiTxnId() {
		return upiTxnId;
	}

	public void setUpiTxnId(String upiTxnId) {
		this.upiTxnId = upiTxnId;
	}

}