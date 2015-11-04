/**
 * 
 */
package com.bupt.qrj.unifyum.util.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renjun.qrj 2015年11月4日:下午5:22:55
 *         com.bupt.qrj.unifyum.util.http.HttpClientUtil unifyum-util 用途:
 *
 */
public class HttpClientUtil {

	/** 日志 **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HttpOutUtil.class);

	/** 基础工具 */
	// private final HttpClient httpClient = ;

	/**
	 * get 请求 只有在返回状态码为200的时候才会返回结果否则返回null
	 *
	 * @param reqURL
	 *            请求url
	 * @return 请求返回的字符串
	 */
	public static String doPostRequest(String reqURL, Map<String, Object> params) {
		String result = null;
		HttpClient client = new HttpClient();
		HttpMethod postMethod = new PostMethod(reqURL);
		postMethod.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=gbk");
		if (params != null && params.size() > 0) {
			Iterator<Map.Entry<String, Object>> iter = params.entrySet()
					.iterator();
			List<NameValuePair> paramPair = new ArrayList<NameValuePair>();
			while (iter.hasNext()) {
				Map.Entry<String, Object> entry = iter.next();
				NameValuePair pair = new NameValuePair();
				pair.setName(entry.getKey());
				pair.setValue(String.valueOf(entry.getValue()));
				paramPair.add(pair);
			}
			NameValuePair[] pairArray = new NameValuePair[paramPair.size()];
			pairArray = paramPair.toArray(pairArray);
			((PostMethod) postMethod).setRequestBody(pairArray);
		}
		try {
			client.executeMethod(postMethod);
			result = postMethod.getResponseBodyAsString();
		} catch (HttpException e) {
			LOGGER.error("httpclintutil post request http exception: ", e);
		} catch (IOException e) {
			LOGGER.error("httpclintutil post request io exception: ", e);

		}
		return result;

	}
}
