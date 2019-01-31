package com.am.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

	public static final String COMTRADE_DATE_FORMAT_SECONDS = "yyyy-MM-dd_HH-mm-ss";
	public static final String CFG_DATE_TIME_FORMAT = "yyyy-MM-dd_HH-mm-ss-SSS";
	public static final String EXPORT_PROJECT_DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss-SSS";

	public static final String SYS_FORMAT = "dd/MM/yyyy,HH:mm:ss.SSS";

	public static final String MONGO_DB_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	// private SimpleDateFormat Format;
	private static final String[] formats = { "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ssZ",
			"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
			"yyyy-MM-dd HH:mm:ss", "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy'T'HH:mm:ss.SSS'Z'", "MM/dd/yyyy'T'HH:mm:ss.SSSZ",
			"MM/dd/yyyy'T'HH:mm:ss.SSS", "MM/dd/yyyy'T'HH:mm:ssZ", "MM/dd/yyyy'T'HH:mm:ss", "yyyy:MM:dd HH:mm:ss",
			"yyyyMMdd", };

	public static String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(SYS_FORMAT);

		return dateFormat.format(date);
	}

	public static Date getComtradeDate(String folderName) {
		// String dateFormat = "yyyy-MM-dd_HH-mm-ss-SSS";
		StringBuilder sb = new StringBuilder();

		int timeIndex = folderName.indexOf("_");

		sb.append(folderName.substring(0, timeIndex));

		if (timeIndex != -1) {
			sb.append(" ");
			String time = folderName.substring(timeIndex + 1);

			int index = time.indexOf("-");
			String hour = time.substring(0, index);

			sb.append(hour);// hour
			index = index + 1;
			sb.append(":");

			sb.append(time.substring(index, index + 2));// min
			sb.append(":");

			index = index + 3;
			sb.append(time.substring(index, index + 2));// sec
			sb.append(".");
			index = index + 3;
			sb.append(time.substring(index));// milsec
		}

		return getDate(sb.toString(), CFG_DATE_TIME_FORMAT);
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	public static Date addSeconds(Date date, Integer seconds) {
		Calendar time = Calendar.getInstance();
		time.setTime(date);
		int currentSeconds = time.get(Calendar.SECOND);

		time.set(Calendar.SECOND, seconds + currentSeconds);

		return time.getTime();
	}

	public static Date addHours(Date date, Integer hours) {
		Calendar time = Calendar.getInstance();
		time.setTime(date);
		int currentHours = time.get(Calendar.HOUR);

		time.set(Calendar.HOUR, hours + currentHours);

		return time.getTime();
	}

	public static Date addMilliSeconds(Date date, Integer milliSeconds) {
		Calendar time = Calendar.getInstance();
		time.setTime(date);
		int currentMilliSeconds = time.get(Calendar.MILLISECOND);

		time.set(Calendar.MILLISECOND, milliSeconds + currentMilliSeconds);

		return time.getTime();
	}

	public static Date getDate(String dateString, String format) {

		Date date1 = null;
		try {
			date1 = new SimpleDateFormat(format).parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date1;

	}

	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(cal.YEAR);

	}

	public static String getDateFormat(String dateString) {

		SimpleDateFormat sdf = new SimpleDateFormat(SYS_FORMAT);
		Date d = null;
		try {
			d = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sdf.applyPattern(SYS_FORMAT);
		String newDateString = sdf.format(d);
		return newDateString;

	}

	public static String formatDateTime(Date date, String format) {
		// format="yyyy-MM-dd HH:mm:ss.SSS";
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		if (date != null) {
			return dateFormat.format(date);
		}
		return "";
	}

	public static String formatDateTime(Date date) {
		String format = SYS_FORMAT;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		if (date != null) {
			return dateFormat.format(date);
		}
		return "";
	}

	public static Date getDateFromString(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat(SYS_FORMAT);
		Date date = null;
		try {

			date = formatter.parse(dateString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}

	public static Date getDateFromString(String dateString, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		try {

			date = formatter.parse(dateString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}

	public static String convertLongToDate(long longDate) {
		Date date = new Date(longDate);
		SimpleDateFormat df2 = new SimpleDateFormat(CFG_DATE_TIME_FORMAT);
		String dateText = df2.format(date);
		return dateText;
	}

	public static LocalDate getLocalDate(Date date) {
		LocalDate localDate = LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
		return localDate;
	}

	public static Date getDate(LocalDate localDate) {

		String dateString = localDate.toString();
		return getDateFromString(dateString, "yyyy-MM-dd");

	}
}
