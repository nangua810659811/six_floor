/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bupt.qrj.unifyum.dal.dao.UserMetaDAO;
import com.bupt.qrj.unifyum.dal.dataobject.UserMetaDO;

/**
 * @author renjun.qrj 2015年10月31日:下午6:00:56
 *         com.bupt.qrj.dao.impl.UserMetaDAOImpl unifyum-dal 用途:
 *
 */
@SuppressWarnings("deprecation")
public class UserMetaDAOImpl extends SqlMapClientDaoSupport implements
		UserMetaDAO {

	/** 日志 **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserMetaDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bupt.qrj.dao.UserMetaDAO#add(com.bupt.qrj.dataobject.UserMetaDO)
	 */
	public void add(UserMetaDO userMeta) throws DataAccessException {
		if (userMeta == null) {
			throw new IllegalArgumentException(
					"Can't insert a null data(mobilelbsMerchantSubcategoryDO) object into db.");
		}

		userMeta.setGmtCreated(new Date());
		userMeta.setGmtModified(new Date());
		this.getSqlMapClientTemplate().insert("UNIFYUM-USER-META-INSERT",
				userMeta);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bupt.qrj.dao.UserMetaDAO#update(com.bupt.qrj.dataobject.UserMetaDO)
	 */
	public void update(UserMetaDO userMeta) throws DataAccessException {
		if (userMeta == null) {
			throw new IllegalArgumentException(
					"Can't insert a null data(mobilelbsMerchantSubcategoryDO) object into db.");
		}
		userMeta.setGmtModified(new Date());
		this.getSqlMapClientTemplate().update("UNIFYUM-USER-META-UPDATE",
				userMeta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bupt.qrj.dao.UserMetaDAO#get(com.bupt.qrj.dataobject.UserMetaDO)
	 */
	public UserMetaDO get(UserMetaDO userMeta) throws DataAccessException {
		if (userMeta == null) {
			throw new IllegalArgumentException(
					"Can't insert a null data(mobilelbsMerchantSubcategoryDO) object into db.");
		}
		if (userMeta.getUserName() == null && userMeta.getAuthToken() == null)
			return null;
		return (UserMetaDO) this.getSqlMapClientTemplate().queryForObject(
				"UNIFYUM-USER-META-GET", userMeta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bupt.qrj.dao.UserMetaDAO#delete(com.bupt.qrj.dataobject.UserMetaDO)
	 */
	public void delete(UserMetaDO userMeta) throws DataAccessException {

		if (userMeta == null || userMeta.getUserName() == null)
			return;
		this.getSqlMapClientTemplate().delete("UNIFYUM-USER-META-DELETE",
				userMeta);
	}

}
