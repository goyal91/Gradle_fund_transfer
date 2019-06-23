package com.innoviti.retail.fundTransfer.utils;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandFundDto implements Serializable, IFundTransferDto {

	private static final long serialVersionUID = -3844961716302513688L;

	private String storeName = "";
	private String brandStoreId = "";
	private String utid;
	private String brandSchemeName = "";
	private Double txnComission;
	private Double mdrSharing;
	private Double edcAmount = 0.0;
	private Double bankApprovalAmount = 0.0;
	private Double differenceInAmount = 0.0;
	private Double mdrAmount = 0.0;
	private Double gstAmount = 0.0;
	private Double brandTxnAmount = 0.0;
	private Double brandMdrAmount = 0.0;
	private Double brandGstAmount = 0.0;
	private Double brandTxfrAmount = 0.0;
	private Double merchantTxnAmount = 0.0;
	private Double merchantMdrAmount = 0.0;
	private Double merchantGstAmount = 0.0;
	private Double merchantTxfrAmount = 0.0;
	private Double merchantOtherDeductions = 0.0;
	private Double merchantDeductionsGst = 0.0;
	private String merchantDeductionRemarks = "";
	private Double finalMerchantTransferAmount = 0.0;
	private Double brandOtherDeductions = 0.0;
	private Double brandDeductionsGST = 0.0;
	private String brandDeductionRemarks = "";
	private Double finalBrandTransferAmount = 0.0;
	private Integer transactionCount = 0;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getUtid() {
		return utid;
	}

	public void setUtid(String utid) {
		this.utid = utid;
	}

	public Double getTxnComission() {
		return txnComission;
	}

	public void setTxnComission(Double txnComission) {
		this.txnComission = txnComission;
	}

	public Double getMdrSharing() {
		return mdrSharing;
	}

	public void setMdrSharing(Double mdrSharing) {
		this.mdrSharing = mdrSharing;
	}

	public Double getEdcAmount() {
		return edcAmount;
	}

	public void setEdcAmount(Double edcAmount) {
		this.edcAmount = edcAmount;
	}

	public Double getBankApprovalAmount() {
		return bankApprovalAmount;
	}

	public void setBankApprovalAmount(Double bankApprovalAmount) {
		this.bankApprovalAmount = bankApprovalAmount;
	}

	public Double getMdrAmount() {
		return mdrAmount;
	}

	public void setMdrAmount(Double mdrAmount) {
		this.mdrAmount = mdrAmount;
	}

	public Double getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(Double gstAmount) {
		this.gstAmount = gstAmount;
	}

	public Double getBrandTxnAmount() {
		return brandTxnAmount;
	}

	public void setBrandTxnAmount(Double brandTxnAmount) {
		this.brandTxnAmount = brandTxnAmount;
	}

	public Double getBrandMdrAmount() {
		return brandMdrAmount;
	}

	public void setBrandMdrAmount(Double brandMdrAmount) {
		this.brandMdrAmount = brandMdrAmount;
	}

	public Double getBrandGstAmount() {
		return brandGstAmount;
	}

	public void setBrandGstAmount(Double brandGstAmount) {
		this.brandGstAmount = brandGstAmount;
	}

	public Double getBrandTxfrAmount() {
		return brandTxfrAmount;
	}

	public void setBrandTxfrAmount(Double brandTxfrAmount) {
		this.brandTxfrAmount = brandTxfrAmount;
	}

	public Double getMerchantTxnAmount() {
		return merchantTxnAmount;
	}

	public void setMerchantTxnAmount(Double merchantTxnAmount) {
		this.merchantTxnAmount = merchantTxnAmount;
	}

	public Double getMerchantMdrAmount() {
		return merchantMdrAmount;
	}

	public void setMerchantMdrAmount(Double merchantMdrAmount) {
		this.merchantMdrAmount = merchantMdrAmount;
	}

	public Double getMerchantGstAmount() {
		return merchantGstAmount;
	}

	public void setMerchantGstAmount(Double merchantGstAmount) {
		this.merchantGstAmount = merchantGstAmount;
	}

	public Double getMerchantTxfrAmount() {
		return merchantTxfrAmount;
	}

	public void setMerchantTxfrAmount(Double merchantTxfrAmount) {
		this.merchantTxfrAmount = merchantTxfrAmount;
	}

	public Double getDifferenceInAmount() {
		return differenceInAmount;
	}

	public void setDifferenceInAmount(Double differenceInAmount) {
		this.differenceInAmount = differenceInAmount;
	}

	public String getBrandSchemeName() {
		return brandSchemeName;
	}

	public void setBrandSchemeName(String brandSchemeName) {
		this.brandSchemeName = brandSchemeName;
	}

	public String getMerchantDeductionRemarks() {
		return merchantDeductionRemarks;
	}

	public void setMerchantDeductionRemarks(String merchantDeductionRemarks) {
		this.merchantDeductionRemarks = merchantDeductionRemarks;
	}

	public void setBrandDeductionRemarks(String brandDeductionRemarks) {
		this.brandDeductionRemarks = brandDeductionRemarks;
	}

	public Integer getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(Integer transactionCount) {
		this.transactionCount = transactionCount;
	}

	public String getBrandStoreId() {
		return brandStoreId;
	}

	public void setBrandStoreId(String brandStoreId) {
		this.brandStoreId = brandStoreId;
	}

	public Double getMerchantOtherDeductions() {
		return merchantOtherDeductions;
	}

	public void setMerchantOtherDeductions(Double merchantOtherDeductions) {
		this.merchantOtherDeductions = merchantOtherDeductions;
	}

	public Double getMerchantDeductionsGst() {
		return merchantDeductionsGst;
	}

	public void setMerchantDeductionsGst(Double merchantDeductionsGst) {
		this.merchantDeductionsGst = merchantDeductionsGst;
	}

	public Double getFinalMerchantTransferAmount() {
		return finalMerchantTransferAmount;
	}

	public void setFinalMerchantTransferAmount(Double finalMerchantTransferAmount) {
		this.finalMerchantTransferAmount = finalMerchantTransferAmount;
	}

	public Double getBrandOtherDeductions() {
		return brandOtherDeductions;
	}

	public void setBrandOtherDeductions(Double brandOtherDeductions) {
		this.brandOtherDeductions = brandOtherDeductions;
	}

	public Double getBrandDeductionsGST() {
		return brandDeductionsGST;
	}

	public void setBrandDeductionsGST(Double brandDeductionsGST) {
		this.brandDeductionsGST = brandDeductionsGST;
	}

	public Double getFinalBrandTransferAmount() {
		return finalBrandTransferAmount;
	}

	public void setFinalBrandTransferAmount(Double finalBrandTransferAmount) {
		this.finalBrandTransferAmount = finalBrandTransferAmount;
	}

	public String getBrandDeductionRemarks() {
		return brandDeductionRemarks;
	}

}