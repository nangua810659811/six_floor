/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dataobject;

/**
 * @author renjun.qrj 2015年10月31日:下午5:52:08 com.bupt.qrj.dataobject.UserMeta
 *         unifyum-dal 用途: - 用户账户基本信息
 *
 */
public class collect2DO {



	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}



	private String time;

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getRepair() {
		return repair;
	}

	public void setRepair(int repair) {
		this.repair = repair;
	}

	public int getDaily() {
		return daily;
	}

	public void setDaily(int daily) {
		this.daily = daily;
	}

	private int temp;




	private int repair;

	private int daily;
	
}
