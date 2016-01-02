/**
 * 
 */
package com.bupt.qrj.unifyum.dal.test.dao;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bupt.qrj.unifyum.dal.dao.UserTestDataDAO;
import com.bupt.qrj.unifyum.dal.dataobject.UserTestDataDO;

/**
 * @author renjun.qrj  2016年1月2日:下午9:34:59
 * com.bupt.qrj.unifyum.dal.test.dao.UserTestDataDAOTestCase
 * unifyum-dal
 * 用途: 
 *
 */
public class UserTestDataDAOTestCase {
    public ApplicationContext getContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
                "META-INF/spring/unifyum-dal-dao.xml", "META-INF/spring/unifyum-dal-db.xml" });
        assertTrue(context.containsBean("userTestDataDAO"));
        return context;
    }

    @Test
    public void testInsert() {
        ApplicationContext context = getContext();
        UserTestDataDAO dao = (UserTestDataDAO) context.getBean("userTestDataDAO");
        UserTestDataDO testD = new UserTestDataDO();
        testD.setUserName("qurenjun123");
        testD.setTestData("sdfdsfsdf");
        dao.insert(testD);
        assertTrue(true);
    }

    @Test
    public void testUpdate() {
        ApplicationContext context = getContext();
        UserTestDataDAO dao = (UserTestDataDAO) context.getBean("userTestDataDAO");
        UserTestDataDO testD = new UserTestDataDO();
        testD.setUserName("qurenjun123");
        testD.setTestData("sdfdsfsdf12345");
        dao.update(testD);
        assertTrue(true);
    }

    @Test
    public void testZGet() {
        ApplicationContext context = getContext();
        UserTestDataDAO dao = (UserTestDataDAO) context.getBean("userTestDataDAO");
        String data = dao.get("qurenjun123");
        assertNotNull(data);
        assertEquals(data, "sdfdsfsdf12345");
    }

}
