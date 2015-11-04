/**
 * 
 */
package com.bupt.qrj.unifyum.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author renjun.qrj 2015年10月31日:下午8:42:31
 *         com.bupt.qrj.unifyum.api.controller.UserMetaController unifyum-api
 *         用途:
 *
 */
public interface UserMetaController {

	public void register(HttpServletRequest request,
			HttpServletResponse response);

	public void unregister(HttpServletRequest request,
			HttpServletResponse response);

	public void login(HttpServletRequest request, HttpServletResponse response);

	public void logout(HttpServletRequest request, HttpServletResponse response);

	public void checkLogin(HttpServletRequest request,
			HttpServletResponse response);

}
