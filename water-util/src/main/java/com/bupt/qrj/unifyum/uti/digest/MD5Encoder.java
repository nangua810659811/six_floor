/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.bupt.qrj.unifyum.uti.digest;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bupt.qrj.unifyum.util.http.HttpOutUtil;

/**
 * @author renjun.qrj 2015年11月8日:下午7:55:08
 *         com.bupt.qrj.unifyum.uti.digest.MD5Encoder unifyum-util 用途:
 *
 */
public class MD5Encoder {

	/** 日志 **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HttpOutUtil.class);

	/**
	 * MD5编码
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (Exception e) {
			LOGGER.warn(e.getMessage(), e);
			return "";
		}

		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}

		return md5StrBuff.toString();
	}

}
