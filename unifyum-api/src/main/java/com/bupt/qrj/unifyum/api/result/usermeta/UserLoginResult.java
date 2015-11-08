/**
 * 
 */
package com.bupt.qrj.unifyum.api.result.usermeta;

import java.util.Date;

import com.bupt.qrj.unifyum.api.result.BaseResult;

/**
 * @author renjun.qrj 2015年11月8日:下午7:33:06
 *         com.bupt.qrj.unifyum.api.result.UserLoginResult unifyum-api 用途:
 *
 */
public class UserLoginResult extends BaseResult {
	private String authToken;
	private Date loginTime;

	/**
	 * @return the authToken
	 */
	public String getAuthToken() {
		return authToken;
	}

	/**
	 * @param authToken
	 *            the authToken to set
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	/**
	 * @return the loginTime
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime
	 *            the loginTime to set
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

}
