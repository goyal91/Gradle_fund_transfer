package com.innoviti.retail.fundTransfer.repository.mis;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.innoviti.retail.fundTransfer.model.mis.ChargeSchedule;

public interface ChargeScheduleDao extends JpaRepository<ChargeSchedule, BigInteger> {

	@Query(value = "SELECT * FROM charge_schedule WHERE deduction_date <=NOW() AND outstanding_amount >0 ORDER BY deduction_date DESC ,priority DESC , outstanding_amount DESC ;", nativeQuery = true)
	List<ChargeSchedule> getChargesToBeRecoveredToday();

}
