/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import com.bupt.qrj.unifyum.dal.dao.MissionReturnDAO;
import com.bupt.qrj.unifyum.dal.dao.setMissionDAO;
import com.bupt.qrj.unifyum.dal.dataobject.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.HashMap;
import java.util.List;

/**
 * @author renjun.qrj 2015年10月31日:下午6:00:56
 *         com.bupt.qrj.dao.impl.UserMetaDAOImpl unifyum-dal 用途:
 *
 */
@SuppressWarnings("deprecation")
public class setMissionDAOImpl extends SqlMapClientDaoSupport implements
        setMissionDAO {

	/** 日志 **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(setMissionDAOImpl.class);



	public List<setMissionDO> List(String type) {

		LOGGER.debug("do the user list start");

		List<setMissionDO> rets = this.getSqlMapClientTemplate().queryForList(
				"Set_Mission-List",type);
		LOGGER.debug("do the TestData user list over");
		if (rets == null || rets.size() == 0) {
			LOGGER.debug("do the user list return null");
			return null;
		}

		return rets;
	}




    public List<materialDO> list1(String type) {

        LOGGER.debug("do the user list start");


        List<materialDO> rets = this.getSqlMapClientTemplate().queryForList(
                "Material-List",type);
        LOGGER.debug("do the TestData user list over");
        if (rets == null || rets.size() == 0) {
            LOGGER.debug("do the user list return null");
            return null;
        }

        return rets;
    }



    public List<EventInfoDO> Event_list() {

        LOGGER.debug("do the user list start");

        List<EventInfoDO> rets = this.getSqlMapClientTemplate().queryForList(
                "Event-Info-set");
        LOGGER.debug("do the TestData user list over");
        if (rets == null || rets.size() == 0) {
            LOGGER.debug("do the user list return null");
            return null;
        }

        return rets;
    }
    public setMissionDO get(String type, String array) throws DataAccessException {

        HashMap<String,Object> abb = new HashMap<String, Object>();
        abb.put("type",type);
        abb.put("array",array);
        setMissionDO resultDO = (setMissionDO) this.getSqlMapClientTemplate()
                .queryForObject("Set_Mission-Info",abb);

        return resultDO;
    }

    public List<setMissioninfoDO> Mission_list() {

        LOGGER.debug("do the user list start");

        List<setMissioninfoDO> rets = this.getSqlMapClientTemplate().queryForList(
                "Mission-Info-set-select");
        LOGGER.debug("do the TestData user list over");
        if (rets == null || rets.size() == 0) {
            LOGGER.debug("do the user list return null");
            return null;
        }

        return rets;
    }
}
