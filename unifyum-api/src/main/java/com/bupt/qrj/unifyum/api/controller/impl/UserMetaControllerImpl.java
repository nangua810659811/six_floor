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

import com.alibaba.fastjson.JSONObject;
import com.bupt.qrj.unifyum.api.controller.UserMetaController;
import com.bupt.qrj.unifyum.api.result.UserRegisterResult;
import com.bupt.qrj.unifyum.api.result.UserUnRegisterResult;
import com.bupt.qrj.unifyum.dal.dao.UserMetaDAO;
import com.bupt.qrj.unifyum.dal.dataobject.UserMetaDO;
import com.bupt.qrj.unifyum.util.http.HttpOutUtil;

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
		UserRegisterResult result = new UserRegisterResult();
		result.setSuccess(false);
		result.setErrMsg("");
		try {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			if (userName == null || password == null || userName.isEmpty()
					|| password.isEmpty()) {
				result.setErrMsg("输入参数有误");
			} else {
				// 检查用户名是否已经存在
				UserMetaDO uMetaDO = new UserMetaDO();
				uMetaDO.setUserName(userName);
				UserMetaDO queryRet = userMetaDAO.get(uMetaDO);
				if (queryRet != null) {
					result.setErrMsg("用户名已经存在");
				} else {
					uMetaDO.setPassword(password);
					userMetaDAO.add(uMetaDO);
					// 插入新用户成功
					result.setSuccess(true);
				}
			}// else userName!=null
		} catch (Exception e) {
			result.setErrMsg(e.getMessage());
			LOGGER.warn("exception when register: " + e.getMessage());
		}
		HttpOutUtil.outData(response, JSONObject.toJSONString(result));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bupt.qrj.unifyum.api.controller.UserMetaController#unregister(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(method = { RequestMethod.POST }, params = "action=unregister")
	public void unregister(HttpServletRequest request,
			HttpServletResponse response) {
		UserUnRegisterResult result = new UserUnRegisterResult();
		result.setSuccess(false);
		try {
			String userName = request.getParameter("userName");
			if (userName == null || userName.isEmpty()) {
				result.setErrMsg("输入参数有误");
			} else {
				UserMetaDO uMetaDO = new UserMetaDO();
				uMetaDO.setUserName(userName);
				userMetaDAO.delete(uMetaDO);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setErrMsg(e.getMessage());
		}
		HttpOutUtil.outData(response, JSONObject.toJSONString(result));
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
