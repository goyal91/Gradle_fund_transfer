package com.innoviti.retail.fundTransfer.repository.mis;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innoviti.retail.fundTransfer.model.mis.ChargeTransaction;

public interface ChargeTransactionDao extends JpaRepository<ChargeTransaction, BigInteger> {

}
