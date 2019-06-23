package com.innoviti.retail.fundTransfer.utils;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

	public static Date innovitiPOSToDateTime(String time) {
		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String arr[] = time.split(" ");
		String arr1[] = arr[1].split(":");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, 00);

		cal.set(Calendar.SECOND, 00);

		cal.set(Calendar.MILLISECOND, 000);

		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arr1[0]));
		System.out.println("date==============Pragati POS Report From================" + cal.getTime());
		return cal.getTime();
	}

	public static Date innovitiPOSFromDateTime(String time) {
		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String arr[] = time.split(" ");
		String arr1[] = arr[1].split(":");
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MINUTE, Integer.parseInt(arr1[1]));
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arr1[0]));
		System.out.println("date==============Pragati POS Report ================" + cal.getTime());
		return cal.getTime();
	}

	/*
	 * public static String pragatiToDateTime(String time, String date) { DateFormat
	 * dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Date endDate =
	 * null; Calendar cal = Calendar.getInstance(); try { endDate =
	 * dateFormat.parse(date);
	 * 
	 * String arr[] = time.split(":");
	 * 
	 * cal.setTime(endDate); cal.set(Calendar.MINUTE, 00);
	 * 
	 * cal.set(Calendar.SECOND, 00);
	 * 
	 * cal.set(Calendar.MILLISECOND, 000);
	 * 
	 * cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arr[0])); } catch
	 * (ParseException e) {
	 * 
	 * e.printStackTrace(); } return dateFormat.format(cal.getTime()); }
	 * 
	 * public static String pragatiFromDateTime(String time, String date) {
	 * DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Date
	 * startDate = null; Calendar cal = Calendar.getInstance(); try { startDate =
	 * dateFormat.parse(date); cal.setTime(startDate); String arr[] =
	 * time.split(":"); cal.add(Calendar.DATE, -1); cal.set(Calendar.SECOND, 00);
	 * cal.set(Calendar.MINUTE, Integer.parseInt(arr[1]));
	 * cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arr[0])); } catch
	 * (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } return dateFormat.format(cal.getTime()); }
	 */

}
