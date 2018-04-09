/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

import com.bupt.qrj.unifyum.dal.dataobject.EventInfoDO;
import com.bupt.qrj.unifyum.dal.dataobject.MapEventInfoDO;
import com.bupt.qrj.unifyum.dal.dataobject.MapHumanInfoDO;
import com.bupt.qrj.unifyum.dal.dataobject.MissionReturnDO;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author renjun.qrj 2015年10月31日:下午6:00:31 com.bupt.qrj.dao.impl.UserMetaDAO
 *         unifyum-dal 用途:
 *
 */
public interface MapInfoDAO {


    public List<MapHumanInfoDO> list(String worker_team);

    public String get_team(String phone) throws DataAccessException;

    public List<MapEventInfoDO> Event_list();

}
