package com.innoviti.retail.fundTransfer.starter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import com.innoviti.retail.fundTransfer.repository.mis.FundTransferConfigurableDao;
import com.innoviti.retail.fundTransfer.service.FundTransferService;
import com.innoviti.retail.fundTransfer.utils.DateTimeUtil;
import com.innoviti.retail.fundTransfer.utils.ReportConstants;

@SpringBootApplication(scanBasePackages = "com.innoviti")
@ComponentScan("com.innoviti.retail.*")
public class FundTransferDetailsApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(FundTransferDetailsApplication.class);

	@Autowired
	FundTransferService fundTransferService;

	@Value("${innovitiPOSMoneyStartTime}")
	private String innovitiPOSMoneyStartTime;

	@Value("${innovitiPOSMoneyEndTime}")
	private String innovitiPOSMoneyEndTime;

	@Autowired
	FundTransferConfigurableDao fundTransferConfigurableDao;

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "application");
		// / System.setProperty("spring.config.name", "application");
		new SpringApplicationBuilder(FundTransferDetailsApplication.class).web(WebApplicationType.NONE)
				// // .REACTIVE,
				// // .SERVLET
				.run(args);

	}

	@Override
	public void run(String... args) throws Exception {
		Date startDate = null;
		Date endDate = null;
		String emailCheck = "yes";
		if (args.length == 0 || args.length == 1 ) {
			startDate = DateTimeUtil
					.innovitiPOSFromDateTime(fundTransferConfigurableDao.getDate(innovitiPOSMoneyStartTime));
			endDate = DateTimeUtil.innovitiPOSToDateTime(fundTransferConfigurableDao.getDate(innovitiPOSMoneyEndTime));
			LOG.info("Start Date " + startDate);
			LOG.info("End Date " + endDate);
			if (args.length == 1) {
				emailCheck = args[0];
			}
		} else if ( args.length == 2 || args.length == 3) {
			LOG.error("Please Provide Start Date And End Date in correct format");
			LOG.error(
					"Use this format  ::  java -jar jar_name.jar  start_date(yyyy-MM-dd HH:mm:ss) end_date(yyyy-MM-dd HH:mm:ss)");
			return;

		} else if (args.length == 4 || args.length == 5) {

			LOG.info("  Getting Input ");
			if (!isValidFormat(ReportConstants.INPUT_DATE_FORMAT, args[0] + " " + args[1])) {
				LOG.error("Please Provide valid Start Date (yyyy-MM-dd HH:mm:ss)");
				return;
			}
			if (!isValidFormat(ReportConstants.INPUT_DATE_FORMAT, args[2] + " " + args[3])) {
				LOG.error("Please Provide valid End Date (yyyy-MM-dd HH:mm:ss) ");
				return;
			}
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(args[0] + " " + args[1]);
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(args[2] + " " + args[3]);
			LOG.info("Start Date :" + startDate);
			LOG.info("End Date :" + endDate);
			if (args.length == 5) {
				emailCheck = args[4];
			}
		}
		fundTransferService.generateFundTransferFile(startDate, endDate,emailCheck);
	}

	private static boolean isValidFormat(String format, String value) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(value);
			if (!value.equals(sdf.format(date))) {
				date = null;
			}
		} catch (ParseException ex) {
			LOG.error("ERROR While Parsing Command Line Argument " + ex.getMessage(), ex);
		}
		return date != null;
	}
}