/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import com.bupt.qrj.unifyum.dal.dao.conditionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * @author renjun.qrj 2015年10月31日:下午6:00:56
 *         com.bupt.qrj.dao.impl.UserMetaDAOImpl unifyum-dal 用途:
 *
 */
@SuppressWarnings("deprecation")
public class conditionDAOImpl extends SqlMapClientDaoSupport implements
		conditionDAO {

	/** 日志 **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(conditionDAOImpl.class);



	public void update(misconditionDO misconditionDO) throws DataAccessException {

        this.getSqlMapClientTemplate().update("condition-UPDATE",
				misconditionDO);

    }


}
