/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import com.bupt.qrj.unifyum.dal.dao.locationDAO;
import com.bupt.qrj.unifyum.dal.dataobject.locationDO;

import com.bupt.qrj.unifyum.dal.dataobject.locationinsertDO;
import com.bupt.qrj.unifyum.dal.dataobject.locationnameDO;
import com.bupt.qrj.unifyum.dal.dataobject.workerLoginDO;
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
public class locationDAOImpl extends SqlMapClientDaoSupport implements
		locationDAO {

	/** 日志 **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(locationDAOImpl.class);



	public void update(locationDO locationDO) throws DataAccessException {

        this.getSqlMapClientTemplate().update("location-UPDATE",
				locationDO);

    }

	public void insert(locationinsertDO locationinsertDO) throws DataAccessException {


		this.getSqlMapClientTemplate().insert("location-insert",
				locationinsertDO);
	}
	public locationnameDO get(locationnameDO locationnameDO) throws DataAccessException {

		if (locationnameDO.getPhone() == null)
			return null;
		//System.out.println("1");
		locationnameDO resultDO = (locationnameDO) this.getSqlMapClientTemplate()
				.queryForObject("location-select-name", locationnameDO);
		//System.out.println("2");
		return resultDO;
	}
}
