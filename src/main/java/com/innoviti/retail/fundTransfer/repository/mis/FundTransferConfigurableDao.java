package com.innoviti.retail.fundTransfer.repository.mis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.innoviti.retail.fundTransfer.model.mis.FundTransferConfigurable;
import com.innoviti.retail.fundTransfer.model.mis.FundTransferConfigurableComposite;

public interface FundTransferConfigurableDao
		extends JpaRepository<FundTransferConfigurable, FundTransferConfigurableComposite> {

	@Query(value = "SELECT value FROM txn_mdr_config WHERE property = ?1 and NOW() BETWEEN start_date AND end_date", nativeQuery = true)
	public String getDate(String time);

}
