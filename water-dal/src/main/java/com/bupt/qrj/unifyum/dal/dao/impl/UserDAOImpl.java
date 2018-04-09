/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import java.util.List;

import com.bupt.qrj.unifyum.dal.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import com.bupt.qrj.unifyum.dal.dataobject.UserDO;



/**
 * @author renjun.qrj  2016年4月4日:下午11:07:26
 * com.bupt.qrj.unifyum.dal.dao.impl.SkinTestDataDAOImpl
 * unifyum-dal
 * 用途: 
 *
 */
public class UserDAOImpl extends SqlMapClientDaoSupport implements UserDAO {
    
	 /** 日志 **/
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);
    public List<UserDO> list(String username) {
        
        List<UserDO> rets = this.getSqlMapClientTemplate().queryForList(
            "USER-DATA-LIST", username);
       
        if (rets == null || rets.size() == 0) {
           
            return null;
        }
       
        return rets;
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.SkinTestDataDAO#listUsers()
     */
    public List<String> listUsers() {
        
        List<String> rets = this.getSqlMapClientTemplate().queryForList(
            "USER-LIST");
        
        if (rets == null || rets.size() == 0) {
            
            return null;
        }
        return rets;
    }
    
    public UserDO get(UserDO user) throws DataAccessException {
		if (user == null) {
			throw new IllegalArgumentException(
					"Can't insert a null data(mobilelbsMerchantSubcategoryDO) object into db.");
		}
		if (user.getUserName() == null && user.getPassword() == null)
			return null;
		
		UserDO userDO = (UserDO) this.getSqlMapClientTemplate()
				.queryForObject("UNIFYUM-USER-GET", user);
		
		return userDO;
	}

}
