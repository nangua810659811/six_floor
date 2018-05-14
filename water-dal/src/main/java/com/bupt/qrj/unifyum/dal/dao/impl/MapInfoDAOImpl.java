/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import com.bupt.qrj.unifyum.dal.dao.MapInfoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

/**
 * @author renjun.qrj 2015年10月31日:下午6:00:56
 *         com.bupt.qrj.dao.impl.UserMetaDAOImpl unifyum-dal 用途:
 *
 */
@SuppressWarnings("deprecation")
public class MapInfoDAOImpl extends SqlMapClientDaoSupport implements
        MapInfoDAO {

	/** 日志 **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MapInfoDAOImpl.class);



	public List<MapHumanInfoDO> list(String worker_team) {

		LOGGER.debug("do the user list start");

		List<MapHumanInfoDO> rets = this.getSqlMapClientTemplate().queryForList(
				"Same-Team-Info",worker_team);
		LOGGER.debug("do the TestData user list over");
		if (rets == null || rets.size() == 0) {
			LOGGER.debug("do the user list return null");
			return null;
		}
//		System.out.println(rets);
		return rets;
	}








    public String get_team(String phone) throws DataAccessException {

        String rets = (String) this.getSqlMapClientTemplate()
                .queryForObject("Get-Team-Info", phone);

        return rets;
    }

    public List<MapEventInfoDO> Event_list() {

        LOGGER.debug("do the user list start");

        List<MapEventInfoDO> rets = this.getSqlMapClientTemplate().queryForList(
                "Map-Event-Info");
        LOGGER.debug("do the TestData user list over");
        if (rets == null || rets.size() == 0) {
            LOGGER.debug("do the user list return null");
            return null;
        }

        return rets;
    }

}
