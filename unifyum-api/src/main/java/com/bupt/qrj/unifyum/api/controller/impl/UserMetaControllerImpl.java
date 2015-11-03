/**
 * 
 */
package com.bupt.qrj.unifyum.api.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bupt.qrj.unifyum.api.controller.UserMetaController;
import com.bupt.qrj.unifyum.api.util.WebUtil;
import com.bupt.qrj.unifyum.dal.dao.UserMetaDAO;

/**
 * @author renjun.qrj 2015年10月31日:下午8:43:02
 *         com.bupt.qrj.unifyum.api.controller.impl.UserMetaControllerImpl
 *         unifyum-api 用途:
 *
 */
@Controller
@RequestMapping("/usermanagement.req")
public class UserMetaControllerImpl implements UserMetaController {

	private UserMetaDAO userMetaDAO;

	/** 日志 **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserMetaControllerImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bupt.qrj.unifyum.api.controller.UserMetaController#register(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

	@RequestMapping(method = { RequestMethod.POST }, params = "action=register")
	public void register(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		WebUtil.outData(response, "haha");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bupt.qrj.unifyum.api.controller.UserMetaController#login(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(method = { RequestMethod.POST }, params = "action=login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bupt.qrj.unifyum.api.controller.UserMetaController#logout(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(method = { RequestMethod.POST }, params = "action=logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bupt.qrj.unifyum.api.controller.UserMetaController#checkLogin(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(method = { RequestMethod.POST }, params = "action=checkLogin")
	public void checkLogin(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the userMetaDAO
	 */
	public UserMetaDAO getUserMetaDAO() {
		return userMetaDAO;
	}

	/**
	 * @param userMetaDAO
	 *            the userMetaDAO to set
	 */
	public void setUserMetaDAO(UserMetaDAO userMetaDAO) {
		this.userMetaDAO = userMetaDAO;
	}

}
