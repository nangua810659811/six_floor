/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public UserTestDataDO get(String userName) {
        LOGGER.debug("do the userTestData get start");
        List<UserTestDataDO> rets = this.getSqlMapClientTemplate().queryForList(
            "UNIFYUM-USER-TEST-DATA-GET", userName);
        if (rets != null && rets.size() > 0) {
            LOGGER.debug("do the userTestData get over,data is over ,return data");
            return rets.get(0);
        } else {
            LOGGER.debug("do the userTestData get over,data is over ,return null");
            return null;
        }

    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.UserTestDataDAO#list(java.util.Map)
     */
    public List<UserTestDataDO> list(Map params) {
        LOGGER.debug("do the userTestData list start");
        List<UserTestDataDO> rets = this.getSqlMapClientTemplate().queryForList(
            "UNIFYUM-USER-TEST-DATA-LIST", params);
        LOGGER.debug("do the userTestData list start");
        return rets;
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

}
