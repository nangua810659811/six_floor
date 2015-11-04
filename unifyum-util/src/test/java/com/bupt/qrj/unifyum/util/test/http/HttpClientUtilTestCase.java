/**
 * 
 */
package com.bupt.qrj.unifyum.util.test.http;

import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.bupt.qrj.unifyum.util.http.HttpClientUtil;

/**
 * @author renjun.qrj 2015年11月4日:下午5:39:50
 *         com.bupt.qrj.unifyum.util.test.http.HttpClientUtilTestCase
 *         unifyum-util 用途:
 *
 */
public class HttpClientUtilTestCase {

	private final String RS_URL = "http://localhost:8080/unifyum/usermanagement.req?action=unregister";

	@Test
	public void testPost() {
		assertTrue(true);
		JSONObject rs_params = new JSONObject();
		rs_params.put("userName", "qurenjun");
		String httpRet = HttpClientUtil.doPostRequest(RS_URL, rs_params);
		JSONObject rs_ret = JSONObject.parseObject(httpRet);
		assertTrue(rs_ret.getBoolean("success"));
	}
}
