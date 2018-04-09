/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;


import com.bupt.qrj.unifyum.dal.dataobject.UserMetaDO;

/**
 * @author renjun.qrj 2015年10月31日:下午6:00:31 com.bupt.qrj.dao.impl.UserMetaDAO
 *         unifyum-dal 用途:
 *
 */
public interface UserMetaDAO {

	/**
	 * add 2015年10月31日 2015年10月31日.下午6:03:54
	 * 
	 * @param userMeta
	 * @return void - 在数据库中新增一个用户数据
	 */
	public void add(UserMetaDO userMeta) throws DataAccessException;

	/**
	 * update 2015年10月31日 2015年10月31日.下午6:04:00
	 * 
	 * @param userMeta
	 * @return void - 更新一个用户数据的信息
	 */
	public void update(UserMetaDO userMeta) throws DataAccessException;

	/**
	 * get 2015年10月31日 2015年10月31日.下午6:04:07
	 * 
	 * @param UserMetaDO
	 * @return UserMetaDO - 从数据库中获取一个用户的数据
	 */
	public UserMetaDO get(UserMetaDO userMeta) throws DataAccessException;

	/**
	 * delete 2015年10月31日 2015年10月31日.下午6:49:03
	 * 
	 * @param UserMetaDO
	 * @return: void - 删除一条数据
	 */
	public void delete(UserMetaDO userMeta) throws DataAccessException;

	public List<String> listUsers();
	
	public List<UserMetaDO> list(String username);
}
