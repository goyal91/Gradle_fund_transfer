/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innoviti.retail.fundTransfer.repository.mis;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.innoviti.retail.fundTransfer.model.mis.FundTransferAggregate;
import com.innoviti.retail.fundTransfer.model.mis.FundTransferAggregateComposite;

public interface FundTransferAggregateDao extends JpaRepository<FundTransferAggregate, FundTransferAggregateComposite> {

	@Query(value = "SELECT fta.fundTransferAggregateComposite.utid as utid , SUM(fta.noOfTransactions) , SUM(fta.edcAmount) , SUM(fta.aggrAmount) , SUM(fta.differenceInAmount) , "
			+ "SUM(fta.aggrAmountToTxnfer) ,SUM(fta.campaignAggrAmountToTxnfer) , SUM(fta.aggrMdr) , SUM(fta.campaignAggrMdr) ,SUM(fta.aggrGst),SUM(fta.campaignAggrGst) ,"
			+ "SUM(fta.aggrBrandTxnAmt) ,SUM(fta.aggrMerchantTxnAmt),SUM(fta.aggrBrandMdrAmt),SUM(fta.aggrMerchantMdrAmt),SUM(fta.aggrBrandGstAmt),SUM(fta.aggrMerchantGstAmt),"
			+ "SUM(fta.aggrBrandTxferAmt),SUM(fta.aggrMerchantTxferAmt), fta.fundTransferAggregateComposite.aggrStartTime , fta.fundTransferAggregateComposite.aggrEndTime ,"
			+ "fta.crtupdDt ,fta.crtupdUser FROM FundTransferAggregate fta  WHERE fta.txnTimestamp >= ?1 AND fta.txnTimestamp < ?2 AND fta.fundTransferAggregateComposite.acqBankCode NOT IN ('21','40') "
			+ "GROUP BY fta.fundTransferAggregateComposite.utid ORDER BY fta.fundTransferAggregateComposite.utid , fta.crtupdDt DESC")
	List<Object> getFundTransferDetails(Date startDate, Date endDate);

	@Query(value = "SELECT distinct utid FROM fund_txfr_aggr where txn_timestamp >= ?1 AND txn_timestamp < ?2  AND acq_bank_code NOT IN ('21','40') ", nativeQuery = true)
	List<String> getUtidList(Date startDate, Date endDate);

	@Query(value = "SELECT fta.chainId , SUM(fta.noOfTransactions) , SUM(fta.edcAmount) , SUM(fta.aggrAmount) , SUM(fta.differenceInAmount) , "
			+ "SUM(fta.aggrBrandTxnAmt) ,SUM(fta.aggrBrandMdrAmt),SUM(fta.aggrBrandGstAmt),"
			+ "SUM(fta.aggrBrandTxferAmt), fta.fundTransferAggregateComposite.aggrStartTime , fta.fundTransferAggregateComposite.aggrEndTime ,"
			+ "fta.crtupdDt ,fta.crtupdUser FROM FundTransferAggregate fta  WHERE fta.txnTimestamp >= ?1 AND fta.txnTimestamp < ?2 AND fta.fundTransferAggregateComposite.acqBankCode NOT IN ('21','40') "
			+ "AND fta.chainId NOT in (?3) GROUP BY fta.chainId ORDER BY fta.fundTransferAggregateComposite.utid , fta.crtupdDt DESC")
	List<Object> getChainFundTransferDetails(Date startDate, Date endDate, String chainId);

	@Query(value = "SELECT distinct chain_id FROM fund_txfr_aggr where txn_timestamp >= ?1 AND txn_timestamp < ?2  AND acq_bank_code NOT IN ('21','40') AND chain_id = ?3", nativeQuery = true)
	List<String> getChainList(Date startDate, Date endDate, String chainId);

	@Query(value = "SELECT bmr.store_name as storeName,bmr.utid as utid,bmr.txn_comission as txnComission,bmr.mdr_sharing as mdrSharing,SUM(fta.edc_amount) AS edcAmount,SUM(fta.aggr_amount) AS bankApprovalAmount,"
			+ "SUM(fta.campaign_aggr_mdr) AS mdrAmount,SUM(fta.campaign_aggr_gst) AS gstAmount, SUM(fta.aggr_brand_txn_amt) AS brandTxnAmount,SUM(fta.aggr_brand_mdr_amt) AS brandMdrAmount,"
			+ "SUM(fta.aggr_brand_gst_amt) AS brandGstAmount,SUM(fta.aggr_brand_txfer_amt) AS brandTxfrAmount, SUM(fta.aggr_merchant_txn_amt) AS merchantTxnAmount,SUM(fta.aggr_merchant_mdr_amt) AS merchantMdrAmount,"
			+ "SUM(fta.aggr_merchant_gst_amt) AS merchantGstAmount, SUM(fta.aggr_merchant_txfer_amt) AS merchantTxfrAmount ,SUM(difference_in_amount) AS differenceInAmount,SUM(fta.no_of_transactions),bmr.brand_store_id "
			+ "FROM fund_txfr_aggr fta RIGHT JOIN brand_mdr_rules bmr ON fta.utid=bmr.utid AND fta.txn_timestamp >= ?1 AND fta.txn_timestamp < ?2 AND fta.chain_id =?3 AND "
			+ "NOW() BETWEEN bmr.start_date AND bmr.end_date AND fta.acq_bank_code NOT IN ('21','40') GROUP BY bmr.utid ORDER BY bmr.utid", nativeQuery = true)
	List<Object> getBrandFundAggregation(Date startDate, Date endDate, String chainId);

	@Query(value = "SELECT fta.fundTransferAggregateComposite.utid as utid , SUM(fta.aggrBrandTxferAmt),SUM(fta.aggrMerchantTxferAmt),fta.chainId FROM FundTransferAggregate fta  WHERE fta.txnTimestamp >= ?1 AND "
			+ "fta.txnTimestamp < ?2 AND fta.fundTransferAggregateComposite.acqBankCode NOT IN ('21','40') AND fta.fundTransferAggregateComposite.utid IN (?3) "
			+ "GROUP BY fta.fundTransferAggregateComposite.utid ORDER BY fta.fundTransferAggregateComposite.utid , fta.crtupdDt DESC")
	List<Object> getFundTransferDetails(Date startDate, Date endDate, List<String> utids);
	
	
	@Query(value = "SELECT bmr.store_name as storeName,bmr.utid as utid,bmr.txn_comission as txnComission,bmr.mdr_sharing as mdrSharing,SUM(fta.ci_amount) AS edcAmount,SUM(fta.bo_amount) AS bankApprovalAmount,"
			+ "SUM(fta.campaign_mdr_rate_amount) AS mdrAmount,SUM(fta.campaign_gst_on_mdr) AS gstAmount, SUM(fta.brand_txn_amt) AS brandTxnAmount,SUM(fta.brand_mdr_amt) AS brandMdrAmount,"
			+ "SUM(fta.brand_gst_amt) AS brandGstAmount,SUM(fta.brand_txfer_amt) AS brandTxfrAmount, SUM(fta.merchant_txn_amt) AS merchantTxnAmount,SUM(fta.merchant_mdr_amt) AS merchantMdrAmount,"
			+ "SUM(fta.merchant_gst_amt) AS merchantGstAmount, SUM(fta.merchant_txfer_amt) AS merchantTxfrAmount ,SUM(difference_in_amount) AS differenceInAmount,COUNT(*),bmr.brand_store_id "
			+ "FROM ftx_txn_mdr fta JOIN brand_mdr_rules bmr ON fta.utid=bmr.utid AND fta.txn_timestamp >= ?1 AND fta.txn_timestamp < ?2 AND fta.chain_id =?3 AND "
			+ "NOW() BETWEEN bmr.start_date AND bmr.end_date AND fta.acq_bank_code NOT IN ('21','40') GROUP BY bmr.utid ORDER BY bmr.utid", nativeQuery = true)
	List<Object> getBrandFundAggregationAnyDate(Date startDate, Date endDate, String chainId);

}
