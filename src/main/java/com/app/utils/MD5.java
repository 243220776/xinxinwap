package com.app.utils;

import java.security.MessageDigest;

public class MD5 {
	
	public static String convert(String plainText, int off, int len) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte b[] = plainText.getBytes();
		md.update(b, off, len);
		// md.update(plainText.getBytes());
		md.digest();

		int i;

		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0) {
				i += 256;
			}
			if (i < 16) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
		// System.out.println("result: " + buf.toString());// 32位的加密
		// System.out.println("result: " + buf.toString().substring(8, 24));//
		// 16位的加密

	}

	public static String convert(String plainText) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(plainText.getBytes());
		byte b[] = md.digest();

		int i;

		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0) {
				i += 256;
			}
			if (i < 16) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
		// System.out.println("result: " + buf.toString());// 32位的加密
		// System.out.println("result: " + buf.toString().substring(8, 24));//
		// 16位的加密

	}
}
