/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao.impl;

import com.bupt.qrj.unifyum.dal.dao.arrangeseekDAO;
import com.bupt.qrj.unifyum.dal.dataobject.arrangeseekDO;
import com.bupt.qrj.unifyum.dal.dataobject.arrangeseekmisDO;
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
public class arrangeseekDAOImpl extends SqlMapClientDaoSupport implements arrangeseekDAO {
    
	 /** 日志 **/
/*    private static final Logger LOGGER = LoggerFactory.getLogger(seekwjsDAOImpl.class);*/
    public List<arrangeseekDO> listarrangeseek(String timemmin) {

//        HashMap<String,Object> abbys = new HashMap<String,Object>();
//        abbys.put("worker_name",worker_name);
//        abbys.put("timemmin",timemmin);
//        abbys.put("timemmax",timemmax);
/*        System.out.println(timemmin+timemmax);*/
        List<arrangeseekDO> rets = this.getSqlMapClientTemplate().queryForList("arrange-seek-null", timemmin);
        /*System.out.println(rets);*/
        return rets;
    }
    public List<arrangeseekmisDO> listarrangeseekmis(String timemmin, String timemmax) {

        HashMap<String,Object> abbys = new HashMap<String,Object>();

        abbys.put("timemin",timemmin);
        abbys.put("timemax",timemmax);
//        System.out.println(timemmin+timemmax);
        List<arrangeseekmisDO> rets = this.getSqlMapClientTemplate().queryForList("arrange-seek-mis", abbys);
        /*System.out.println(rets);*/
        return rets;
    }
}
