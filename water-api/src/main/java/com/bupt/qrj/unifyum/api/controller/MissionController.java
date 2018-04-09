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
public interface MissionController {

	

	public void MissionReturn(HttpServletRequest request, HttpServletResponse response);
	public void seekwjs(HttpServletRequest request, HttpServletResponse response);
	public void mapinfo(HttpServletRequest request, HttpServletResponse response);
	public void login(HttpServletRequest request, HttpServletResponse response);
	public void checkInfo(HttpServletRequest request, HttpServletResponse response);
    public void location(HttpServletRequest request, HttpServletResponse response);
    public void condition(HttpServletRequest request, HttpServletResponse response);
    public void search(HttpServletRequest request, HttpServletResponse response);
}
