/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

import com.bupt.qrj.unifyum.dal.dataobject.locationDO;
import com.bupt.qrj.unifyum.dal.dataobject.misconditionDO;
import org.springframework.dao.DataAccessException;

/**
 * @author renjun.qrj 2015年10月31日:下午6:00:31 com.bupt.qrj.dao.impl.UserMetaDAO
 *         unifyum-dal 用途:
 *
 */
public interface conditionDAO {

	public void update(misconditionDO misconditionDO) throws DataAccessException;

}
