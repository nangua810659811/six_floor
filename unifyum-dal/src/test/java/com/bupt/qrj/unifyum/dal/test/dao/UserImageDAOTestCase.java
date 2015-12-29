/**
 * 
 */
package com.bupt.qrj.unifyum.dal.test.dao;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bupt.qrj.unifyum.dal.dao.UserImageDAO;
import com.bupt.qrj.unifyum.dal.dataobject.UserImageDO;

/**
 * @author renjun.qrj  2015年12月29日:上午7:44:09
 * com.bupt.qrj.unifyum.dal.test.dao.UserImageDAOTestCase
 * unifyum-dal
 * 用途: 
 *
 */
public class UserImageDAOTestCase {

    public ApplicationContext getContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
                "META-INF/spring/unifyum-dal-dao.xml", "META-INF/spring/unifyum-dal-db.xml" });
        assertTrue(context.containsBean("userImageDAO"));
        return context;
    }

    @Test
    public void testAssert() {
        ApplicationContext context = getContext();
    }

    @Ignore
    @Test
    public void testInert() {
        ApplicationContext context = getContext();
        UserImageDAO userImageDAO = (UserImageDAO) context.getBean("userImageDAO");
        UserImageDO image = new UserImageDO();
        image.setUserName("qurenjun");
        image.setImage("sfsdfsdf");
        userImageDAO.addImage(image);
        image.setUserName("qurenjun");
        image.setImage("sfsdfsdf123");
        userImageDAO.addImage(image);

    }

    @Ignore
    @Test
    public void testList() {
        ApplicationContext context = getContext();
        UserImageDAO userImageDAO = (UserImageDAO) context.getBean("userImageDAO");
        List<UserImageDO> doList = userImageDAO.queryImageByUser("qurenjun");
        assertNotNull(doList);
        assertEquals(doList.size(), 2);

    }

    @Test
    public void testGet() {
        ApplicationContext context = getContext();
        UserImageDAO userImageDAO = (UserImageDAO) context.getBean("userImageDAO");
        List<UserImageDO> doList = userImageDAO.queryImageByUser("qurenjun");
        for (UserImageDO ido : doList) {
            UserImageDO image = userImageDAO.getImage(ido.getId());
            assertNotNull(image);
        }
    }
}
