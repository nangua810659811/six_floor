/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

import java.util.List;
import java.util.Map;

import com.bupt.qrj.unifyum.dal.dataobject.UserImageDO;

/**
 * @author renjun.qrj  2015年12月29日:上午7:31:43
 * com.bupt.qrj.unifyum.dal.dao.UserImageDAO
 * unifyum-dal
 * 用途: 
 *
 */
public interface UserImageDAO {

    public void addImage(UserImageDO image);

    public List<UserImageDO> queryImageByUser(String userName);

    public List<UserImageDO> queryImageByProps(Map<String, Object> paras);

    public UserImageDO getImage(Long id);

    public Integer count(Map<String, Object> paras);

}
