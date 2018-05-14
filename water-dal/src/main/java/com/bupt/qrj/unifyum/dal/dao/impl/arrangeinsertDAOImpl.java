/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import com.bupt.qrj.unifyum.dal.dao.arrangeinsertDAO;
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
public class arrangeinsertDAOImpl extends SqlMapClientDaoSupport implements
		arrangeinsertDAO {

	/** 日志 **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(arrangeinsertDAOImpl.class);


	public void insert(arrangeinsertDO arrangeinsertDO) throws DataAccessException {


		this.getSqlMapClientTemplate().insert("arrange-worker-insert",
				arrangeinsertDO);
    }


	public void update(arrangeinsertDO arrangeinsertDO) throws DataAccessException {



		this.getSqlMapClientTemplate().update("arrange-worker-UPDATE",
				arrangeinsertDO);

    }


}
