/*Table structure for table `txn_error` */

DROP TABLE IF EXISTS `txn_error`;

CREATE TABLE `txn_error` (
  `prim_id` varchar(42) NOT NULL,
  `trigger_name` varchar(100) DEFAULT NULL,
  `crtupd_dt` datetime DEFAULT NULL,
  `crtupd_user` varchar(20) DEFAULT 'SYSTEM',
  `error_code` int(11) DEFAULT NULL,
  `error_message` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`prim_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-----------------------------------------------------------------------------------------------------------
/*Table structure for table `txn_mdr_rates` */

DROP TABLE IF EXISTS `txn_mdr_rates`;
-----------------------------------------------------------------------------------------------------------
/*This table will have all the MDR rates at UTID level with utid = 'UTID-ALL' as deafult. If there is any campaign going on at particular city or at mcc code or at zone level, 
we need to fetch all utid's lying in that domain and needs to insert data in this table for each utid. */
CREATE TABLE `txn_mdr_rates` (
  `utid` varchar(12) NOT NULL,
  `card_type` varchar(15) NOT NULL DEFAULT 'CREDIT',
  `scheme_code` varchar(50) NOT NULL DEFAULT 'NULL',
  `mdr` double NOT NULL,
  `transaction_type` varchar(15) NOT NULL DEFAULT 'EFT',
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL DEFAULT '9999-12-31 00:00:00',
  `min_amount` double NOT NULL DEFAULT '0',
  `max_amount` double NOT NULL DEFAULT '99999999999',
  `crtupd_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `crtupd_user` varchar(20) NOT NULL DEFAULT 'SYSTEM',
  PRIMARY KEY (`utid`,`card_type`,`transaction_type`,`start_date`,`end_date`,`min_amount`,`max_amount`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-----------------------------------------------------------------------------------------------------------

/*Data for the table `txn_mdr_rates` */

INSERT  INTO `txn_mdr_rates`(`utid`,`card_type`,`scheme_code`,`mdr`,`transaction_type`,`start_date`,`end_date`,`min_amount`,`max_amount`,`crtupd_dt`,`crtupd_user`) 
VALUES 
('UTID-ALL','CREDIT','NULL',2.25,'EFT',NOW(),'9999-12-31 00:00:00',0,99999999,NOW(),'SYSTEM'),
('UTID-ALL','CREDIT','NULL',2.75,'EMI',NOW(),'9999-12-31 00:00:00',0,99999999,NOW(),'SYSTEM'),
('UTID-ALL','DEBIT','NULL',0,'EFT',NOW(),'9999-12-31 00:00:00',0,2000,NOW(),'SYSTEM'),
('UTID-ALL','DEBIT','NULL',0.9,'EFT',NOW(),'9999-12-31 00:00:00',2000.01,99999999,NOW(),'SYSTEM'),
('UTID-ALL','PREPAID','NULL',0,'EFT',NOW(),'9999-12-31 00:00:00',0,2000,NOW(),'SYSTEM'),
('UTID-ALL','PREPAID','NULL',0.9,'EFT',NOW(),'9999-12-31 00:00:00',2000.01,99999999,NOW(),'SYSTEM'),
('UTID-ALL','AGGR-DEFAULT','NULL',0.9,'EFT',NOW(),'9999-12-31 00:00:00',0,99999999,NOW(),'SYSTEM'),
('UTID-ALL','NON-AGGR','NULL',0,'EFT',NOW(),'9999-12-31 00:00:00',0,99999999,NOW(),'SYSTEM');
-----------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS `txn_mdr_config`;
-----------------------------------------------------------------------------------------------------------
/*This table will have all configurable values which we are using in triggers to calculate MDR at transaction level.*/


CREATE TABLE `txn_mdr_config` (
  `property` varchar(20) NOT NULL,
  `value` varchar(20) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL DEFAULT '9999-12-31 00:00:00',
  `crtupd_dt` datetime DEFAULT CURRENT_TIMESTAMP,
  `CRTUPD_user` varchar(20) DEFAULT 'SYSTEM',
  PRIMARY KEY (`property`,`value`,`start_date`,`end_date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-----------------------------------------------------------------------------------------------------------

/*Data for the table `txn_mdr_config` */

insert  into `txn_mdr_config`(`property`,`value`,`start_date`,`end_date`,`crtupd_dt`,`CRTUPD_user`)
 values ('acq_bank_code','05',NOW(),'9999-12-31 00:00:00',NOW(),'SYSTEM'),
('acq_bank_code','19',NOW(),'9999-12-31 00:00:00',NOW(),'SYSTEM'),
('acq_bank_code','31',NOW(),'9999-12-31 00:00:00',NOW(),'SYSTEM'),
( 'acq_bank_code','32',NOW(),'9999-12-31 00:00:00',NOW(),'SYSTEM'),
('gst','0.18',NOW(),'9999-12-31 00:00:00',NOW(),'SYSTEM'),
('startTime','%Y-%m-%d 18:00:00',NOW(),'9999-12-31 00:00:00',NOW(),'SYSTEM'),
('endTime','%Y-%m-%d 18:00:00',NOW(),'9999-12-31 00:00:00','2018-12-10 15:46:14','SYSTEM');
-----------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS `ftx_txn_mdr`;
-----------------------------------------------------------------------------------------------------------
/*This table will have all the entries of successful transactions with MDR percentage, MDR amount & GST amount.
If any campaign is going on then it will have campaign MDR percentage, MDR amount & GST amount*/

CREATE TABLE `ftx_txn_mdr` (
  `prim_id` varchar(42) NOT NULL,
  `utid` varchar(12) NOT NULL,
  `btid` varchar(12) NOT NULL,
  `batchsrlno` int(6) NOT NULL,
  `acq_bank_code` varchar(4) NOT NULL,
  `ci_amount` varchar(12) DEFAULT NULL,
  `bo_amount` varchar(12) DEFAULT NULL,
  `difference_in_amount` varchar(12) DEFAULT NULL,
  `ci_inno_processingcode` varchar(6) DEFAULT NULL,
  `transaction_type` varchar(50) DEFAULT NULL,
  `scheme_code` varchar(50) DEFAULT 'NULL',
  `input_rate` double NOT NULL DEFAULT '0',
  `output_rate` double NOT NULL DEFAULT '0',
  `campaign_output_rate` double NOT NULL DEFAULT '0',
  `input_rate_amount` double NOT NULL DEFAULT '0',
  `output_rate_amount` double NOT NULL DEFAULT '0',
  `campaign_output_rate_amount` double NOT NULL DEFAULT '0',
  `gst_on_mdr` double NOT NULL DEFAULT '0',
  `campaign_gst_on_mdr` double NOT NULL DEFAULT '0',
  `txn_amount_before_tax` double NOT NULL DEFAULT '0',
  `txn_amount_after_tax` double NOT NULL DEFAULT '0',
  `campaign_txn_amount_before_tax` double NOT NULL DEFAULT '0',
  `campaign_txn_amount_after_tax` double NOT NULL DEFAULT '0',
  `txn_timestamp` datetime DEFAULT NULL,
  `crtupd_dt` datetime DEFAULT NULL,
  `crtupd_user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`prim_id`,`utid`,`btid`,`batchsrlno`,`acq_bank_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-----------------------------------------------------------------------------------------------------------

/*Table structure for table `fund_txfr_aggr` */

DROP TABLE IF EXISTS `fund_txfr_aggr`;
-----------------------------------------------------------------------------------------------------------

CREATE TABLE `fund_txfr_aggr` (
  `utid` varchar(12) NOT NULL,
  `scheme_code` varchar(50) DEFAULT 'NULL',
  `acq_bank_code` int(4) NOT NULL DEFAULT '0',
  `no_of_transactions` int(6) NOT NULL DEFAULT '0',
  `edc_amount` double NOT NULL DEFAULT '0',
  `aggr_amount` double NOT NULL DEFAULT '0',
  `difference_in_amount` double NOT NULL DEFAULT '0',
  `aggr_amount_to_txfer` double NOT NULL DEFAULT '0',
  `campaign_aggr_amount_to_txfer` double NOT NULL DEFAULT '0',
  `aggr_mdr` double NOT NULL DEFAULT '0',
  `campaign_aggr_mdr` double NOT NULL DEFAULT '0',
  `aggr_gst` double NOT NULL DEFAULT '0',
  `campaign_aggr_gst` double NOT NULL DEFAULT '0',
  `aggr_start_time` datetime NOT NULL,
  `aggr_end_time` datetime NOT NULL,
  `txn_timestamp` datetime DEFAULT NULL,
  `crtupd_dt` datetime DEFAULT NULL,
  `crtupd_user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`utid`,`acq_bank_code`,`aggr_start_time`,`aggr_end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-----------------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS insert_ftx_txn_mdr_on_update_transaction_process;
-----------------------------------------------------------------------------------------------------------
/*This trigger will execute on transaction_process. It will insert new record in ftx_txn_mdr table for every successful transaction.*/
DELIMITER $$
CREATE  TRIGGER  insert_ftx_txn_mdr_on_update_transaction_process
AFTER UPDATE ON transaction_process
FOR EACH ROW
BEGIN 
   	DECLARE txn_amount_In_RS DOUBLE;
	DECLARE edc_txn_amount_in_rs DOUBLE;
	DECLARE difference_in_amount DOUBLE;
	DECLARE cashBack DOUBLE;
	DECLARE output_rate DOUBLE;
   	DECLARE output_rate_amount DOUBLE;
	DECLARE campaign_output_rate DOUBLE;
   	DECLARE campaign_output_rate_amount DOUBLE;
  	DECLARE txn_amount_before_tax DOUBLE;
	DECLARE gst DOUBLE;
	DECLARE gst_amount DOUBLE;
	DECLARE campaign_gst_amount DOUBLE;
	DECLARE txn_amount_after_tax DOUBLE;  
	DECLARE campaign_txn_amount_before_tax DOUBLE;
	DECLARE campaign_txn_amount_after_tax DOUBLE;
	DECLARE errorCode CHAR(5) DEFAULT '00000';
	DECLARE errorMessage TEXT DEFAULT '';
	DECLARE scheme_code VARCHAR(50) DEFAULT NULL;
	
	
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
		BEGIN
			GET DIAGNOSTICS CONDITION 1
			errorCode = RETURNED_SQLSTATE, errorMessage = MESSAGE_TEXT;
		END;
    
    IF NEW.transaction_status = '00000000' AND NEW.ci_inno_processingcode IN ('000000','000001','000002','000003','500000')  AND NEW.txn_timestamp IS NOT NULL THEN
	
			SET txn_amount_In_RS = getTxnAmount(NEW.bo_amount,NEW.ci_inno_processingcode,NEW.parent_prim_id);
			
			SET edc_txn_amount_in_rs = getTxnAmount(NEW.ci_amount,NEW.ci_inno_processingcode,NEW.parent_prim_id);
			
			SET difference_in_amount = edc_txn_amount_in_rs - txn_amount_In_RS;
						
			CALL getOutputRate(NEW.bin_no,NEW.acq_bank_code,NEW.ci_inno_processingcode,txn_amount_In_RS,NEW.ci_tender_mode,NEW.txn_timestamp,NEW.parent_prim_id,@output_rate);
			
			SET output_rate = @output_rate;
			
			SET output_rate_amount = (output_rate/100) * edc_txn_amount_in_rs;
			
			SELECT VALUE INTO gst FROM txn_mdr_config WHERE property='gst' AND NEW.txn_timestamp BETWEEN start_date AND end_date;
			
			SET gst_amount = gst * output_rate_amount;
			
			SET txn_amount_before_tax = edc_txn_amount_in_rs - output_rate_amount;
			
			SET txn_amount_after_tax = txn_amount_before_tax -  gst_amount;		
			
			CALL getCampaignData(NEW.bin_no,NEW.ci_inno_processingcode,txn_amount_In_RS,NEW.ci_tender_mode,NEW.txn_timestamp,NEW.parent_prim_id,NEW.ci_utid,@scheme_code,@campaign_output_rate);
			
			SET campaign_output_rate = @campaign_output_rate;
			
			SET scheme_code = @scheme_code;
			
			/* if campaign output rate is null then we are assigning default mdr rate i.e output rate for that particular transaction.*/
			IF campaign_output_rate IS NOT NULL THEN
			
				SET campaign_output_rate_amount = (campaign_output_rate/100) * edc_txn_amount_in_rs;				
				SET campaign_txn_amount_before_tax = edc_txn_amount_in_rs - campaign_output_rate_amount;					
				SET campaign_gst_amount = gst * campaign_output_rate_amount;  						    
				SET campaign_txn_amount_after_tax = campaign_txn_amount_before_tax - campaign_gst_amount;
			
			ELSE 
			
				SET campaign_output_rate = output_rate;
				SET campaign_output_rate_amount = output_rate_amount;
				SET campaign_gst_amount = gst_amount;
				SET campaign_txn_amount_before_tax = txn_amount_before_tax;
				SET campaign_txn_amount_after_tax = txn_amount_after_tax;				
			
			END IF;				
        
			INSERT INTO ftx_txn_mdr  (prim_id, 				
			                          utid, 					
									  btid, 					
									  batchsrlno, 				
									  acq_bank_code, 
									  ci_amount,
									  bo_amount, 
									  difference_in_amount,
									  ci_inno_processingcode, 	
									  transaction_type, 
									  scheme_code,									  
									  input_rate, 				
									  output_rate, 		
									  campaign_output_rate,
									  input_rate_amount, 		
									  output_rate_amount, 
									  campaign_output_rate_amount,	
									  gst_on_mdr, 		
									  campaign_gst_on_mdr,
									  txn_amount_before_tax, 	
									  txn_amount_after_tax, 
									  campaign_txn_amount_before_tax,
									  campaign_txn_amount_after_tax,
									  txn_timestamp, 			
									  crtupd_dt, 				
									  crtupd_user 			
									)								  							  
			VALUES
									( NEW.prim_id,
									  NEW.ci_utid,
									  NEW.bi_btid, 
									  NEW.batchsrl,
									  NEW.acq_bank_code,
									  edc_txn_amount_in_rs,
									  txn_amount_In_RS,
									  difference_in_amount,
									  NEW.ci_inno_processingcode,
									  NEW.transaction_type,
									  scheme_code,
									  0.00,
									  output_rate,
									  campaign_output_rate,
									  0.00,
									  output_rate_amount,
									  campaign_output_rate_amount,
									  gst_amount,
									  campaign_gst_amount,
									  txn_amount_before_tax,
									  txn_amount_after_tax,
									  campaign_txn_amount_before_tax,
									  campaign_txn_amount_after_tax,
									  NEW.txn_timestamp,
									  NOW(),
									  "SYSTEM"
									);
									
	IF errorCode != '00000' THEN
		INSERT  INTO `txn_error`(`prim_id`,`trigger_name`,`crtupd_dt`,`crtupd_user`,`error_code`,`error_message`)
		VALUES 
		(NEW.prim_id,'insert_ftx_txn_mdr_on_update_transaction_process',NOW(),'SYSTEM',errorCode,errorMessage);
	END IF;
	
    END IF;

END $$
DELIMITER ;
-----------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS getOutputRate;
-----------------------------------------------------------------------------------------------------------
/*This function helps to find out the default MDR rates for any transaction happens on Innoviti POS terminal*/
DELIMITER $$
CREATE 
    PROCEDURE getOutputRate(IN bin_no VARCHAR(6),IN acq_bank_code VARCHAR(4),IN ci_inno_processingcode VARCHAR(6),IN txn_amount_In_RS DOUBLE,IN ci_tender_mode VARCHAR(30), 
IN txn_timestamp DATETIME ,IN parent_prim_id VARCHAR(42) ,OUT output_rate DOUBLE) 
    BEGIN
DECLARE cardType VARCHAR(30);
DECLARE outputRate VARCHAR(30);
	
    SELECT credit_debit INTO cardType FROM issuing_bank ib WHERE ib.bin_no=bin_no limit 1;
       
	IF ci_inno_processingcode IN ('000001','000002','000003') THEN 		
		SELECT ftm.output_rate INTO outputRate FROM ftx_txn_mdr ftm WHERE ftm.prim_id = parent_prim_id;
		
	ELSEIF ci_inno_processingcode IN ('000000','500000') THEN  		
	  
		SELECT mdr INTO outputRate FROM txn_mdr_rates t WHERE t.utid= 'UTID-ALL' AND  t.card_type=cardType AND txn_amount_In_RS BETWEEN min_amount AND max_amount 
		AND txn_timestamp BETWEEN start_date AND end_date AND t.transaction_type = ci_tender_mode ;			
		
	END IF;
    
    IF outputRate IS NULL THEN 
		
		IF acq_bank_code IN (SELECT VALUE FROM txn_mdr_config WHERE property=acq_bank_code AND txn_timestamp BETWEEN start_date AND end_date) THEN
			SET cardType = 'AGGR-DEFAULT';
		ELSE
			SET cardType = 'NON-AGGR';
		END IF;
		
		SELECT mdr INTO outputRate FROM txn_mdr_rates t WHERE t.utid= 'UTID-ALL' AND card_type=cardType AND txn_amount_In_RS BETWEEN min_amount AND max_amount 
		AND txn_timestamp BETWEEN start_date AND end_date;		
   END IF;
   
   SET output_rate = outputRate;
   
     END$$
DELIMITER ;
-----------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS getCampaignData;
-----------------------------------------------------------------------------------------------------------
/*This function helps to find out the campaign MDR rates for any transaction happens on Innoviti POS terminal at UTID level. is there is no campaign going on 
then it will return null.*/
DELIMITER $$
CREATE 
    PROCEDURE getCampaignData(IN bin_no INT(6),IN ci_inno_processingcode VARCHAR(6),IN txn_amount_In_RS DOUBLE,IN ci_tender_mode VARCHAR(30),
IN txn_timestamp DATETIME,IN parent_prim_id VARCHAR(42),IN ci_utid VARCHAR(12),OUT scheme_code VARCHAR(50),OUT output_rate DOUBLE) 
    BEGIN
DECLARE cardType VARCHAR(30);

	
    SELECT credit_debit INTO cardType FROM issuing_bank ib WHERE ib.bin_no=bin_no limit 1;   
	IF ci_inno_processingcode IN ('000001','000002','000003') THEN 
	
		SELECT ftm.campaign_output_rate,ftm.scheme_code INTO output_rate , scheme_code FROM ftx_txn_mdr ftm WHERE ftm.prim_id = parent_prim_id;	
		
	ELSEIF  ci_inno_processingcode IN ('000000','500000') THEN  
	
		SELECT mdr , t.scheme_code INTO output_rate,scheme_code FROM txn_mdr_rates t WHERE t.utid= ci_utid AND  t.card_type=cardType AND txn_amount_In_RS BETWEEN min_amount AND max_amount 
		AND txn_timestamp BETWEEN start_date AND end_date AND t.transaction_type = ci_tender_mode ;   
	END IF;	
    END$$
DELIMITER ;
-----------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS getTxnAmount;
-----------------------------------------------------------------------------------------------------------
/* This function is used to convert amount from transaction_process table into INR.*/
DELIMITER $$
CREATE FUNCTION getTxnAmount(bo_amount VARCHAR(12),ci_inno_processingcode VARCHAR(6), parent_prim_id VARCHAR(42)) RETURNS DOUBLE
DETERMINISTIC
BEGIN
	DECLARE txn_amount DOUBLE;
	DECLARE txn_amount_In_RS DOUBLE;
	
	SET txn_amount_In_RS = bo_amount / 100;
			
	   IF ci_inno_processingcode IN ('000001','000002','000003','500001','500002','500003') THEN

		SELECT tp.ci_amount INTO txn_amount FROM transaction_process tp WHERE tp.prim_id=parent_prim_id AND tp.transaction_status = '00000000';
		SET txn_amount_In_RS = 0 - (txn_amount/ 100);

	   END IF;		
	RETURN (txn_amount_In_RS);	
	
END $$
DELIMITER ;
-----------------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS insert_ftx_txn_aggr_on_insert_ftx_txn_mdr;
-----------------------------------------------------------------------------------------------------------
/* This trigger will calculate the aggregated amount of all  transactions happened at UTID and acq_bank_code level between 6 pm from previous day to 6 pm of current day..
First time it will insert the new record and post that it keeps on updating the same.*/
DELIMITER $$
CREATE
    TRIGGER insert_ftx_txn_aggr_on_insert_ftx_txn_mdr AFTER INSERT
    ON ftx_txn_mdr
    FOR EACH ROW 
    BEGIN    
	DECLARE startTime DATETIME;
	DECLARE endTime DATETIME;
	DECLARE errorCode CHAR(5) DEFAULT '00000';
	DECLARE errorMessage TEXT DEFAULT '';
	
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
		BEGIN
			GET DIAGNOSTICS CONDITION 1
			errorCode = RETURNED_SQLSTATE, errorMessage = MESSAGE_TEXT;
		END;
		
	SET startTime = getstartDate(NEW.txn_timestamp);
	
	SET endTime = getEndDate(NEW.txn_timestamp);
    
	IF 
	(SELECT 1  FROM fund_txfr_aggr fa WHERE fa.utid = NEW.utid AND fa.aggr_start_time = startTime AND fa.aggr_end_time= endTime AND fa.acq_bank_code =NEW.acq_bank_code) IS NULL THEN
	
	INSERT INTO fund_txfr_aggr ( utid,
				     scheme_code,
				     acq_bank_code,
				     no_of_transactions,
				     edc_amount,
				     aggr_amount,
				     difference_in_amount,
				     aggr_amount_to_txfer,
				     campaign_aggr_amount_to_txfer,
				     aggr_mdr,
				     campaign_aggr_mdr,
				     aggr_gst,
				     campaign_aggr_gst,
				     aggr_start_time,
				     aggr_end_time,
					 txn_timestamp,
				     crtupd_dt,
				     crtupd_user
				    )
	VALUES                      
				    ( NEW.utid,
				      NEW.scheme_code,
				      NEW.acq_bank_code,
				      1,
				      NEW.ci_amount,
				      NEW.bo_amount,
				      NEW.difference_in_amount,
				      NEW.txn_amount_after_tax,
				      NEW.campaign_txn_amount_after_tax,
				      NEW.output_rate_amount,
				      NEW.campaign_output_rate_amount,
				      NEW.gst_on_mdr,
				      NEW.campaign_gst_on_mdr,
				      startTime,
				      endTime,
					  NEW.txn_timestamp,
				      NOW(),
				      "SYSTEM"
				    );
	
	
	ELSE			
	UPDATE fund_txfr_aggr fa SET aggr_amount = fa.aggr_amount + (NEW.bo_amount), 
				   no_of_transactions = no_of_transactions + 1,
				   edc_amount = edc_amount + NEW.ci_amount,
				   difference_in_amount = difference_in_amount + NEW.difference_in_amount,
				   aggr_amount_to_txfer = fa.aggr_amount_to_txfer + NEW.txn_amount_after_tax,
				   campaign_aggr_amount_to_txfer = fa.campaign_aggr_amount_to_txfer + NEW.campaign_txn_amount_after_tax,
				   aggr_mdr = fa.aggr_mdr + NEW.output_rate_amount,
				   campaign_aggr_mdr = fa.campaign_aggr_mdr + NEW.campaign_output_rate_amount,
				   aggr_gst = fa.aggr_gst +NEW.gst_on_mdr,
				   campaign_aggr_gst = fa.campaign_aggr_gst + NEW.campaign_gst_on_mdr,
				   scheme_code = NEW.scheme_code,
				   txn_timestamp = NEW.txn_timestamp,
				   crtupd_dt = NOW()
	WHERE fa.utid = NEW.utid AND fa.aggr_start_time <= startTime AND fa.aggr_end_time >= endTime AND fa.acq_bank_code =NEW.acq_bank_code ;
	
	IF errorCode != '00000' THEN
		INSERT  INTO `txn_error`(`prim_id`,`trigger_name`,`crtupd_dt`,`crtupd_user`,`error_code`,`error_message`)
		VALUES 
		(NEW.prim_id,'insert_ftx_txn_aggr_on_insert_ftx_txn_mdr',NOW(),'SYSTEM',errorCode,errorMessage);
	END IF;
	END IF;
	
    END$$
DELIMITER ;
-----------------------------------------------------------------------------------------------------------
DROP FUNCTION IF EXISTS getstartDate;
-----------------------------------------------------------------------------------------------------------
/*This function is used to get the startTIme of the settlement report */ 
DELIMITER $$
CREATE FUNCTION  getstartDate(txn_timestamp DATETIME) RETURNS DATETIME
DETERMINISTIC
BEGIN
	DECLARE startTime DATETIME;
	DECLARE timeformat VARCHAR(20);
	
	SELECT VALUE INTO timeformat FROM txn_mdr_config WHERE property='startTime' AND txn_timestamp BETWEEN start_date AND end_date;
	
		IF DATE_FORMAT(NOW(),timeformat) >= NOW() THEN
		SET startTime = DATE_ADD(DATE_FORMAT(NOW(),timeformat),INTERVAL -1 DAY);
		
	ELSEIF NOW() > DATE_FORMAT(NOW(),timeformat) THEN
		SET startTime = DATE_FORMAT(NOW(),timeformat);
		
	END IF;	
	
	RETURN (startTime);
END
-----------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS getEndDate;
-----------------------------------------------------------------------------------------------------------
/*This function is used to get the startTIme of the settlement report */ 
DELIMITER $$
CREATE FUNCTION  getEndDate(txn_timestamp DATETIME) RETURNS DATETIME
DETERMINISTIC
BEGIN
	DECLARE endTime DATETIME;
	DECLARE timeformat VARCHAR(20);
	
	SELECT VALUE INTO timeformat FROM txn_mdr_config WHERE property='endTime' AND txn_timestamp BETWEEN start_date AND end_date;
	
		IF DATE_FORMAT(NOW(),timeformat) >= NOW() THEN
		SET endTime = DATE_FORMAT(NOW(),timeformat);		
		
	ELSEIF NOW() > DATE_FORMAT(NOW(),timeformat) THEN
		SET endTime = DATE_ADD(DATE_FORMAT(NOW(),timeformat),INTERVAL 1 DAY);
		
	END IF;	
	
	RETURN (endTime);
END
-----------------------------------------------------------------------------------------------------------