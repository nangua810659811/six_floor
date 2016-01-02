/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bupt.qrj.unifyum.dal.dao.UserTestDataDAO;
import com.bupt.qrj.unifyum.dal.dataobject.UserTestDataDO;

/**
 * @author renjun.qrj  2016年1月2日:下午9:09:53
 * com.bupt.qrj.unifyum.dal.dao.impl.UserTestDataDAOImpl
 * unifyum-dal
 * 用途: 
 *
 */
public class UserTestDataDAOImpl extends SqlMapClientDaoSupport implements UserTestDataDAO {

    /** 日志 **/
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(UserTestDataDAOImpl.class);

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.UserTestDataDAO#get(java.lang.String)
     */
    public String get(String userName) {
        LOGGER.debug("do the userTestData get start");
        String testData = (String) this.getSqlMapClientTemplate().queryForObject(
            "UNIFYUM-USER-TEST-DATA-GET", userName);
        LOGGER.debug("do the userTestData get over,data is " + testData);
        return testData;
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.UserTestDataDAO#insert(com.bupt.qrj.unifyum.dal.dataobject.UserTestDataDO)
     */
    public void insert(UserTestDataDO dataDO) {
        LOGGER.debug("do the userTestData insert start");
        dataDO.setGmtCreated(new Date());
        dataDO.setGmtModified(new Date());
        this.getSqlMapClientTemplate().insert("UNIFYUM-USER-TEST-DATA-INSERT", dataDO);
        LOGGER.debug("do the userTestData insert over");
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.UserTestDataDAO#update(com.bupt.qrj.unifyum.dal.dataobject.UserTestDataDO)
     */
    public void update(UserTestDataDO dataDO) {
        LOGGER.debug("do the userTestData update start");
        dataDO.setGmtModified(new Date());
        this.getSqlMapClientTemplate().update("UNIFYUM-USER-TEST-DATA-UPDATE", dataDO);
        LOGGER.debug("do the userTestData update over");
    }

}
