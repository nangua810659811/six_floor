/**
 * 
 */
package com.bupt.qrj.unifyum.api.result;

/**
 * @author renjun.qrj 2015年10月31日:下午8:43:13
 *         com.bupt.qrj.unifyum.api.result.BaseResult unifyum-api 用途:
 *
 */
public class BaseResult {
	/** 是否出现了后台错误 */
	private boolean success;
	/** 错误信息描述 */
	private String errMsg;
	/** 返回值代码 */
	private String code;

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg
	 *            the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
