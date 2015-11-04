/**
 * 
 */
package com.bupt.qrj.unifyum.util.test.redis;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.bupt.qrj.unifyum.util.redis.RedisUtil;

/**
 * @author renjun.qrj 2015年10月31日:下午9:38:51
 *         com.bupt.qrj.unifyum.api.test.util.RedisUtilTestCase unifyum-api 用途:
 *
 */

public class RedisUtilTestCase {

	@Test
	public void testRedis() {

		JSONObject test = new JSONObject();
		test.put("haha", "haha");
		RedisUtil.saveObject("1", test, 3600);
		assertTrue(true);
		JSONObject test2 = (JSONObject) RedisUtil.getObject("1");
		assertEquals(test2.get("haha"), "haha");
		RedisUtil.removeObject("1");
		assertNull(RedisUtil.getObject("1"));
	}
}
