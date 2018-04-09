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
public interface arrangeController {


	public void arrangeinsert(HttpServletRequest request, HttpServletResponse response);
	public void arrangeseekall(HttpServletRequest request, HttpServletResponse response);
	public void arrangeseek(HttpServletRequest request, HttpServletResponse response);
    public void arrangepermis(HttpServletRequest request, HttpServletResponse response);
	public void MissionReturn(HttpServletRequest request, HttpServletResponse response);
}


