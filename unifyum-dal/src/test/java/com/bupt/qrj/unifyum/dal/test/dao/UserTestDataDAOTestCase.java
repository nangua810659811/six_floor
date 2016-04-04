/**
 * 
 */
package com.bupt.qrj.unifyum.dal.test.dao;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//@Ignore
public class UserTestDataDAOTestCase {
    public static ApplicationContext getContext() {
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
        testD.setTestData("曲仁军".getBytes());
        dao.insert(testD);
        assertTrue(true);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        ApplicationContext context = getContext();
        UserTestDataDAO dao = (UserTestDataDAO) context.getBean("userTestDataDAO");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", "zijin");
        params.put("orderKey", "gmt_created");
        params.put("limit", 10);
        List<UserTestDataDO> datal = dao.list(params);
        assertNotNull(datal);
    }

    @Test
    public void testUpdate() {
        ApplicationContext context = getContext();
        UserTestDataDAO dao = (UserTestDataDAO) context.getBean("userTestDataDAO");
        UserTestDataDO testD = new UserTestDataDO();
        testD.setUserName("qurenjun123");
        testD.setTestData("曲仁军".getBytes());
        dao.insert(testD);
        assertTrue(true);
    }

    @Test
    public void testZGet() {
        ApplicationContext context = getContext();
        UserTestDataDAO dao = (UserTestDataDAO) context.getBean("userTestDataDAO");
        UserTestDataDO data = dao.get("qurenjun123");
        assertNotNull(data);
        // assertEquals(data.toString(), "sdfdsfsdf12345");
    }

    @Test
    public void testList() {
        ApplicationContext context = getContext();
        UserTestDataDAO dao = (UserTestDataDAO) context.getBean("userTestDataDAO");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", "zijin");
        params.put("orderKey", "gmt_created");
        params.put("limit", 10);
        List<UserTestDataDO> datal = dao.list(params);
        assertNotNull(datal);
    }

}
