/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

import java.util.List;

import com.bupt.qrj.unifyum.dal.dataobject.SkinTestDataDO;

/**
 * @author renjun.qrj  2016年4月4日:下午11:06:21
 * com.bupt.qrj.unifyum.dal.dao.SkinTestDataDAO
 * unifyum-dal
 * 用途: 
 *
 */
public interface SkinTestDataDAO {
    void insert(SkinTestDataDO dataDO);

    /**
     * 获取全部的,用userName 过滤，可以传limit和start
     * */
    List<SkinTestDataDO> list(String userName);

    /**
     * 查看所有的用户名
     * */
    List<String> listUsers();

}
