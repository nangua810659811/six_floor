/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

import java.util.List;

import com.bupt.qrj.unifyum.dal.dataobject.UserDO;

/**
 * @author renjun.qrj 2015年10月31日:下午6:00:31 com.bupt.qrj.dao.impl.UserMetaDAO
 *         unifyum-dal 用途:
 *
 */
public interface UserDAO {

	
	public List<String> listUsers();
	
	public List<UserDO> list(String username);

	public UserDO get(UserDO userDO);
}
