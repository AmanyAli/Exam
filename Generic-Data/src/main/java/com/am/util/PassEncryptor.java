package com.am.util;

import org.apache.commons.codec.binary.Base64;
/**
 * @author Amany Ali
 * 
 *Password encoder class
 *
 */
public class PassEncryptor {

	public static String EncodePassword(String password) {

		byte[] encoded = Base64.encodeBase64(password.getBytes());
		return new String(encoded);

	}

	public static String DecodePassword(String password) {

		byte[] decoded = Base64.decodeBase64(password.getBytes());

		return new String(decoded);
	}

}
