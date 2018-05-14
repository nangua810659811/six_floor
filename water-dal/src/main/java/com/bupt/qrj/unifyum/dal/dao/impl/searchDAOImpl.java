/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;


import com.bupt.qrj.unifyum.dal.dao.searchDAO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.HashMap;
import java.util.List;


/**
 * @author renjun.qrj  2016年4月4日:下午11:07:26
 * com.bupt.qrj.unifyum.dal.dao.impl.SkinTestDataDAOImpl
 * unifyum-dal
 * 用途: 
 *
 */
public class searchDAOImpl extends SqlMapClientDaoSupport implements searchDAO {
    
	 /** 日志 **/
/*    private static final Logger LOGGER = LoggerFactory.getLogger(seekwjsDAOImpl.class);*/
    public List<searchDO> list(String worker_name,String mission_name,String mission_type,String mission_level,String timemin,String timemax) {

        HashMap<String,Object> abbs = new HashMap<String,Object>();
        abbs.put("worker_name",worker_name);
        abbs.put("mission_name",mission_name);
        abbs.put("mission_type",mission_type);
        abbs.put("mission_level",mission_level);
        abbs.put("timemin",timemin);
        abbs.put("timemax",timemax);
        List<searchDO> rets = this.getSqlMapClientTemplate().queryForList("search-mission",abbs);
       
        return rets;
    }

}
