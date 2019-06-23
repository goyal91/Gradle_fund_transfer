package com.innoviti.retail.fundTransfer.utils;

import java.io.Serializable;

public class TransactionDetailsDto implements IFundTransferDto, Serializable {

	private static final long serialVersionUID = 3900842665280208742L;

	private String primId;

	private String utid;

	private String btid;

	private String batchSrlNo;

	private int acqBankCode;

	private String chainId;

	private String biBankMerchantId = "";

	private String boRrnNumber = "";

	private String boApprovalCode = "";

	private String ciMaskCardNumber = "";

	private String cardType = "";

	private Double ciAmount;

	private Double boAmount;

	private String schemeCode = "";

	private Double differenceInAmount;

	private String ciInnoProcessingcode;

	private String tenderMode;

	private String logo = "";

	private Double inputRate;

	private Double mdrRate;

	private Double campaignMdrRate;

	private Double inputRateAmount;

	private Double mdrRateAmount;

	private Double campaignMdrRateAmount;

	private Double gstOnMdr;

	private Double campaignGstOnMdr;

	private Double txnAmountToTxfer;

	private Double campaignTxnAmountToTxfer;

	private String brandCommissionScheme;

	private Double brandTxnCommRate;

	private Double brandTxnAmt;

	private Double merchantTxnAmt;

	private Double brandMdrCommRate;

	private Double brandMdrAmt;

	private Double merchantMdrAmt;

	private Double brandGstAmt;

	private Double merchantGstAmt;

	private Double brandTxferAmt;

	private Double merchantTxferAmt;

	private String txnTimestamp;

	private String upiTxnId = "";

	private String storeName = "";

	public String getPrimId() {
		return primId;
	}

	public void setPrimId(String primId) {
		this.primId = primId;
	}

	public String getUtid() {
		return utid;
	}

	public void setUtid(String utid) {
		this.utid = utid;
	}

	public String getBtid() {
		return btid;
	}

	public void setBtid(String btid) {
		this.btid = btid;
	}

	public String getBatchSrlNo() {
		return batchSrlNo;
	}

	public void setBatchSrlNo(String batchSrlNo) {
		this.batchSrlNo = batchSrlNo;
	}

	public int getAcqBankCode() {
		return acqBankCode;
	}

	public void setAcqBankCode(int acqBankCode) {
		this.acqBankCode = acqBankCode;
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

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public Double getDifferenceInAmount() {
		return differenceInAmount;
	}

	public void setDifferenceInAmount(Double differenceInAmount) {
		this.differenceInAmount = differenceInAmount;
	}

	public String getCiInnoProcessingcode() {
		return ciInnoProcessingcode;
	}

	public void setCiInnoProcessingcode(String ciInnoProcessingcode) {
		this.ciInnoProcessingcode = ciInnoProcessingcode;
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

	public Double getInputRate() {
		return inputRate;
	}

	public void setInputRate(Double inputRate) {
		this.inputRate = inputRate;
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

	public Double getInputRateAmount() {
		return inputRateAmount;
	}

	public void setInputRateAmount(Double inputRateAmount) {
		this.inputRateAmount = inputRateAmount;
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

	public String getTxnTimestamp() {
		return txnTimestamp;
	}

	public void setTxnTimestamp(String txnTimestamp) {
		this.txnTimestamp = txnTimestamp;
	}

	public String getUpiTxnId() {
		return upiTxnId;
	}

	public void setUpiTxnId(String upiTxnId) {
		this.upiTxnId = upiTxnId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}