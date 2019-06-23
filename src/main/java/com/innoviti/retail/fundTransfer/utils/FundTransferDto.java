package com.innoviti.retail.fundTransfer.utils;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FundTransferDto implements Serializable, IFundTransferDto {

	private static final long serialVersionUID = 7235452865328725193L;

	private String merchantBeneficiaryName = "";
	private String chainName = "";
	private String merchantContactName = "";
	private String accountNumber = "";
	private String ifscCode = "";
	private String storeName = "";
	private String merchantCellNo = "";
	private String merchantEmailId = "";
	private String utid;
	private Double mdr;
	private Double campaignMdr;
	private Double gstOnMdr;
	private Double campaignGstOnMdr;
	private Double amountToTransfer;
	private Double campaignAmountToTransfer;
	private Double bankApprovalAmount;
	private Double EDCAmount;
	private Double differenceInAMount;
	private Integer transactionCount;
	private String schemeCode = "";
	private String transferDate;
	private String subscriptionPlan = "";
	private Double subscriptionFee = 0.0;
	private String subscriptionDate = "";
	private Double otherDeductions = 0.0;
	private String deductionRemarks = "";
	private Double finalTransferAmount = 0.0;
	private String clientCode = "INNOVITI";
	private String productCode = "RPAY";
	private String paymentType = "IMPS";
	private int paymentRefNo = 0;
	private String paymentDate = "";
	private String drAcNo = "04222010001565";
	private Double aggrAmount = 0.0;
	private Double finalAggrTxfrAmount = 0.0;
	private String beneficiaryCode = "";
	private String iFSCCode = "";
	private String debitNarration = "";
	private String creditNarration = "";
	private String enrichment1 = "";
	private String enrichment2 = "";
	private String cellSpacing = "";

	public FundTransferDto() {
		super();
	}

	public String getMerchantBeneficiaryName() {
		return merchantBeneficiaryName;
	}

	public void setMerchantBeneficiaryName(String merchantBeneficiaryName) {
		this.merchantBeneficiaryName = merchantBeneficiaryName;
	}

	public String getMerchantContactName() {
		return merchantContactName;
	}

	public void setMerchantContactName(String merchantContactName) {
		this.merchantContactName = merchantContactName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getMerchantCellNo() {
		return merchantCellNo;
	}

	public void setMerchantCellNo(String merchantCellNo) {
		this.merchantCellNo = merchantCellNo;
	}

	public String getMerchantEmailId() {
		return merchantEmailId;
	}

	public void setMerchantEmailId(String merchantEmailId) {
		this.merchantEmailId = merchantEmailId;
	}

	public String getUtid() {
		return utid;
	}

	public void setUtid(String utid) {
		this.utid = utid;
	}

	public Double getMdr() {
		return mdr;
	}

	public void setMdr(Double mdr) {
		this.mdr = mdr;
	}

	public Double getGstOnMdr() {
		return gstOnMdr;
	}

	public void setGstOnMdr(Double gstOnMdr) {
		this.gstOnMdr = gstOnMdr;
	}

	public Double getBankApprovalAmount() {
		return bankApprovalAmount;
	}

	public void setBankApprovalAmount(Double bankApprovalAmount) {
		this.bankApprovalAmount = bankApprovalAmount;
	}

	public Double getEDCAmount() {
		return EDCAmount;
	}

	public void setEDCAmount(Double eDCAmount) {
		EDCAmount = eDCAmount;
	}

	public Double getDifferenceInAMount() {
		return differenceInAMount;
	}

	public void setDifferenceInAMount(Double differenceInAMount) {
		this.differenceInAMount = differenceInAMount;
	}

	public Double getCampaignMdr() {
		return campaignMdr;
	}

	public void setCampaignMdr(Double campaignMdr) {
		this.campaignMdr = campaignMdr;
	}

	public Double getCampaignGstOnMdr() {
		return campaignGstOnMdr;
	}

	public void setCampaignGstOnMdr(Double campaignGstOnMdr) {
		this.campaignGstOnMdr = campaignGstOnMdr;
	}

	public Double getAmountToTransfer() {
		return amountToTransfer;
	}

	public void setAmountToTransfer(Double amountToTransfer) {
		this.amountToTransfer = amountToTransfer;
	}

	public Double getCampaignAmountToTransfer() {
		return campaignAmountToTransfer;
	}

	public void setCampaignAmountToTransfer(Double campaignAmountToTransfer) {
		this.campaignAmountToTransfer = campaignAmountToTransfer;
	}

	public Integer getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(Integer transactionCount) {
		this.transactionCount = transactionCount;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}

	public String getSubscriptionPlan() {
		return subscriptionPlan;
	}

	public void setSubscriptionPlan(String subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}

	public Double getSubscriptionFee() {
		return subscriptionFee;
	}

	public void setSubscriptionFee(Double subscriptionFee) {
		this.subscriptionFee = subscriptionFee;
	}

	public String getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public String getChainName() {
		return chainName;
	}

	public void setChainName(String chainName) {
		this.chainName = chainName;
	}

	public Double getOtherDeductions() {
		return otherDeductions;
	}

	public void setOtherDeductions(Double otherDeductions) {
		this.otherDeductions = otherDeductions;
	}

	public String getDeductionRemarks() {
		return deductionRemarks;
	}

	public void setDeductionRemarks(String deductionRemarks) {
		this.deductionRemarks = deductionRemarks;
	}

	public Double getFinalTransferAmount() {
		return finalTransferAmount;
	}

	public void setFinalTransferAmount(Double finalTransferAmount) {
		this.finalTransferAmount = finalTransferAmount;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getPaymentRefNo() {
		return paymentRefNo;
	}

	public void setPaymentRefNo(int paymentRefNo) {
		this.paymentRefNo = paymentRefNo;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getDrAcNo() {
		return drAcNo;
	}

	public void setDrAcNo(String drAcNo) {
		this.drAcNo = drAcNo;
	}

	public Double getAggrAmount() {
		return aggrAmount;
	}

	public void setAggrAmount(Double aggrAmount) {
		this.aggrAmount = aggrAmount;
	}

	public Double getFinalAggrTxfrAmount() {
		return finalAggrTxfrAmount;
	}

	public void setFinalAggrTxfrAmount(Double finalAggrTxfrAmount) {
		this.finalAggrTxfrAmount = finalAggrTxfrAmount;
	}

	public String getBeneficiaryCode() {
		return beneficiaryCode;
	}

	public void setBeneficiaryCode(String beneficiaryCode) {
		this.beneficiaryCode = beneficiaryCode;
	}

	public String getiFSCCode() {
		return iFSCCode;
	}

	public void setiFSCCode(String iFSCCode) {
		this.iFSCCode = iFSCCode;
	}

	public String getDebitNarration() {
		return debitNarration;
	}

	public void setDebitNarration(String debitNarration) {
		this.debitNarration = debitNarration;
	}

	public String getCreditNarration() {
		return creditNarration;
	}

	public void setCreditNarration(String creditNarration) {
		this.creditNarration = creditNarration;
	}

	public String getEnrichment1() {
		return enrichment1;
	}

	public void setEnrichment1(String enrichment1) {
		this.enrichment1 = enrichment1;
	}

	public String getEnrichment2() {
		return enrichment2;
	}

	public void setEnrichment2(String enrichment2) {
		this.enrichment2 = enrichment2;
	}

	public String getCellSpacing() {
		return cellSpacing;
	}

	public void setCellSpacing(String cellSpacing) {
		this.cellSpacing = cellSpacing;
	}

}