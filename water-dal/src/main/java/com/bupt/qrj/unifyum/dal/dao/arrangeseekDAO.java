/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dao;

import java.util.List;

/**
 * @author renjun.qrj 2015年10月31日:下午6:00:31 com.bupt.qrj.dao.impl.UserMetaDAO
 *         unifyum-dal 用途:
 *
 */
public interface arrangeseekDAO {


	public List<arrangeseekDO> listarrangeseek(String timemmin);

	public List<arrangeseekmisDO> listarrangeseekmis(String timemmin, String timemmax);
}
