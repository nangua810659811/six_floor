/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import com.bupt.qrj.unifyum.dal.dao.collectDAO;
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
public class collectDAOImpl extends SqlMapClientDaoSupport implements collectDAO {
    
	 /** 日志 **/
/*    private static final Logger LOGGER = LoggerFactory.getLogger(seekwjsDAOImpl.class);*/
    public List<collectDO> list(String ymmin, String ymmax) {

        HashMap<String,Object> abbs = new HashMap<String,Object>();
        abbs.put("ymmin",ymmin);
        abbs.put("ymmax",ymmax);
        List<collectDO> rets = this.getSqlMapClientTemplate().queryForList("select-exception",abbs);
       
        return rets;
    }
    public List<collect1DO> list1(String ymmin, String ymmax, String workshop) {

        HashMap<String,Object> abbs = new HashMap<String,Object>();
        abbs.put("ymmin",ymmin);
        abbs.put("ymmax",ymmax);
        abbs.put("workshop",workshop);
        List<collect1DO> rets = this.getSqlMapClientTemplate().queryForList("select-collect1",abbs);

        return rets;
    }
    public List<collect2DO> list2(String ymmin, String ymmax) {

        HashMap<String,Object> abbs = new HashMap<String,Object>();
        abbs.put("ymmin",ymmin);
        abbs.put("ymmax",ymmax);
        List<collect2DO> rets = this.getSqlMapClientTemplate().queryForList("select-collect2",abbs);

        return rets;
    }

}
