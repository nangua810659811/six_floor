/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dataobject;

/**
 * @author renjun.qrj 2015年10月31日:下午5:52:08 com.bupt.qrj.dataobject.UserMeta
 *         unifyum-dal 用途: - 用户账户基本信息
 *
 */
public class SeekWjsDO {


	public String getMission_id() {
		return mission_id;
	}

	public void setMission_id(String mission_id) {
		this.mission_id = mission_id;
	}

	public String getMission_condition() {
		return mission_condition;
	}

	public void setMission_condition(String mission_condition) {
		this.mission_condition = mission_condition;
	}

	public String getMission_addition() {
		return mission_addition;
	}

	public void setMission_addition(String mission_addition) {
		this.mission_addition = mission_addition;
	}

	private String mission_id;
	private String mission_condition;

	private String mission_addition;



	
}
