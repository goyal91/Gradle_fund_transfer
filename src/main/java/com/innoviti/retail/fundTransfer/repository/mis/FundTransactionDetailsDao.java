package com.innoviti.retail.fundTransfer.repository.mis;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.innoviti.retail.fundTransfer.model.mis.FundTransactionDetails;
import com.innoviti.retail.fundTransfer.model.mis.FundTransactionDetailsComposite;

public interface FundTransactionDetailsDao
		extends JpaRepository<FundTransactionDetails, FundTransactionDetailsComposite> {

	@Query(value = "SELECT * FROM ftx_txn_mdr where txn_timestamp >= ?1 AND txn_timestamp < ?2 AND acq_bank_code NOT IN ('21','40') AND utid IN (?3) AND chain_id = ?4 ORDER BY utid,txn_timestamp", nativeQuery = true)
	List<FundTransactionDetails> getFundTransactionDetails(Date startDate, Date endDate, List<String> utidList,
			String chainId);

	@Query(value = "SELECT * FROM ftx_txn_mdr where txn_timestamp >= ?1 AND txn_timestamp < ?2 AND acq_bank_code NOT IN ('21','40') AND utid IN (?3) AND chain_id = ?4 ORDER BY utid,txn_timestamp", nativeQuery = true)
	List<FundTransactionDetails> getOyoFundTransactionDetails(Date startDate, Date endDate, List<String> utidList,
			String chainId);

}
