/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

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

    void update(UserTestDataDO dataDO);

    String get(String userName);
}
