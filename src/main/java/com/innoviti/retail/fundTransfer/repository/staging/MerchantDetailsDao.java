package com.innoviti.retail.fundTransfer.repository.staging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.innoviti.retail.fundTransfer.model.staging.MerchantAccountDetails;
import com.innoviti.retail.fundTransfer.model.staging.MerchantAccountDetailsComposite;

public interface MerchantDetailsDao extends JpaRepository<MerchantAccountDetails, MerchantAccountDetailsComposite> {

	@Query(value = "SELECT * FROM merchant_account_details WHERE store_code IN (?1) ", nativeQuery = true)
	List<MerchantAccountDetails> getMerchantAccountDetails(List<String> storeCode);

	@Query(value = "SELECT b.chain_id,b.brand_name,b.account_number,b.ifsc_code,b.beneficiary_name,c.cont_name,c.cont_email,c.cont_mobphn FROM `brand_details` b JOIN `contact_staging` c ON "
			+ "c.chain_id=b.chain_id WHERE c.store_code=9 AND b.chain_id in (?1)", nativeQuery = true)
	List<Object> getBrandDetails(List<String> chainList);

	@Query(value = "SELECT pd.pricing_type AS pricing_type, pd.rate AS rate, p.crtupd_dt AS subscriptionDate , p.utid AS utid FROM "
			+ "pricing_details pd JOIN pragati_utid_pricing_mapping p ON p.pricing_code = pd.pricing_code WHERE p.utid IN (?1) GROUP BY "
			+ "pd.pricing_type , pd.rate, p.utid ORDER BY p.crtupd_dt DESC", nativeQuery = true)
	List<Object> getSubscriptionPlanDetails(List<String> utidList);

}
