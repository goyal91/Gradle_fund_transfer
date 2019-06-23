/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innoviti.retail.fundTransfer.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.innoviti.retail.fundTransfer.fileGeneration.ExcelFileGenerationImpl;
import com.innoviti.retail.fundTransfer.model.mis.ChargeSchedule;
import com.innoviti.retail.fundTransfer.model.mis.ChargeTransaction;
import com.innoviti.retail.fundTransfer.model.mis.FundTransactionDetails;
import com.innoviti.retail.fundTransfer.model.mis.TransactionError;
import com.innoviti.retail.fundTransfer.model.staging.MerchantAccountDetails;
import com.innoviti.retail.fundTransfer.repository.config.ContactDao;
import com.innoviti.retail.fundTransfer.repository.mis.ChargeScheduleDao;
import com.innoviti.retail.fundTransfer.repository.mis.ChargeTransactionDao;
import com.innoviti.retail.fundTransfer.repository.mis.FundTransactionDetailsDao;
import com.innoviti.retail.fundTransfer.repository.mis.FundTransferAggregateDao;
import com.innoviti.retail.fundTransfer.repository.mis.TransactionErrorDao;
import com.innoviti.retail.fundTransfer.repository.staging.MerchantDetailsDao;
import com.innoviti.retail.fundTransfer.utils.BrandFundDto;
import com.innoviti.retail.fundTransfer.utils.FundTransferDto;
import com.innoviti.retail.fundTransfer.utils.MailUtils;
import com.innoviti.retail.fundTransfer.utils.TransactionDetailsDto;

@Service
public class FundTransferServiceImpl implements FundTransferService {
	private static Logger logger = LoggerFactory.getLogger(FundTransferServiceImpl.class);

	@Autowired
	FundTransferAggregateDao fundTransferAggregateDao;

	@Autowired
	FundTransactionDetailsDao fundTransactionDetailsDao;

	@Autowired
	MerchantDetailsDao merchantDetailsDao;

	@Autowired
	ContactDao contactDao;

	@Autowired
	ChargeScheduleDao chargeScheduleDao;

	@Autowired
	ChargeTransactionDao chargeTransactionDao;

	@Autowired
	TransactionErrorDao transactionErrorDao;

	@Autowired
	ExcelFileGenerationImpl excelFileGeneration;

	@Value("${report.email.subject}")
	private String reportEmailSubject;

	@Value("${report.email.recipient}")
	private String reportEmailRecipient;

	@Value("${report.email.sender}")
	private String reportEmailSender;

	@Value("${report.email.sender.password}")
	private String reportEmailSenderPassword;

	@Value("${chain.id}")
	private String chainId;

	@Value("${brand.chain.id}")
	private String brandChainId;

	@Value("${mer.id}")
	private String merId;

	@Value("${oyo.report.email.subject}")
	private String brandReportEmailSubject;

	@Value("${oyo.report.email.recipient}")
	private String brandReportEmailRecipient;

	@Value("${brand.report.email.recipient.cc}")
	private String brandReportEmailRecipientCC;

	@Value("${report.email.body}")
	private String emailBody;

	@Value("${brand.report.email.body}")
	private String brandEmailBody;

	List<ChargeTransaction> chargeTransactions = new ArrayList<>();

	List<ChargeSchedule> chargesScheduledToday = new ArrayList<>();

	List<TransactionError> errors = new ArrayList<>();
	/**
	 * variable to store charged amount on daily basis at UTID level from Merchant
	 */
	HashMap<String, BigDecimal> utidChargedAmount = new HashMap<>();
	/**
	 * variable to store charged amount Gst on daily basis at UTID level from
	 * Merchant
	 */
	HashMap<String, BigDecimal> utidChargedAmountGst = new HashMap<>();
	/**
	 * variable to store remarks for charged amount at UTID level from Merchant
	 */
	HashMap<String, String> utidChargedRemarks = new HashMap<>();
	/**
	 * variable to store charged amount on daily basis at UTID level from Brand
	 */
	HashMap<String, BigDecimal> utidBrandChargedAmount = new HashMap<>();
	/**
	 * variable to store charged amount Gst on daily basis at UTID level from Brand
	 */
	HashMap<String, BigDecimal> utidBrandChargedAmountGst = new HashMap<>();
	/**
	 * variable to store remarks for charged amount at UTID level from Brand
	 */
	HashMap<String, String> utidBrandChargedRemarks = new HashMap<>();
	/**
	 * variable to store charged amount on daily basis at Chain level from Brand
	 */
	HashMap<String, BigDecimal> brandChargedAmount = new HashMap<>();
	// HashMap<String, String> brandChargedRemarks = new HashMap<>();

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formatter2 = new SimpleDateFormat("ddMMyyyy");

	@Override
	public void generateFundTransferFile(Date startDate, Date endDate, String emailCheck) {

		List<Object> fundTransferDetails = new ArrayList<>();
		List<Object> brandFundTransferDetails = new ArrayList<>();
		List<FundTransactionDetails> fundTransactionDetails = new ArrayList<>();

		List<FundTransactionDetails> brandFundTransactionDetails = new ArrayList<>();
		int counter = 0;

		try {

			fundTransferDetails = fundTransferAggregateDao.getFundTransferDetails(startDate, endDate);

			brandFundTransferDetails = fundTransferAggregateDao.getBrandFundAggregation(startDate, endDate,
					brandChainId);

			if (!(fundTransferDetails.size() > 0)) {
				logger.info("There are no transactions happened between " + startDate + " and " + endDate);
				return;
			}
			logger.info("Fund Transfer Aggregate Size :" + fundTransferDetails.size());

			ListIterator<Object> fundTransferIterator = fundTransferDetails.listIterator();

			ListIterator<Object> brandFundTransferIterator = brandFundTransferDetails.listIterator();

			List<FundTransferDto> fundTransferDtos = new ArrayList<FundTransferDto>();

			List<BrandFundDto> brandFundTransferDtos = new ArrayList<BrandFundDto>();

			List<String> utidList = new ArrayList<>();
			List<String> storeCode = new ArrayList<>();
			HashMap<String, Object> merchantDetailsInfo = new HashMap<>();
			HashMap<String, MerchantAccountDetails> merchantAccountInfo = new HashMap<>();
			HashMap<String, String> storeDetails = new HashMap<>();
			utidList = fundTransferAggregateDao.getUtidList(startDate, endDate);
			for (String store : utidList) {
				storeCode.add(store.substring(0, 6));
			}
			List<MerchantAccountDetails> merchantAccountDetails = merchantDetailsDao
					.getMerchantAccountDetails(storeCode);
			logger.info("Account Details List size :" + merchantAccountDetails.size());
			ListIterator<MerchantAccountDetails> merchantAccountDetailsIterator = merchantAccountDetails.listIterator();

			while (merchantAccountDetailsIterator.hasNext()) {
				MerchantAccountDetails merchantAccountDetail = merchantAccountDetailsIterator.next();
				merchantAccountInfo.put(merchantAccountDetail.getMerchantAccountDetailsComposite().getStoreCode(),
						merchantAccountDetail);

			}

			List<Object> merchantDetails = new ArrayList<>();

			merchantDetails = contactDao.getMerchantDetailsforInnovitiPos(utidList, merId);
			ListIterator<Object> merchantDetailsIterator = merchantDetails.listIterator();
			utidList.clear();
			while (merchantDetailsIterator.hasNext()) {
				Object[] merchant = (Object[]) merchantDetailsIterator.next();
				merchantDetailsInfo.put(merchant[4].toString(), merchant);
				utidList.add(merchant[4].toString());
				storeDetails.put(merchant[4].toString(), merchant[0].toString());
			}
			List<Object> subscriptionDetails = new ArrayList<>();
			subscriptionDetails = merchantDetailsDao.getSubscriptionPlanDetails(utidList);
			HashMap<String, Object> subscriptionPlanDetails = new HashMap<>();

			ListIterator<Object> subscriptionDetailsIterator = subscriptionDetails.listIterator();
			logger.info("Subscription Details fetched");
			while (subscriptionDetailsIterator.hasNext()) {
				Object[] subscriptionDetail = (Object[]) subscriptionDetailsIterator.next();
				subscriptionPlanDetails.put(subscriptionDetail[3].toString(), subscriptionDetail);

			}

			fundTransactionDetails = fundTransactionDetailsDao.getFundTransactionDetails(startDate, endDate, utidList,
					chainId);
			logger.info("Innoviti POS Txn List size :" + fundTransactionDetails.size());

			List<TransactionDetailsDto> fundTransactionDetailDtos = new ArrayList<TransactionDetailsDto>();

			generateTransactionDetailsDto(fundTransactionDetails, fundTransactionDetailDtos, storeDetails);

			brandFundTransactionDetails = fundTransactionDetailsDao.getFundTransactionDetails(startDate, endDate,
					utidList, brandChainId);

			logger.info("Brand Txn List size :" + brandFundTransactionDetails.size());

			List<TransactionDetailsDto> brandFundTransactionDetailDtos = new ArrayList<TransactionDetailsDto>();

			generateTransactionDetailsDto(brandFundTransactionDetails, brandFundTransactionDetailDtos, storeDetails);

			List<ChargeSchedule> chargesToBeRecoveredToday = chargeScheduleDao.getChargesToBeRecoveredToday();
			HashMap<String, List<ChargeSchedule>> chargesDeductionsofUtid = new HashMap<>();
			if (chargesToBeRecoveredToday.size() > 0) {
				for (ChargeSchedule chargeSchedule : chargesToBeRecoveredToday) {
					List<ChargeSchedule> chargeScheduled = new ArrayList<>();
					chargeScheduled.add(chargeSchedule);
					if (chargesDeductionsofUtid.containsKey(chargeSchedule.getUtid())
							&& chargeSchedule.getBearer() == 'M') {
						chargeScheduled.addAll(chargesDeductionsofUtid.get(chargeSchedule.getUtid()));
					} else if (chargesDeductionsofUtid.containsKey(chargeSchedule.getChainId())
							&& chargeSchedule.getBearer() == 'B') {
						chargeScheduled.addAll(chargesDeductionsofUtid.get(chargeSchedule.getChainId()));
					}
					if (chargeSchedule.getBearer() == 'M') {
						chargesDeductionsofUtid.put(chargeSchedule.getUtid(), chargeScheduled);
					} else if (chargeSchedule.getBearer() == 'B') {
						chargesDeductionsofUtid.put(chargeSchedule.getChainId(), chargeScheduled);
					}

				}
			}

			while (fundTransferIterator.hasNext()) {
				FundTransferDto fundTransferDto = new FundTransferDto();
				Object[] fundTransferAggregate = (Object[]) fundTransferIterator.next();
				if (merchantDetailsInfo.containsKey(fundTransferAggregate[0].toString().toUpperCase())) {
					Object[] merchant = (Object[]) merchantDetailsInfo
							.get(fundTransferAggregate[0].toString().toUpperCase());
					fundTransferDto.setStoreName(merchant[0].toString());
					if (null != merchant[1]) {
						fundTransferDto.setMerchantContactName(merchant[1].toString());
						fundTransferDto.setMerchantCellNo(merchant[2].toString());
						fundTransferDto.setMerchantEmailId(merchant[3].toString());
						fundTransferDto.setChainName(merchant[5].toString());
					}
				} else {
					continue;
				}
				if (subscriptionPlanDetails.containsKey(fundTransferAggregate[0].toString().toUpperCase())) {
					Object[] subscriptionData = (Object[]) subscriptionPlanDetails
							.get(fundTransferAggregate[0].toString().toUpperCase());
					fundTransferDto.setSubscriptionPlan(subscriptionData[0].toString());
					fundTransferDto.setSubscriptionFee(Double.valueOf(subscriptionData[1].toString()));
					fundTransferDto.setSubscriptionDate(subscriptionData[2].toString());
				}

				fundTransferDto.setUtid(fundTransferAggregate[0].toString().toUpperCase());
				fundTransferDto.setTransactionCount(Integer.valueOf(fundTransferAggregate[1].toString()));
				fundTransferDto.setEDCAmount(Double.valueOf(fundTransferAggregate[2].toString()));
				fundTransferDto.setBankApprovalAmount(Double.valueOf(fundTransferAggregate[3].toString()));
				fundTransferDto.setDifferenceInAMount(Double.valueOf(fundTransferAggregate[4].toString()));
				fundTransferDto.setAmountToTransfer(Double.valueOf(fundTransferAggregate[5].toString()));
				fundTransferDto.setCampaignAmountToTransfer(Double.valueOf(fundTransferAggregate[18].toString()));
				fundTransferDto.setMdr(Double.valueOf(fundTransferAggregate[7].toString()));
				fundTransferDto.setCampaignMdr(Double.valueOf(fundTransferAggregate[14].toString()));
				fundTransferDto.setGstOnMdr(Double.valueOf(fundTransferAggregate[9].toString()));
				fundTransferDto.setCampaignGstOnMdr(Double.valueOf(fundTransferAggregate[16].toString()));
				fundTransferDto.setTransferDate(fundTransferAggregate[20].toString());
				fundTransferDto.setCreditNarration("Innoviti-POS" + formatter2.format(new Date()));
				fundTransferDto.setAggrAmount(Double.valueOf(fundTransferAggregate[5].toString()));
				fundTransferDto.setPaymentRefNo(++counter);
				fundTransferDto.setPaymentDate(formatter1.format(new Date()));

				if (fundTransferDto.getStoreName().length() > 23) {
					fundTransferDto.setDebitNarration(fundTransferDto.getUtid() + formatter2.format(new Date())
							+ fundTransferDto.getStoreName().substring(0, 23));
				} else {
					fundTransferDto.setDebitNarration(
							fundTransferDto.getUtid() + formatter2.format(new Date()) + fundTransferDto.getStoreName());
				}

				if (chargesDeductionsofUtid.containsKey(fundTransferAggregate[0].toString().toUpperCase())) {
					chargesToBeDeducted(startDate, endDate,
							chargesDeductionsofUtid.get(fundTransferAggregate[0].toString().toUpperCase()),
							Double.valueOf(fundTransferAggregate[18].toString()), true, false);
					if (null != utidChargedAmount.get(fundTransferAggregate[0].toString().toUpperCase())) {
						fundTransferDto.setOtherDeductions(
								utidChargedAmount.get(fundTransferAggregate[0].toString().toUpperCase())
										.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()
										+ utidChargedAmountGst.get(fundTransferAggregate[0].toString().toUpperCase())
												.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
						fundTransferDto.setFinalTransferAmount(
								fundTransferDto.getCampaignAmountToTransfer() - fundTransferDto.getOtherDeductions());
						fundTransferDto.setFinalAggrTxfrAmount(
								fundTransferDto.getAmountToTransfer() - fundTransferDto.getOtherDeductions());
					}

				} else {
					fundTransferDto.setFinalTransferAmount(fundTransferDto.getCampaignAmountToTransfer());
					fundTransferDto.setFinalAggrTxfrAmount(fundTransferDto.getAmountToTransfer());
				}

				if (merchantAccountInfo
						.containsKey(fundTransferAggregate[0].toString().substring(0, 6).toUpperCase())) {
					MerchantAccountDetails merchantAccountDetail = merchantAccountInfo
							.get(fundTransferAggregate[0].toString().substring(0, 6).toUpperCase());
					fundTransferDto.setAccountNumber(
							merchantAccountDetail.getMerchantAccountDetailsComposite().getAccountNumber());
					fundTransferDto
							.setIfscCode(merchantAccountDetail.getMerchantAccountDetailsComposite().getIfscCode());
					fundTransferDto.setMerchantBeneficiaryName(merchantAccountDetail.getBeneficiaryName());
				}

				fundTransferDtos.add(fundTransferDto);
			}

			List<String> chainList = new ArrayList<>();
			chainList = fundTransferAggregateDao.getChainList(startDate, endDate, brandChainId);
			if (chainList.size() > 0) {

				fundTransferDetails.clear();
				fundTransferDetails = fundTransferAggregateDao.getChainFundTransferDetails(startDate, endDate, chainId);
				fundTransferIterator = fundTransferDetails.listIterator();
				List<Object> brandDetails = merchantDetailsDao.getBrandDetails(chainList);
				ListIterator<Object> brandDetailsIterator = brandDetails.listIterator();
				HashMap<String, Object> brandDetailsInfo = new HashMap<>();
				while (brandDetailsIterator.hasNext()) {
					Object[] brand = (Object[]) brandDetailsIterator.next();
					brandDetailsInfo.put(brand[0].toString(), brand);
				}

				while (fundTransferIterator.hasNext()) {

					FundTransferDto fundTransferDto = new FundTransferDto();
					Object[] fundTransferAggregate = (Object[]) fundTransferIterator.next();

					if (brandDetailsInfo.containsKey(fundTransferAggregate[0].toString())) {
						Object[] brand = (Object[]) brandDetailsInfo.get(fundTransferAggregate[0].toString());
						fundTransferDto.setStoreName(brand[1].toString());
						fundTransferDto.setChainName(brand[1].toString());
						fundTransferDto.setAccountNumber(brand[2].toString());
						fundTransferDto.setIfscCode(brand[3].toString());
						fundTransferDto.setMerchantBeneficiaryName(brand[4].toString());
						fundTransferDto.setMerchantContactName(brand[5].toString());
						fundTransferDto.setMerchantEmailId(brand[6].toString());
						fundTransferDto.setMerchantCellNo(brand[7].toString());

					} else {
						continue;
					}

					fundTransferDto.setUtid(fundTransferAggregate[0].toString().toUpperCase());
					fundTransferDto.setTransactionCount(Integer.valueOf(fundTransferAggregate[1].toString()));
					fundTransferDto.setEDCAmount(Double.valueOf(fundTransferAggregate[2].toString()));
					fundTransferDto.setBankApprovalAmount(Double.valueOf(fundTransferAggregate[3].toString()));
					fundTransferDto.setDifferenceInAMount(Double.valueOf(fundTransferAggregate[4].toString()));
					fundTransferDto.setAmountToTransfer(Double.valueOf(fundTransferAggregate[8].toString()));
					fundTransferDto.setCampaignAmountToTransfer(Double.valueOf(fundTransferAggregate[8].toString()));
					fundTransferDto.setMdr(Double.valueOf(fundTransferAggregate[6].toString()));
					fundTransferDto.setCampaignMdr(Double.valueOf(fundTransferAggregate[6].toString()));
					fundTransferDto.setGstOnMdr(Double.valueOf(fundTransferAggregate[7].toString()));
					fundTransferDto.setCampaignGstOnMdr(Double.valueOf(fundTransferAggregate[7].toString()));
					fundTransferDto.setPaymentRefNo(++counter);
					fundTransferDto.setTransferDate(fundTransferAggregate[10].toString());
					fundTransferDto.setCreditNarration("Innoviti-POS" + formatter2.format(new Date()));
					fundTransferDto.setPaymentDate(formatter1.format(new Date()));

					if (fundTransferDto.getStoreName().length() > 23) {
						fundTransferDto.setDebitNarration(fundTransferDto.getUtid() + formatter2.format(new Date())
								+ fundTransferDto.getStoreName().substring(0, 23));
					} else {
						fundTransferDto.setDebitNarration(fundTransferDto.getUtid() + formatter2.format(new Date())
								+ fundTransferDto.getStoreName());
					}
					if (chargesDeductionsofUtid.containsKey(fundTransferAggregate[0].toString())) {
						chargesToBeDeducted(startDate, endDate,
								chargesDeductionsofUtid.get(fundTransferAggregate[0].toString()),
								Double.valueOf(fundTransferAggregate[8].toString()), false, true);
						if (null != brandChargedAmount.get(fundTransferAggregate[0].toString())) {
							fundTransferDto.setOtherDeductions(
									brandChargedAmount.get(fundTransferAggregate[0].toString()).doubleValue());
							fundTransferDto.setFinalTransferAmount(fundTransferDto.getCampaignAmountToTransfer()
									- fundTransferDto.getOtherDeductions());
							fundTransferDto.setFinalAggrTxfrAmount(
									fundTransferDto.getAmountToTransfer() - fundTransferDto.getOtherDeductions());
						}

					} else {
						fundTransferDto.setFinalTransferAmount(fundTransferDto.getCampaignAmountToTransfer());
						fundTransferDto.setFinalAggrTxfrAmount(fundTransferDto.getAggrAmount());
					}

					fundTransferDtos.add(fundTransferDto);

				}
			}

			while (brandFundTransferIterator.hasNext()) {
				BrandFundDto brandFundDto = new BrandFundDto();
				Object[] brandFundTransferAggregate = (Object[]) brandFundTransferIterator.next();
				if (!utidBrandChargedAmount.containsKey(brandFundTransferAggregate[1].toString().toUpperCase())
						&& null == brandFundTransferAggregate[4]) {
					continue;
				}
				brandFundDto.setStoreName(brandFundTransferAggregate[0].toString());
				brandFundDto.setUtid(brandFundTransferAggregate[1].toString());
				brandFundDto.setTxnComission(Double.valueOf(brandFundTransferAggregate[2].toString()));
				brandFundDto.setMdrSharing(Double.valueOf(brandFundTransferAggregate[3].toString()));
				if (null != brandFundTransferAggregate[4]) {
					brandFundDto.setEdcAmount(Double.valueOf(brandFundTransferAggregate[4].toString()));
					brandFundDto.setBankApprovalAmount(Double.valueOf(brandFundTransferAggregate[5].toString()));
					brandFundDto.setMdrAmount(Double.valueOf(brandFundTransferAggregate[6].toString()));
					brandFundDto.setGstAmount(Double.valueOf(brandFundTransferAggregate[7].toString()));
					brandFundDto.setBrandTxnAmount(Double.valueOf(brandFundTransferAggregate[8].toString()));
					brandFundDto.setBrandMdrAmount(Double.valueOf(brandFundTransferAggregate[9].toString()));
					brandFundDto.setBrandGstAmount(Double.valueOf(brandFundTransferAggregate[10].toString()));
					brandFundDto.setBrandTxfrAmount(Double.valueOf(brandFundTransferAggregate[11].toString()));
					brandFundDto.setMerchantTxnAmount(Double.valueOf(brandFundTransferAggregate[12].toString()));
					brandFundDto.setMerchantMdrAmount(Double.valueOf(brandFundTransferAggregate[13].toString()));
					brandFundDto.setMerchantGstAmount(Double.valueOf(brandFundTransferAggregate[14].toString()));
					brandFundDto.setMerchantTxfrAmount(Double.valueOf(brandFundTransferAggregate[15].toString()));
					brandFundDto.setDifferenceInAmount(Double.valueOf(brandFundTransferAggregate[16].toString()));
					brandFundDto.setTransactionCount(Integer.valueOf(brandFundTransferAggregate[17].toString()));
				}
				brandFundDto.setBrandStoreId(brandFundTransferAggregate[18].toString());
				if (utidChargedAmount.containsKey(brandFundTransferAggregate[1].toString().toUpperCase())) {
					brandFundDto.setMerchantOtherDeductions(
							utidChargedAmount.get(brandFundTransferAggregate[1].toString().toUpperCase())
									.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
					brandFundDto.setMerchantDeductionsGst(
							utidChargedAmountGst.get(brandFundTransferAggregate[1].toString().toUpperCase())
									.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
					brandFundDto.setMerchantDeductionRemarks(
							utidChargedRemarks.get(brandFundTransferAggregate[1].toString().toUpperCase()));
				}
				if (utidBrandChargedAmount.containsKey(brandFundTransferAggregate[1].toString().toUpperCase())) {
					brandFundDto.setBrandOtherDeductions(
							utidBrandChargedAmount.get(brandFundTransferAggregate[1].toString().toUpperCase())
									.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
					brandFundDto.setBrandDeductionsGST(
							utidBrandChargedAmountGst.get(brandFundTransferAggregate[1].toString().toUpperCase())
									.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
					brandFundDto.setBrandDeductionRemarks(
							utidBrandChargedRemarks.get(brandFundTransferAggregate[1].toString().toUpperCase()));
				}

				brandFundDto.setFinalMerchantTransferAmount(new BigDecimal(brandFundDto.getMerchantTxfrAmount()
						- (brandFundDto.getMerchantOtherDeductions() + brandFundDto.getMerchantDeductionsGst()))
								.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());

				brandFundDto.setFinalBrandTransferAmount(new BigDecimal(brandFundDto.getBrandTxfrAmount()
						- (brandFundDto.getBrandOtherDeductions() + brandFundDto.getBrandDeductionsGST()))
								.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());

				brandFundTransferDtos.add(brandFundDto);
			}

			List<Object> excelSheetData = new ArrayList<>();
			excelSheetData.add(fundTransferDtos);
			excelSheetData.add(fundTransactionDetailDtos);
			excelSheetData.add(brandFundTransferDtos);
			excelSheetData.add(brandFundTransactionDetailDtos);

			XSSFWorkbook workBook = new XSSFWorkbook();
			excelFileGeneration.generateExcel(workBook, excelSheetData);
			if (emailCheck.equalsIgnoreCase("yes")) {

				MailUtils.sendZipEmailWithAttachment(reportEmailRecipient, "", reportEmailSender,
						reportEmailSenderPassword, reportEmailSubject + "_" + formatter.format(new Date()), emailBody,
						reportEmailSubject + "_" + formatter.format(new Date()), workBook);

				workBook.removeSheetAt(0);
				workBook.removeSheetAt(0);

				String body = brandEmailBody + startDate + " & " + endDate + ".";
				MailUtils.sendZipEmailWithAttachment(brandReportEmailRecipient, brandReportEmailRecipientCC,
						reportEmailSender, reportEmailSenderPassword,
						brandReportEmailSubject + "_" + formatter.format(new Date()), body,
						brandReportEmailSubject + "_" + formatter.format(new Date()), workBook);

				chargeScheduleDao.saveAll(chargesScheduledToday);
				chargeTransactionDao.saveAll(chargeTransactions);
				transactionErrorDao.saveAll(errors);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		logger.info("Exitting Application");
	}

	private void generateTransactionDetailsDto(List<FundTransactionDetails> fundTransactionDetails,
			List<TransactionDetailsDto> fundTransactionDetailDtos, HashMap<String, String> storeDetails) {
		for (FundTransactionDetails fundTransactionDetail : fundTransactionDetails) {
			TransactionDetailsDto transactionDetailsDto = new TransactionDetailsDto();

			transactionDetailsDto.setPrimId(fundTransactionDetail.getFundTransactionDetailsComposite().getPrimId());
			transactionDetailsDto.setStoreName(
					storeDetails.get(fundTransactionDetail.getFundTransactionDetailsComposite().getUtid()));
			transactionDetailsDto.setUtid(fundTransactionDetail.getFundTransactionDetailsComposite().getUtid());
			transactionDetailsDto.setCiAmount(fundTransactionDetail.getCiAmount());
			transactionDetailsDto.setBoAmount(fundTransactionDetail.getBoAmount());
			transactionDetailsDto.setDifferenceInAmount(fundTransactionDetail.getDifferenceInAmount());
			if (chainId.equals(fundTransactionDetail.getChainId())) {
				transactionDetailsDto.setBtid(fundTransactionDetail.getFundTransactionDetailsComposite().getBtid());
				transactionDetailsDto
						.setBatchSrlNo(fundTransactionDetail.getFundTransactionDetailsComposite().getBatchSrlNo());
				transactionDetailsDto
						.setAcqBankCode(fundTransactionDetail.getFundTransactionDetailsComposite().getAcqBankCode());
				transactionDetailsDto.setCiInnoProcessingcode(fundTransactionDetail.getCiInnoProcessingcode());
			}
			transactionDetailsDto.setUpiTxnId(fundTransactionDetail.getUpiTxnId());
			transactionDetailsDto.setBoRrnNumber(fundTransactionDetail.getBoRrnNumber());
			transactionDetailsDto.setBoApprovalCode(fundTransactionDetail.getBoApprovalCode());
			transactionDetailsDto.setBiBankMerchantId(fundTransactionDetail.getBiBankMerchantId());
			transactionDetailsDto.setCiMaskCardNumber(fundTransactionDetail.getCiMaskCardNumber());
			transactionDetailsDto.setCardType(fundTransactionDetail.getCardType());
			transactionDetailsDto.setLogo(fundTransactionDetail.getLogo());
			transactionDetailsDto.setTenderMode(fundTransactionDetail.getTenderMode());
			transactionDetailsDto.setSchemeCode(fundTransactionDetail.getSchemeCode());
			if (chainId.equals(fundTransactionDetail.getChainId())) {
				transactionDetailsDto.setMdrRate(fundTransactionDetail.getMdrRate());
				transactionDetailsDto.setMdrRateAmount(fundTransactionDetail.getMdrRateAmount());
				transactionDetailsDto.setGstOnMdr(fundTransactionDetail.getGstOnMdr());
				transactionDetailsDto.setTxnAmountToTxfer(fundTransactionDetail.getTxnAmountToTxfer());
			}
			transactionDetailsDto.setCampaignMdrRate(fundTransactionDetail.getCampaignMdrRate());
			transactionDetailsDto.setCampaignMdrRateAmount(fundTransactionDetail.getCampaignMdrRateAmount());
			transactionDetailsDto.setCampaignGstOnMdr(fundTransactionDetail.getCampaignGstOnMdr());
			transactionDetailsDto.setCampaignTxnAmountToTxfer(fundTransactionDetail.getCampaignTxnAmountToTxfer());
			transactionDetailsDto.setTxnTimestamp(fundTransactionDetail.getTxnTimestamp().toString());

			if (null != fundTransactionDetail.getBrandCommissionScheme()) {
				transactionDetailsDto.setBrandCommissionScheme(fundTransactionDetail.getBrandCommissionScheme());
				transactionDetailsDto.setBrandTxnCommRate(fundTransactionDetail.getBrandTxnCommRate());
				transactionDetailsDto.setBrandMdrCommRate(fundTransactionDetail.getBrandMdrCommRate());
				transactionDetailsDto.setBrandTxnAmt(fundTransactionDetail.getBrandTxnAmt());
				transactionDetailsDto.setBrandMdrAmt(fundTransactionDetail.getBrandMdrAmt());
				transactionDetailsDto.setBrandGstAmt(fundTransactionDetail.getBrandGstAmt());
				transactionDetailsDto.setBrandTxferAmt(fundTransactionDetail.getBrandTxferAmt());
				transactionDetailsDto.setMerchantTxnAmt(fundTransactionDetail.getMerchantTxnAmt());
				transactionDetailsDto.setMerchantMdrAmt(fundTransactionDetail.getMerchantMdrAmt());
				transactionDetailsDto.setMerchantGstAmt(fundTransactionDetail.getMerchantGstAmt());
				transactionDetailsDto.setMerchantTxferAmt(fundTransactionDetail.getMerchantTxferAmt());
			}
			fundTransactionDetailDtos.add(transactionDetailsDto);
		}

	}

	@Override
	/**
	 * Method to calculate charges to be deducted at UTID/Chain level from Fund
	 * Transfer Amount
	 * 
	 */
	public void chargesToBeDeducted(Date startDate, Date endDate, List<ChargeSchedule> chargeScheduled,
			Double aggrAmount, boolean merchant, boolean brand) {
		logger.info("Entered Charges Deduction Method");
		BigDecimal aggregateAmount = new BigDecimal(aggrAmount);
		for (ChargeSchedule chargeSchedule : chargeScheduled) {
			ChargeTransaction chargeTransaction = new ChargeTransaction();
			BigDecimal chargedAmount = new BigDecimal(0);
			BigDecimal chargedGstAmount = new BigDecimal(0);
			// Double chargedGstAmount = 0.00d;
			if (chargeSchedule.getBearer() == chargeSchedule.getReceiver()) {
				logger.info("Charges to be deducted for Charge Id  " + chargeSchedule.getChargeId()
						+ " is invalid as Beared & Reciever is same");
				TransactionError error = new TransactionError();
				error.setPrimId(chargeSchedule.getUtid() + new Date());
				error.setTriggeName("Charge Scheduled");
				error.setCrtupdDt(new Date());
				error.setCrtupdUser("Fund Transfer Service");
				error.setErrorMessage("Charges to be deducted for Charge Id :" + chargeSchedule.getChargeId()
						+ " is invalid as Bearer & Reciever is same");
				errors.add(error);
				continue;
			}

			if (chargeSchedule.getBearer() == 'M' && merchant) {

				logger.info("Charges to be deducted from Merchant for Utid: " + chargeSchedule.getUtid() + " is "
						+ (chargeSchedule.getOutstandingAmount() + chargeSchedule.getGstOutstandingAmount())
						+ " & aggr amount is " + aggregateAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()
						+ " on " + new Date() + " as " + chargeSchedule.getRemarks());
				// chargesCalculations(chargeSchedule, aggrAmount, chargeTransaction,
				// chargedAmount, chargedGstAmount);

				if (aggregateAmount.doubleValue() >= (chargeSchedule.getOutstandingAmount()
						+ chargeSchedule.getGstOutstandingAmount())) {
					aggregateAmount = aggregateAmount.subtract(new BigDecimal(
							chargeSchedule.getOutstandingAmount() + chargeSchedule.getGstOutstandingAmount()));
					chargedAmount = new BigDecimal(chargeSchedule.getOutstandingAmount());
					chargedGstAmount = new BigDecimal(chargeSchedule.getGstOutstandingAmount());
					chargeTransaction.setChargedAmount(chargeSchedule.getOutstandingAmount());
					chargeTransaction.setChargedGstAmount(chargeSchedule.getGstOutstandingAmount());

				} else if (aggregateAmount.doubleValue() > 0) {
					chargedAmount = new BigDecimal((chargeSchedule.getOutstandingAmount()
							* aggregateAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue())
							/ (chargeSchedule.getOutstandingAmount() + chargeSchedule.getGstOutstandingAmount()));
					chargedGstAmount = aggregateAmount.subtract(chargedAmount);
					chargeTransaction
							.setChargedAmount(chargedAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
					chargeTransaction
							.setChargedGstAmount(chargedGstAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
					aggregateAmount = new BigDecimal(0);

				} else {
					continue;
				}

				if (!utidChargedAmount.containsKey(chargeSchedule.getUtid())) {
					utidChargedAmount.put(chargeSchedule.getUtid(),
							new BigDecimal(chargeTransaction.getChargedAmount()));
					utidChargedAmountGst.put(chargeSchedule.getUtid(),
							new BigDecimal(chargeTransaction.getChargedGstAmount()));
					utidChargedRemarks.put(chargeSchedule.getUtid(), chargeSchedule.getRemarks());
				} else {
					utidChargedAmount.put(chargeSchedule.getUtid(), utidChargedAmount.get(chargeSchedule.getUtid())
							.add(new BigDecimal(chargeTransaction.getChargedAmount())));
					utidChargedAmountGst.put(chargeSchedule.getUtid(),
							utidChargedAmountGst.get(chargeSchedule.getUtid())
									.add(new BigDecimal(chargeTransaction.getChargedGstAmount())));
					utidChargedRemarks.put(chargeSchedule.getUtid(),
							utidChargedRemarks.get(chargeSchedule.getUtid()) + " & " + chargeSchedule.getRemarks());
				}

				if (chargeSchedule.getReceiver() == 'B') {
					utidBrandChargedAmount.put(chargeSchedule.getUtid(),
							new BigDecimal(chargeTransaction.getChargedAmount()).negate());
					utidBrandChargedAmountGst.put(chargeSchedule.getUtid(),
							new BigDecimal(chargeTransaction.getChargedGstAmount()).negate());

					if (!brandChargedAmount.containsKey(chargeSchedule.getChainId())) {
						brandChargedAmount.put(chargeSchedule.getChainId(),
								new BigDecimal(chargeTransaction.getChargedAmount()).negate()
										.subtract(new BigDecimal(chargeTransaction.getChargedGstAmount())));
					} else {
						brandChargedAmount.put(chargeSchedule.getChainId(),
								brandChargedAmount.get(chargeSchedule.getChainId())
										.subtract(new BigDecimal(chargeTransaction.getChargedAmount()))
										.subtract(new BigDecimal(chargeTransaction.getChargedGstAmount())));

					}
					if (null != utidBrandChargedRemarks.get(chargeSchedule.getUtid())) {
						utidBrandChargedRemarks.put(chargeSchedule.getUtid(),
								utidBrandChargedRemarks.get(chargeSchedule.getUtid()) + " & "
										+ chargeSchedule.getRemarks() + " from Utid: " + chargeSchedule.getUtid());
					} else {

						utidBrandChargedRemarks.put(chargeSchedule.getUtid(),
								chargeSchedule.getRemarks() + " from Utid: " + chargeSchedule.getUtid());
					}

				}

			} else if (chargeSchedule.getBearer() == 'B' && brand) {

				logger.info("Charges to be deducted from Brand for Utid: " + chargeSchedule.getUtid() + " is "
						+ (chargeSchedule.getOutstandingAmount() + chargeSchedule.getGstOutstandingAmount())
						+ " & aggr amount is " + aggregateAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()
						+ " on " + new Date() + " as " + chargeSchedule.getRemarks());

				// chargesCalculations(chargeSchedule, aggrAmount, chargeTransaction,
				// chargedAmount, chargedGstAmount);

				if (aggregateAmount.doubleValue() >= (chargeSchedule.getOutstandingAmount()
						+ chargeSchedule.getGstOutstandingAmount())) {
					aggregateAmount = aggregateAmount.subtract(new BigDecimal(
							chargeSchedule.getOutstandingAmount() + chargeSchedule.getGstOutstandingAmount()));
					chargedAmount = new BigDecimal(chargeSchedule.getOutstandingAmount());
					chargedGstAmount = new BigDecimal(chargeSchedule.getGstOutstandingAmount());
					chargeTransaction.setChargedAmount(chargeSchedule.getOutstandingAmount());
					chargeTransaction.setChargedGstAmount(chargeSchedule.getGstOutstandingAmount());

				} else if (aggregateAmount.doubleValue() > 0) {
					chargedAmount = new BigDecimal((chargeSchedule.getOutstandingAmount()
							* aggregateAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue())
							/ (chargeSchedule.getOutstandingAmount() + chargeSchedule.getGstOutstandingAmount()));
					chargedGstAmount = aggregateAmount.subtract(chargedAmount);
					chargeTransaction
							.setChargedAmount(chargedAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
					chargeTransaction
							.setChargedGstAmount(chargedGstAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
					aggregateAmount = new BigDecimal(0);

				} else {
					continue;
				}

				if (!brandChargedAmount.containsKey(chargeSchedule.getChainId())) {
					brandChargedAmount.put(chargeSchedule.getChainId(),
							new BigDecimal(chargeTransaction.getChargedAmount())
									.add(new BigDecimal(chargeTransaction.getChargedGstAmount())));
				} else {
					brandChargedAmount.put(chargeSchedule.getChainId(),
							brandChargedAmount.get(chargeSchedule.getChainId())
									.add(new BigDecimal(chargeTransaction.getChargedAmount()))
									.add(new BigDecimal(chargeTransaction.getChargedGstAmount())));

				}
				if (!utidBrandChargedAmount.containsKey(chargeSchedule.getUtid())) {
					utidBrandChargedAmount.put(chargeSchedule.getUtid(),
							new BigDecimal(chargeTransaction.getChargedAmount()));
					utidBrandChargedAmountGst.put(chargeSchedule.getUtid(),
							new BigDecimal(chargeTransaction.getChargedGstAmount()));

					utidBrandChargedRemarks.put(chargeSchedule.getUtid(), chargeSchedule.getRemarks());
				} else {
					utidBrandChargedAmount.put(chargeSchedule.getUtid(), utidBrandChargedAmount
							.get(chargeSchedule.getUtid()).add(new BigDecimal(chargeTransaction.getChargedAmount())));

					utidBrandChargedAmountGst.put(chargeSchedule.getUtid(),
							utidBrandChargedAmountGst.get(chargeSchedule.getUtid())
									.add(new BigDecimal(chargeTransaction.getChargedGstAmount())));

					utidBrandChargedRemarks.put(chargeSchedule.getUtid(),
							utidBrandChargedRemarks.get(chargeSchedule.getUtid()) + " & "
									+ chargeSchedule.getRemarks());
				}
			}
			chargeTransaction.setChargeId(chargeSchedule.getChargeId());
			chargeTransaction.setChargedDate(new Date());
			chargeTransaction.setCrtupdDt(new Date());
			chargeTransaction.setSourceAmount("Fund Transfer");
			chargeTransaction.setCrtupdUser("System");
			chargeTransactions.add(chargeTransaction);
			chargeSchedule.setOutstandingAmount(chargeSchedule.getOutstandingAmount()
					- chargedAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			chargeSchedule.setGstOutstandingAmount(chargeSchedule.getGstOutstandingAmount()
					- chargedGstAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			chargeSchedule.setUpdateDate(new Date());
			chargesScheduledToday.add(chargeSchedule);

		}

	}

	private void chargesCalculations(ChargeSchedule chargeSchedule, Double aggrAmount,
			ChargeTransaction chargeTransaction, Double chargedAmount, Double chargedGstAmount) {

		if (aggrAmount >= (chargeSchedule.getOutstandingAmount() + chargeSchedule.getGstOutstandingAmount())) {
			aggrAmount = aggrAmount
					- (chargeSchedule.getOutstandingAmount() + chargeSchedule.getGstOutstandingAmount());
			chargedAmount = chargeSchedule.getOutstandingAmount();
			chargedGstAmount = chargeSchedule.getGstOutstandingAmount();
			chargeTransaction.setChargedAmount(chargeSchedule.getOutstandingAmount());
			chargeTransaction.setChargedGstAmount(chargeSchedule.getGstOutstandingAmount());

		} else if (aggrAmount > 0) {
			chargedAmount = (chargeSchedule.getOutstandingAmount() * aggrAmount)
					/ (chargeSchedule.getOutstandingAmount() + chargeSchedule.getGstOutstandingAmount());
			chargedGstAmount = aggrAmount - chargedAmount;
			chargeTransaction.setChargedAmount(chargedAmount);
			chargeTransaction.setChargedGstAmount(chargedGstAmount);
			aggrAmount = 0.0d;

		}

	}

	@Override
	/**
	 * This method is used to calculate charged schedule on daily basis from
	 * Merchant on the basis of Fund Transfer amount
	 */
	public void chargesToBeDeductedOnDailyBasis(Date startDate, Date endDate) {

		List<ChargeSchedule> chargesToBeRecoveredToday = chargeScheduleDao.getChargesToBeRecoveredToday();
		List<String> utids = new ArrayList<>();
		HashMap<String, Double> utidMerchantAmount = new HashMap<>();
		Double brandAmount = 0.0;
		for (ChargeSchedule chargeSchedule : chargesToBeRecoveredToday) {
			utids.add(chargeSchedule.getUtid());

		}

		List<Object> transactionalAmount = fundTransferAggregateDao.getFundTransferDetails(startDate, endDate, utids);
		ListIterator<Object> transactionalAmountIterator = transactionalAmount.listIterator();

		while (transactionalAmountIterator.hasNext()) {
			Object[] transactionAmount = (Object[]) transactionalAmountIterator.next();
			utidMerchantAmount.put(transactionAmount[0].toString(), Double.valueOf(transactionAmount[2].toString()));
			brandAmount += Double.valueOf(transactionAmount[1].toString());
		}

		List<ChargeTransaction> chargeTransactions = new ArrayList<>();
		for (ChargeSchedule chargeSchedule : chargesToBeRecoveredToday) {
			Double chargedAmount = 0.0;
			if (chargeSchedule.getBearer() == 'M' && utidMerchantAmount.containsKey(chargeSchedule.getUtid())) {
				ChargeTransaction chargeTransaction = new ChargeTransaction();
				if (utidMerchantAmount.get(chargeSchedule.getUtid()) >= chargeSchedule.getOutstandingAmount()) {
					utidMerchantAmount.put(chargeSchedule.getUtid(),
							(utidMerchantAmount.get(chargeSchedule.getUtid()) - chargeSchedule.getOutstandingAmount()));
					chargedAmount = 0.0;
					chargeTransaction.setChargedAmount(chargeSchedule.getOutstandingAmount());

				} else {
					chargedAmount = chargeSchedule.getOutstandingAmount()
							- utidMerchantAmount.get(chargeSchedule.getUtid());
					chargeTransaction.setChargedAmount(utidMerchantAmount.get(chargeSchedule.getUtid()));
				}

				chargeTransaction.setChargeId(chargeSchedule.getChargeId());
				chargeTransaction.setCrtupdDt(new Date());
				chargeTransaction.setCrtupdUser("System");
				chargeTransactions.add(chargeTransaction);
				chargeSchedule.setOutstandingAmount(chargedAmount);
				chargeSchedule.setUpdateDate(new Date());

			}

		}
		chargeScheduleDao.saveAll(chargesToBeRecoveredToday);
		chargeTransactionDao.saveAll(chargeTransactions);
	}

}