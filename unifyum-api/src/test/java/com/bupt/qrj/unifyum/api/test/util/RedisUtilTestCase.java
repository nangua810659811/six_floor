/**
 * 
 */
package com.bupt.qrj.unifyum.api.test.util;

import java.util.Date;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.bupt.qrj.unifyum.api.model.UserMeta;
import com.bupt.qrj.unifyum.api.util.RedisUtil;

/**
 * @author renjun.qrj 2015年10月31日:下午9:38:51
 *         com.bupt.qrj.unifyum.api.test.util.RedisUtilTestCase unifyum-api 用途:
 *
 */
public class RedisUtilTestCase extends TestCase {

	public ApplicationContext getContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/spring/unifyum-dal-dao.xml",
						"META-INF/spring/unifyum-dal-db.xml" });
		assertTrue(context.containsBean("userMetaDAO"));
		return context;
	}

	public void testRedis() {
		UserMeta meta = new UserMeta();
		Date loginTime = new Date();
		meta.setUserName("sdfsdf");
		meta.setPassword("12345");
		meta.setLoginTime(loginTime);
		RedisUtil.saveObject("1", meta, 3600);
		assertTrue(true);
		UserMeta meta2 = build(RedisUtil.getObject("1"));
		assertEquals(meta2.getPassword(), "12345");
		assertEquals(meta2.getLoginTime().getTime(), loginTime.getTime());
		RedisUtil.removeObject("1");
		assertNull(RedisUtil.getObject("1"));
	}

	/**
	 * build 2015年11月3日 2015年11月3日.上午8:37:55 return: UserMeta
	 */
	private UserMeta build(JSONObject object) {
		// TODO Auto-generated method stub
		UserMeta meta = new UserMeta();
		meta.setPassword(object.getString("password"));
		meta.setUserName(object.getString("userName"));
		meta.setAuthToken(object.getString("authToken"));
		meta.setLoginTime(object.getDate("loginTime"));
		return meta;
	}
}
