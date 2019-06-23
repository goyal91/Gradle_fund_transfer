package com.innoviti.retail.fundTransfer.utils;

public class ReportConstants {

	public static final String NA = "NA";

	public static final String INPUT_DATE_FORMAT = "yyyy-MM-dd 18:00:00";

	public static final String[] sheetNames = { "Fund Txfr Details-InnovitiPOS", "Txn Details-InnovitiPOS",
			"OYO Aggr Sheet", "OYO Txn Details-InnovitiPOS" };

	public static final String[][] aggrHeaderColumns = { { "Chain Name", "getChainName" },
			{ "Store Name", "getStoreName" }, { "UTID", "getUtid" }, { "SUBSCRIPTIONPLAN", "getSubscriptionPlan" },
			{ "SUBDate", "getSubscriptionDate" }, { "SUBFee", "getSubscriptionFee" },
			{ "Bank Approval Amount", "getBankApprovalAmount" }, { "EDC Amount", "getEDCAmount" },
			{ "Discount / Cashback", "getDifferenceInAMount" }, { "No of Transactions", "getTransactionCount" },
			{ "Aggregate MDR", "getMdr" }, { "Aggregate GST", "getGstOnMdr" },
			{ "Aggregate Amount to Txfr", "getAmountToTransfer" }, { "Campaign Aggregate MDR", "getCampaignMdr" },
			{ "Campaign Aggregate GST", "getCampaignGstOnMdr" },
			{ "Campaign Aggregate Amount to Txfr", "getCampaignAmountToTransfer" },
			{ "Other Deductions", "getOtherDeductions" }, { "Final Txfr Amount", "getFinalTransferAmount" },
			{ "Transfer Date", "getTransferDate" }, { "", "getCellSpacing" }, { "Client_Code", "getClientCode" },
			{ "Product_Code", "getProductCode" }, { "Payment_Type", "getPaymentType" },
			{ "Payment_Ref_No", "getPaymentRefNo" }, { "Payment_Date", "getPaymentDate" }, { "Dr_Ac_No", "getDrAcNo" },
			{ "Aggr_Amount", "getFinalAggrTxfrAmount" }, { "Final_Txfr_Amount", "getFinalTransferAmount" },
			{ "Beneficiary_Code", "getBeneficiaryCode" }, { "Beneficiary_Name", "getMerchantBeneficiaryName" },
			{ "IFSC Code", "getIfscCode" }, { "Beneficiary_Acc_No", "getAccountNumber" },
			{ "Beneficiary_Email", "getMerchantEmailId" }, { "Beneficiary_Mobile", "getMerchantCellNo" },
			{ "Debit_Narration", "getDebitNarration" }, { "Credit_Narration", "getCreditNarration" },
			{ "Enrichment_1", "getEnrichment1" }, { "Enrichment_2", "getEnrichment2" } };

	public static final String[][] brandAggrHeaderColumns = { { "OYO Id", "getBrandStoreId" },
			{ "Store Name", "getStoreName" }, { "UTID", "getUtid" }, { "No of Transactions", "getTransactionCount" },
			{ "Bill Amount", "getEdcAmount" }, { "Bank Approval Amount", "getBankApprovalAmount" },
			{ "Discount/Cashback", "getDifferenceInAmount" }, { "MDR Amount", "getMdrAmount" },
			{ "Brand Txn Commission %", "getTxnComission" }, { "Brand MDR Commission %", "getMdrSharing" },
			{ "Brand Txn Amount", "getBrandTxnAmount" }, { "Brand MDR Amount", "getBrandMdrAmount" },
			{ "GST on MDR Amount", "getBrandGstAmount" }, { "Brand Other Deductions", "getBrandOtherDeductions" },
			{ "GST on Other Deductions", "getBrandDeductionsGST" },
			{ "Other Deduction Remarks", "getBrandDeductionRemarks" },
			{ "Final Brand Transfer Amount", "getFinalBrandTransferAmount" },
			{ "Merchant Txn Amount", "getMerchantTxnAmount" }, { "Merchant Mdr Amount", "getMerchantMdrAmount" },
			{ "GST on MDR Amount", "getMerchantGstAmount" },
			{ "Merchant Other Deductions", "getMerchantOtherDeductions" },
			{ "GST on Other Deductions", "getMerchantDeductionsGst" },
			{ "Other Deduction Remarks", "getMerchantDeductionRemarks" },
			{ "Final Merchant Transfer Amount", "getFinalMerchantTransferAmount" } };

	public static final String[][] txnHeaderColumns = { { "Prim Id", "getPrimId" }, { "Store Name", "getStoreName" },
			{ "UTID", "getUtid" }, { "Edc Amount", "getCiAmount" }, { "Bank Approval Amount", "getBoAmount" },
			{ "Difference in Amount", "getDifferenceInAmount" }, { "BTID", "getBtid" },
			{ "BatchSrlNo", "getBatchSrlNo" }, { "AcqBankCode", "getAcqBankCode" },
			{ "ciInnoProcessingCode", "getCiInnoProcessingcode" }, { "UpiTxnId", "getUpiTxnId" },
			{ "RRN number", "getBoRrnNumber" }, { "Auth code", "getBoApprovalCode" },
			{ "Bank Merchant ID", "getBiBankMerchantId" }, { "Card Number", "getCiMaskCardNumber" },
			{ "Card Type", "getCardType" }, { "Card Logo", "getLogo" }, { "Tender Mode", "getTenderMode" },
			{ "Scheme Code", "getSchemeCode" }, { "MDR Rate", "getMdrRate" }, { "MDR Amount", "getMdrRateAmount" },
			{ "GST on MDR", "getGstOnMdr" }, { "Final Txfr Amount", "getTxnAmountToTxfer" },
			{ "Campaign MDR Rate", "getCampaignMdrRate" }, { "Campaign MDR Amount", "getCampaignMdrRateAmount" },
			{ "Campaign GST on MDR", "getCampaignGstOnMdr" },
			{ "Campaign Final Txfr Amount", "getCampaignTxnAmountToTxfer" }, { "Txn Timestamp", "getTxnTimestamp" } };

	public static final String[][] brandTxnHeaderColumns = { { "Txn Id", "getPrimId" },
			{ "Store Name", "getStoreName" }, { "UTID", "getUtid" }, { "Bill Amount", "getCiAmount" },
			{ "Bank Approval Amount", "getBoAmount" }, { "Discount/Cashback", "getDifferenceInAmount" },
			{ "UpiTxnId", "getUpiTxnId" }, { "RRN number", "getBoRrnNumber" }, { "Auth code", "getBoApprovalCode" },
			{ "Bank Merchant ID", "getBiBankMerchantId" }, { "Card Number", "getCiMaskCardNumber" },
			{ "Card Type", "getCardType" }, { "Card Scheme", "getSchemeCode" }, { "Txn Type", "getTenderMode" },
			{ "Scheme Code", "getSchemeCode" }, { "MDR Rate", "getCampaignMdrRate" },
			{ "MDR Amount", "getCampaignMdrRateAmount" }, { "GST on MDR", "getCampaignGstOnMdr" },
			{ "Final Transfer Amount", "getCampaignTxnAmountToTxfer" }, { "Txn Timestamp", "getTxnTimestamp" },
			{ "Brand Commission Scheme", "getBrandCommissionScheme" },
			{ "Brand Txn Commission%", "getBrandTxnCommRate" }, { "Brand MDR Commission %", "getBrandMdrCommRate" },
			{ "Brand Commission Amount", "getBrandTxnAmt" }, { "Brand MDR Amount", "getBrandMdrAmt" },
			{ "GST Amount", "getBrandGstAmt" }, { "Brand Transfer Amount", "getBrandTxferAmt" },
			{ "Merchant Txn Amount", "getMerchantTxnAmt" }, { "Merchant Mdr Amount", "getMerchantMdrAmt" },
			{ "Merchant GST Amount", "getMerchantGstAmt" }, { "Merchant Transfer Amount", "getMerchantTxferAmt" } };
}
