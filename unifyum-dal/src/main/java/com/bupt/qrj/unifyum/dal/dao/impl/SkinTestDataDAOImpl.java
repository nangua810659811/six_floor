/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bupt.qrj.unifyum.dal.dao.SkinTestDataDAO;
import com.bupt.qrj.unifyum.dal.dataobject.SkinTestDataDO;

/**
 * @author renjun.qrj  2016年4月4日:下午11:07:26
 * com.bupt.qrj.unifyum.dal.dao.impl.SkinTestDataDAOImpl
 * unifyum-dal
 * 用途: 
 *
 */
public class SkinTestDataDAOImpl extends SqlMapClientDaoSupport implements SkinTestDataDAO {
    /** 日志 **/
    private static final Logger LOGGER = LoggerFactory.getLogger(SkinTestDataDAOImpl.class);

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.SkinTestDataDAO#insert(com.bupt.qrj.unifyum.dal.dataobject.SkinTestDataDO)
     */
    public void insert(SkinTestDataDO dataDO) {
        LOGGER.debug("do the skinTestData insert start");
        dataDO.setGmtCreated(new Date());
        dataDO.setGmtModified(new Date());
        this.getSqlMapClientTemplate().insert("UNIFYUM-SKIN-TEST-DATA-INSERT", dataDO);
        LOGGER.debug("do the skinTestData insert over");
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.SkinTestDataDAO#list(java.lang.String)
     */
    public List<SkinTestDataDO> list(String userName) {
        LOGGER.debug("do the skinTestData list start");
        List<SkinTestDataDO> rets = this.getSqlMapClientTemplate().queryForList(
            "UNIFYUM-SKIN-TEST-DATA-LIST", userName);
        LOGGER.debug("do the skinTestData list over");
        if (rets == null || rets.size() == 0) {
            LOGGER.debug("do the skinTestData list return null");
            return null;
        }
        LOGGER.debug("do the skinTestData list return data list");
        return rets;
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.SkinTestDataDAO#listUsers()
     */
    public List<String> listUsers() {
        LOGGER.debug("do the skinTestData user list start");
        List<String> rets = this.getSqlMapClientTemplate().queryForList(
            "UNIFYUM-SKIN-TEST-USER-LIST");
        LOGGER.debug("do the skinTestData user list over");
        if (rets == null || rets.size() == 0) {
            LOGGER.debug("do the skinTestData list return null");
            return null;
        }
        return rets;
    }

}
