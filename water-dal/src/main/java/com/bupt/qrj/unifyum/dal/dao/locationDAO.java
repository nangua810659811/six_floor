/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

import com.bupt.qrj.unifyum.dal.dataobject.arrangeinsertDO;
import com.bupt.qrj.unifyum.dal.dataobject.locationDO;

import com.bupt.qrj.unifyum.dal.dataobject.locationinsertDO;
import com.bupt.qrj.unifyum.dal.dataobject.locationnameDO;
import org.springframework.dao.DataAccessException;

import java.util.List;

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
