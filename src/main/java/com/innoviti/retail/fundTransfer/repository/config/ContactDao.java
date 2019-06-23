package com.innoviti.retail.fundTransfer.repository.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.innoviti.retail.fundTransfer.model.config.ContactComposite;
import com.innoviti.retail.fundTransfer.model.config.ContactDetails;

public interface ContactDao extends JpaRepository<ContactDetails, ContactComposite> {

	@Query(value = "SELECT s.store_name as storeName,c.cont_name AS contactName,c.cont_mobphn AS mobilePhone, c.cont_email AS contactEmail , utl.utid as utid "
			+ " ,ct.chain_name AS chainName from unipay_terminal_lookup utl inner join store s on utl.store_code = s.store_code left outer join contact c"
			+ " on s.store_code = c.store_code JOIN chain_type ct ON c.chain_id=ct.chain_id where c.mer_id IN (?2) AND s.store_name not like '%uplilcat%' and "
			+ "s.store_name not like '%Donotuse%' and s.store_name not like '%Donot use%' AND s.store_name not like '%Do not use%' AND "
			+ "( c.store_code is null or (c.store_code is not null AND c.cont_email not like '%@innoviti.com' AND c.cont_email not like '%con')) AND utl.utid in (?1)", nativeQuery = true)
	ArrayList<Object> getMerchantDetailsforInnovitiPos(List<String> utid, String merId);

}
