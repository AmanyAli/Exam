package com.am.util;
/**
 * @author Amany Ali
 * 
 *String Utility class to provide all method related to String modification
 *
 */
public class StringUtil {

	public static String convertByteToHex(byte val) {
		StringBuilder sb = new StringBuilder();
		int v = val & 0xff;

		if (v < 16) {
			sb.append('0');
		}

		sb.append(Integer.toHexString(v));

		return sb.toString().toUpperCase();

	}

	public static String dec2Hex(long num) {
		String hex = "";

		while (num != 0) {
			if (num % 16 < 10)
				hex = Long.toString(num % 16) + hex;
			else
				hex = (char) ((num % 16) + 55) + hex;
			num = num / 16;
		}

		return hex;
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static String removeSpecialCharacters(String data) {

		return data.replaceAll("[^a-zA-Z0-9]+", "");
	}

	public static String capitalizeFirstLetter(String word) {
		StringBuilder sb = new StringBuilder(word); // one StringBuilder object
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString(); // one String object

	}

	public static String LowerCaseFirstLetter(String word) {
		StringBuilder sb = new StringBuilder(word); // one StringBuilder object
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString(); // one String object

	}

	public static String append(boolean revers, Object... data) {

		StringBuilder sb = new StringBuilder();

		if (data != null) {
			if (revers) {
				for (int i = data.length - 1; i >= 0; i--) {

					if (data[i] != null) {
						sb.append(data[i]);
					}
				}
			} else {

				for (int i = 0; i < data.length; i++) {
					if (data[i] != null) {
						sb.append(data[i]);
					}
				}
			}
		}
		return sb.toString();

	}

}
