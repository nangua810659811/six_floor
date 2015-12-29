/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bupt.qrj.unifyum.dal.dao.UserImageDAO;
import com.bupt.qrj.unifyum.dal.dataobject.UserImageDO;

/**
 * @author renjun.qrj  2015年12月29日:上午7:31:54
 * com.bupt.qrj.unifyum.dal.dao.impl.UserImageDAOImpl
 * unifyum-dal
 * 用途: 
 *
 */
public class UserImageDAOImpl extends SqlMapClientDaoSupport implements UserImageDAO {

    /** 日志 **/
    private static final Logger LOGGER = LoggerFactory.getLogger(UserImageDAOImpl.class);

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.UserImageDAO#addImage(com.bupt.qrj.unifyum.dal.dataobject.UserImageDO)
     */
    public void addImage(UserImageDO image) {
        if (image == null) {
            throw new IllegalArgumentException(
                "Can't insert a null data(mobilelbsMerchantSubcategoryDO) object into db.");
        }
        LOGGER.debug("do the userImage add");
        this.getSqlMapClientTemplate().insert("UNIFYUM-USER-IMAGE-INSERT", image);
        LOGGER.debug("do the userImage success");
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.UserImageDAO#queryImageByUser(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<UserImageDO> queryImageByUser(String userName) {
        if (userName == null || userName.isEmpty())
            return null;
        return this.getSqlMapClientTemplate().queryForList("UNIFYUM-USER-IMAGE-LIST", userName);
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.UserImageDAO#getImage(java.lang.Integer)
     */
    public UserImageDO getImage(Long id) {
        if (id == null)
            return null;
        return (UserImageDO) this.getSqlMapClientTemplate().queryForObject(
            "UNIFYUM-USER-IMAGE-GET", id);
    }

}
