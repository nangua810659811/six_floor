/**
 * 
 */
package com.bupt.qrj.unifyum.dal.test.dao;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bupt.qrj.unifyum.dal.dao.SkinTestDataDAO;
import com.bupt.qrj.unifyum.dal.dataobject.SkinTestDataDO;

/**
 * @author renjun.qrj  2016年4月4日:下午11:14:23
 * com.bupt.qrj.unifyum.dal.test.dao.SkinTestDataDAOTestCase
 * unifyum-dal
 * 用途: 
 *
 */
public class SkinTestDataDAOTestCase {
    public static ApplicationContext getContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
                "META-INF/spring/unifyum-dal-dao.xml", "META-INF/spring/unifyum-dal-db.xml" });
        assertTrue(context.containsBean("skinTestDataDAO"));
        return context;
    }

    public static void main(String[] args) {
        ApplicationContext context = getContext();
        SkinTestDataDAO dataDao = (SkinTestDataDAO) context.getBean("skinTestDataDAO");
        assertNotNull(dataDao);
        SkinTestDataDO dataDO = new SkinTestDataDO();
        dataDO.setUserName("qurenjun");
        dataDO.setType("type1");
        dataDO.setValue("value" + new Date());
        dataDao.insert(dataDO);
        dataDO.setType("type2");
        dataDO.setValue("value" + new Date());
        dataDao.insert(dataDO);
        dataDO.setType("type3");
        dataDO.setValue("value" + new Date());
        dataDao.insert(dataDO);
        List<SkinTestDataDO> testDataList = dataDao.list("qurenjun");
        assertNotNull(dataDao);
        List<String> users = dataDao.listUsers();
        assertNotNull(users);
    }

}
