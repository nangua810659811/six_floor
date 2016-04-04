/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

import java.util.List;
import java.util.Map;

import com.bupt.qrj.unifyum.dal.dataobject.UserTestDataDO;

/**
 * @author renjun.qrj  2016年1月2日:下午9:09:23
 * com.bupt.qrj.unifyum.dal.dao.UserTestDataDAO
 * unifyum-dal
 * 用途: 
 *
 */
public interface UserTestDataDAO {

    void insert(UserTestDataDO dataDO);

    /**
     * 获取最新的一条
     * */
    UserTestDataDO get(String userName);

    /**
     * 获取全部的,用userName 过滤，可以传limit和start
     * */
    List<UserTestDataDO> list(Map params);
}
