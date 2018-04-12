/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dataobject;

/**
 * @author renjun.qrj 2015年10月31日:下午5:52:08 com.bupt.qrj.dataobject.UserMeta
 *         unifyum-dal 用途: - 用户账户基本信息
 *
 */
public class arrfeedbackDO {

	public String getMission_description() {
		return mission_description;
	}

	public void setMission_description(String mission_description) {
		this.mission_description = mission_description;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getCover_fields() {
		return cover_fields;
	}

	public void setCover_fields(String cover_fields) {
		this.cover_fields = cover_fields;
	}

	public String getMission_level() {
		return mission_level;
	}

	public void setMission_level(String mission_level) {
		this.mission_level = mission_level;
	}

	public String getMission_source() {
		return mission_source;
	}

	public void setMission_source(String mission_source) {
		this.mission_source = mission_source;
	}

	public String getMission_addition() {
		return mission_addition;
	}

	public void setMission_addition(String mission_addition) {
		this.mission_addition = mission_addition;
	}

	public String getWork_instrument() {
		return work_instrument;
	}

	public void setWork_instrument(String work_instrument) {
		this.work_instrument = work_instrument;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	private String mission_description;

	private String mission;
	private String cover_fields;
	private String mission_level;

	private String mission_source;
	private String mission_addition;
	private String work_instrument;
	private String event;


	
}
