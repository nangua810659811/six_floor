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

	private final String REGISTER_URL = "http://localhost:8080/unifyum/usermanagement.req?action=register";

	private final String UNREGISTER_URL = "http://localhost:8080/unifyum/usermanagement.req?action=unregister";

	@Test
	public void testPost() {

		JSONObject rs_params = new JSONObject();
		rs_params.put("userName", "qurenjun-test");
		rs_params.put("password", "123456");
		// 先做注册
		String httpRet = HttpClientUtil.doPostRequest(REGISTER_URL, rs_params);
		JSONObject rs_ret = JSONObject.parseObject(httpRet);
		assertTrue(rs_ret.getBoolean("success"));
		// 再做注销
		httpRet = HttpClientUtil.doPostRequest(UNREGISTER_URL, rs_params);
		rs_ret = JSONObject.parseObject(httpRet);
		assertTrue(rs_ret.getBoolean("success"));
	}
}
