/**
 * 
 */
package com.bupt.qrj.unifyum.api.test.util;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		meta.setUserName("sdfsdf");
		meta.setPassword("12345");
		RedisUtil.saveObject("1", meta, 10);
		assertTrue(true);
		UserMeta meta2 = (UserMeta) RedisUtil.getObject("1");
		assertEquals(meta2.getPassword(), "12345");
	}

}
