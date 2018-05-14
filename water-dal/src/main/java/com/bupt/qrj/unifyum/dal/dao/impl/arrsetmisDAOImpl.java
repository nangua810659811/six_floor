/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import com.bupt.qrj.unifyum.dal.dao.arrsetmisDAO;
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
public class arrsetmisDAOImpl extends SqlMapClientDaoSupport implements
		arrsetmisDAO {

	/** 日志 **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(arrsetmisDAOImpl.class);


	public void insert(arrsetmisDO arrsetmisDO) throws DataAccessException {


		this.getSqlMapClientTemplate().insert("arrange-worker-insert",
				arrsetmisDO);
    }


	public arrfeedbackDO get(arrfeedbackDO arrfeedbackDO) throws DataAccessException {

		if (arrfeedbackDO.getMission() == null)
			return null;
		//System.out.println("1");
		arrfeedbackDO resultDO = (arrfeedbackDO) this.getSqlMapClientTemplate()
				.queryForObject("select-arrfeedback", arrfeedbackDO);
		//System.out.println("2");
		return resultDO;
	}


}
