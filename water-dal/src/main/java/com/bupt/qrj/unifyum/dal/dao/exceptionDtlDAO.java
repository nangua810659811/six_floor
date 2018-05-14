/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author renjun.qrj 2015年10月31日:下午6:00:31 com.bupt.qrj.dao.impl.UserMetaDAO
 *         unifyum-dal 用途:
 *
 */
public interface exceptionDtlDAO {




	public List<exceptionDtlDO> list(String ymmin, String ymmax,String status);

	public void insert(insertExceptionDO insertExceptionDO) throws DataAccessException;
}
