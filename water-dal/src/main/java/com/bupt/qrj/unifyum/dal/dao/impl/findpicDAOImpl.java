/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import com.bupt.qrj.unifyum.dal.dao.findpicDAO;
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
public class findpicDAOImpl extends SqlMapClientDaoSupport implements
		findpicDAO {

	/** 日志 **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(findpicDAOImpl.class);

	public picinfoDO get(picinfoDO picinfo) throws DataAccessException {


		//System.out.println("1");
		picinfoDO resultDO = (picinfoDO) this.getSqlMapClientTemplate()
				.queryForObject("find-pic", picinfo);
		//System.out.println("2");
		return resultDO;
	}

	public picinfoDO getnewest(picinfoDO picinfo) throws DataAccessException {


		//System.out.println("1");
		picinfoDO resultDO = (picinfoDO) this.getSqlMapClientTemplate()
				.queryForObject("find-pic-newest");
		//System.out.println("2");
		return resultDO;
	}

}
