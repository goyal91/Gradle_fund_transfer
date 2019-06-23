package com.innoviti.retail.fundTransfer.repository.mis;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innoviti.retail.fundTransfer.model.mis.TransactionError;

public interface TransactionErrorDao extends JpaRepository<TransactionError, String> {

}
