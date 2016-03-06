/**
 * 
 */
package com.bupt.qrj.unifyum.dal.test.dao;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
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
        image
            .setImage("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");
        image.setBlood("0");
        image.setColor("1");
        image.setMoisten("2");
        image.setSatin("3");
        image.setTexture("4");
        for (int i = 0; i < 100000; i++) {
            userImageDAO.addImage(image);
            System.out.println(">>>>>>>>>>>>>>> Over " + i + "   items <<<<<<<<<<<<<<<<<<<<");
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        assertNull(null);

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

    @Ignore
    @Test
    public void testEList() {
        ApplicationContext context = getContext();
        UserImageDAO userImageDAO = (UserImageDAO) context.getBean("userImageDAO");
        StringBuffer filterBuf = new StringBuffer();
        filterBuf.append("user_name = 'qurenjun'");
        filterBuf.append(" AND ");
        filterBuf.append("blood = '0'");

        HashMap<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("filterStr", filterBuf.toString());
        List<UserImageDO> doList = userImageDAO.queryImageByProps(paraMap);
        assertNotNull(doList);
        assertEquals(doList.size(), 2);
    }

    @Ignore
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

    @Ignore
    @Test
    public void testCount() {
        ApplicationContext context = getContext();
        HashMap<String, Object> paraMap = new HashMap<String, Object>();
        StringBuffer filterBuf = new StringBuffer();
        filterBuf.append("user_name = 'qurenjun'");
        filterBuf.append(" AND ");
        filterBuf.append("blood = '2'");
        paraMap.put("filterStr", filterBuf.toString());

        UserImageDAO userImageDAO = (UserImageDAO) context.getBean("userImageDAO");
        Integer count = userImageDAO.count(paraMap);
        assertNotNull(count);
    }
}
