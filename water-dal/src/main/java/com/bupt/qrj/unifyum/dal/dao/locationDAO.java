/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

import org.springframework.dao.DataAccessException;

/**
 * @author renjun.qrj 2015年10月31日:下午6:00:31 com.bupt.qrj.dao.impl.UserMetaDAO
 *         unifyum-dal 用途:
 *
 */
public interface locationDAO {

	public void update(locationDO locationDO) throws DataAccessException;
	public locationnameDO get(locationnameDO locationnameDO);
	public void insert(locationinsertDO locationinsertDO) throws DataAccessException;
}
