/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

import com.bupt.qrj.unifyum.dal.dataobject.arrangeinsertDO;
import com.bupt.qrj.unifyum.dal.dataobject.feedbackDO;
import com.bupt.qrj.unifyum.dal.dataobject.locationDO;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author renjun.qrj 2015年10月31日:下午6:00:31 com.bupt.qrj.dao.impl.UserMetaDAO
 *         unifyum-dal 用途:
 *
 */
public interface feedbackDAO {

	public void updatedata(feedbackDO feedbackDO) throws DataAccessException;
	public void updatepic(feedbackDO feedbackDO) throws DataAccessException;
    public void insertdata(feedbackDO feedbackDO) throws DataAccessException;
    public void insertpic(feedbackDO feedbackDO) throws DataAccessException;
    public List<feedbackDO> seek(String mission_id,String event_id,String work_name);
}
