/**
 * 
 */
package com.bupt.qrj.unifyum.dal.test.dao;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bupt.qrj.unifyum.dal.dao.UserMetaDAO;
import com.bupt.qrj.unifyum.dal.dataobject.UserMetaDO;

/**
 * @author renjun.qrj 2015年10月31日:下午7:13:11 com.bupt.qrj.dao.UserMetaDAOTestCase
 *         unifyum-dal 用途:
 *
 */
public class UserMetaDAOTestCase extends TestCase {

	public ApplicationContext getContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/spring/unifyum-dal-dao.xml",
						"META-INF/spring/unifyum-dal-db.xml" });
		assertTrue(context.containsBean("userMetaDAO"));
		return context;
	}

	public void testInsert() {
		UserMetaDO um = new UserMetaDO();
		um.setUserName("qrj-test");
		um.setPassword("123456");
		UserMetaDAO dao = (UserMetaDAO) getContext().getBean("userMetaDAO");
		assertNotNull(dao);
		dao.add(um);
		// 保证没有异常
		assertTrue(true);
		UserMetaDO um2 = dao.get(um);
		assertNotNull(um2);
		assertEquals(um2.getUserName(), "qrj-test");
		um.setPassword("qqqqqq1");
		dao.update(um);
		um2 = dao.get(um);
		assertEquals(um2.getPassword(), "qqqqqq1");
		dao.delete(um);
		um2 = dao.get(um);
		assertNull(um2);
	}
}
