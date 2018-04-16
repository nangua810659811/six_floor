/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dataobject;

/**
 * @author renjun.qrj 2015年10月31日:下午5:52:08 com.bupt.qrj.dataobject.UserMeta
 *         unifyum-dal 用途: - 用户账户基本信息
 *
 */
public class collectDO {


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}



	private String time;


	public int getAlarm() {
		return alarm;
	}

	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}

	public int getHidden_danger() {
		return hidden_danger;
	}

	public void setHidden_danger(int hidden_danger) {
		this.hidden_danger = hidden_danger;
	}

	public int getQuestion() {
		return question;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

	private int alarm;



	private int hidden_danger;

	private int question;
	
}
