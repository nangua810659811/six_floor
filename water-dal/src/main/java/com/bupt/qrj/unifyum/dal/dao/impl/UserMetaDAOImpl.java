/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import java.util.Date;
import java.util.List;

import com.bupt.qrj.unifyum.dal.dao.UserMetaDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

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
		LOGGER.debug("do the userMeta add");
		userMeta.setGmtCreated(new Date());
		userMeta.setGmtModified(new Date());
		this.getSqlMapClientTemplate().insert("UNIFYUM-USER-META-INSERT",
				userMeta);
		LOGGER.debug("do the userMeta success");

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
		LOGGER.debug("do the userMeta update password");
		userMeta.setGmtModified(new Date());
		this.getSqlMapClientTemplate().update("UNIFYUM-USER-META-UPDATE",
				userMeta);
		LOGGER.debug("do the userMeta update password success");
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
		LOGGER.debug("do the userMeta get ");
		UserMetaDO resultDO = (UserMetaDO) this.getSqlMapClientTemplate()
				.queryForObject("UNIFYUM-USER-META-GET", userMeta);
		LOGGER.debug("do the userMeta get success");
		return resultDO;
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
		LOGGER.debug("do the userMeta delete");
		this.getSqlMapClientTemplate().delete("UNIFYUM-USER-META-DELETE",
				userMeta);
		LOGGER.debug("do the userMeta success");
	}

	public List<String> listUsers() {
	
		 LOGGER.debug("do the user list start");
	       
			List<String> rets = this.getSqlMapClientTemplate().queryForList(
	            "UNIFYUM-USER-LIST");
	        LOGGER.debug("do the TestData user list over");
	        if (rets == null || rets.size() == 0) {
	            LOGGER.debug("do the user list return null");
	            return null;
	        }
	        return rets;
	}

	public List<UserMetaDO> list(String username) {
		
		LOGGER.debug("do the DetailUserData list start");
        List<UserMetaDO> rets = this.getSqlMapClientTemplate().queryForList(
            "UNIFYUM-DETAIL-USER-DATA-LIST",username);
        LOGGER.debug("do the DetailUserData list over");
        if (rets == null || rets.size() == 0) {
            LOGGER.debug("do the DetailUserData list return null");
            return null;
        }
        LOGGER.debug("do the DetailUserData list return data list");
        return rets;
	}

}
