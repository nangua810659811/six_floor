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
public interface lockController {


	public void login(HttpServletRequest request, HttpServletResponse response);

	public void signin(HttpServletRequest request, HttpServletResponse response);

	public void seekwjs(HttpServletRequest request, HttpServletResponse response);

	public void seekyjs(HttpServletRequest request, HttpServletResponse response);

    public void refusemission(HttpServletRequest request, HttpServletResponse response);

    public void location(HttpServletRequest request, HttpServletResponse response);

    public void finish(HttpServletRequest request, HttpServletResponse response);

	public void refuseinfo(HttpServletRequest request, HttpServletResponse response);

    public void missiondownload(HttpServletRequest request, HttpServletResponse response);

	public void houseinfo(HttpServletRequest request, HttpServletResponse response);

	public void readytodo(HttpServletRequest request, HttpServletResponse response);

	public void alreadydone(HttpServletRequest request, HttpServletResponse response);

    public void mapshow(HttpServletRequest request, HttpServletResponse response);

    public void refusefind(HttpServletRequest request, HttpServletResponse response);

	public void setmission(HttpServletRequest request, HttpServletResponse response);

    public void missiondetail(HttpServletRequest request, HttpServletResponse response);

    public void findfinish(HttpServletRequest request, HttpServletResponse response);

    public void checkmission(HttpServletRequest request, HttpServletResponse response);

    public void loginss(HttpServletRequest request, HttpServletResponse response);

    public void changepass(HttpServletRequest request, HttpServletResponse response);

    public void seekall(HttpServletRequest request, HttpServletResponse response);

    public void picinfo(HttpServletRequest request, HttpServletResponse response);

    public void recentmis(HttpServletRequest request, HttpServletResponse response);

    public void sweeponesweep(HttpServletRequest request, HttpServletResponse response);

    public void changepassapp(HttpServletRequest request, HttpServletResponse response);

    public void postteam(HttpServletRequest request, HttpServletResponse response);

    public void opinionfeedback(HttpServletRequest request, HttpServletResponse response);

    public void checkcode(HttpServletRequest request, HttpServletResponse response);

    public void headpic(HttpServletRequest request, HttpServletResponse response);

}


