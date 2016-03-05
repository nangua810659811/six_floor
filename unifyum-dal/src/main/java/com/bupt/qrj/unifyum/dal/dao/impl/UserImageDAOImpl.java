/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<UserImageDO> queryImageByUser(String userName) {
        HashMap paraMap = new HashMap<String, Object>();
        List<UserImageDO> imageDOList = null;
        if (userName == null || userName.isEmpty())
            return null;
        //输入参数
        paraMap.put("userName", userName);
        paraMap.put("sortKey", "record_time");
        paraMap.put("order", "desc");
        @SuppressWarnings("rawtypes")
        List<HashMap> retList = this.getSqlMapClientTemplate().queryForList(
            "UNIFYUM-USER-IMAGE-LIST", paraMap);
        if (retList != null && !retList.isEmpty()) {
            imageDOList = new ArrayList<UserImageDO>();
            for (HashMap imageDataMap : retList) {
                imageDOList.add(build(imageDataMap));
            }
        }
        return imageDOList;
    }

    /**
     * build 2016年3月5日
     * 2016年3月5日.下午11:17:29
     * return: UserImageDO 
     */
    private UserImageDO build(HashMap imageDataMap) {
        UserImageDO imageDO = new UserImageDO();
        imageDO.setId(new Long((Integer) imageDataMap.get("id")));
        imageDO.setUserName((String) imageDataMap.get("user_name"));
        imageDO.setBlood((String) imageDataMap.get("moisten"));
        imageDO.setBlood((String) imageDataMap.get("blood"));
        imageDO.setBlood((String) imageDataMap.get("color"));
        imageDO.setBlood((String) imageDataMap.get("texture"));
        imageDO.setBlood((String) imageDataMap.get("satin"));
        Date timestamp = (Date) imageDataMap.get("record_time");
        imageDO.setRecordTime(timestamp);
        return imageDO;
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.UserImageDAO#queryImageByProps(java.util.Map)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<UserImageDO> queryImageByProps(Map<String, Object> paras) {
        List<UserImageDO> imageDOList = null;
        //如果没有设定sortKey 和 order，配置一个默认值
        if (!paras.containsKey("sortKey")) {
            paras.put("sortKey", "record_time");

        }
        if (!paras.containsKey("order")) {
            paras.put("order", "desc");
        }
        //检查返回值
        List<HashMap> retList = this.getSqlMapClientTemplate().queryForList(
            "UNIFYUM-USER-IMAGE-LIST", paras);
        if (retList != null && !retList.isEmpty()) {
            imageDOList = new ArrayList<UserImageDO>();
            for (HashMap imageDataMap : retList) {
                imageDOList.add(build(imageDataMap));
            }
        }
        return imageDOList;
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

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.dal.dao.UserImageDAO#count(java.lang.String)
     */
    public Integer count(Map<String, Object> paras) {
        //没有用户名的时候返回null
        if (!paras.containsKey("userName"))
            return null;
        return (Integer) this.getSqlMapClientTemplate().queryForObject("UNIFYUM-USER-IMAGE-COUNT",
            paras);
    }

}
