package com.bupt.qrj.unifyum.api.test.controller;

/**
 * 
 */

import static junit.framework.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.bupt.qrj.unifyum.util.http.HttpClientUtil;

/**
 * @author renjun.qrj 2015年11月4日:下午5:39:50
 *         com.bupt.qrj.unifyum.util.test.http.HttpClientUtilTestCase
 *         unifyum-util 用途:
 *
 */
@Ignore("not need to run with maven")
public class UserMetaControllerTestCase {

    private final String HOST           = "123.57.221.17";
    private final String REGISTER_URL   = "http://" + HOST
                                          + ":8080/unifyum/usermanagement.req?action=register";

    private final String UNREGISTER_URL = "http://" + HOST
                                          + ":8080/unifyum/usermanagement.req?action=unregister";

    private final String LOGIN_URL      = "http://" + HOST
                                          + ":8080/unifyum/usermanagement.req?action=login";

    private final String LOGOUT_URL     = "http://" + HOST
                                          + ":8080/unifyum/usermanagement.req?action=logout";

    private final String CHECKLOGIN_URL = "http://" + HOST
                                          + ":8080/unifyum/usermanagement.req?action=checkLogin";

    @Ignore
    @Test
    public void testRegister() {

        JSONObject rs_params = new JSONObject();
        rs_params.put("userName", "qurenjun");
        rs_params.put("password", "123456");
        // 先做注册
        String httpRet = HttpClientUtil.doPostRequest(REGISTER_URL, rs_params);
        JSONObject rs_ret = JSONObject.parseObject(httpRet);
        assertTrue(rs_ret.getBoolean("success"));

    }

    @Test
    public void testLoginAndLogout() {
        JSONObject rs_params = new JSONObject();
        rs_params.put("userName", "qurenjun");
        rs_params.put("password", "123456");
        // 先做登录
        String httpRet = HttpClientUtil.doPostRequest(LOGIN_URL, rs_params);
        JSONObject rs_ret = JSONObject.parseObject(httpRet);
        assertTrue(rs_ret.getBoolean("success"));
        String authToken = rs_ret.getString("authToken");
        // 然后做登录检查
        rs_params.clear();
        rs_params.put("authToken", authToken);
        httpRet = HttpClientUtil.doPostRequest(CHECKLOGIN_URL, rs_params);
        rs_ret = JSONObject.parseObject(httpRet);
        assertTrue(rs_ret.getBoolean("success"));
        // 然后登出
        rs_params.put("userName", "qurenjun");
        httpRet = HttpClientUtil.doPostRequest(LOGOUT_URL, rs_params);
        rs_ret = JSONObject.parseObject(httpRet);
        assertTrue(rs_ret.getBoolean("success"));
        // 然后检查登录
        rs_params.clear();
        rs_params.put("authToken", authToken);
        httpRet = HttpClientUtil.doPostRequest(CHECKLOGIN_URL, rs_params);
        rs_ret = JSONObject.parseObject(httpRet);
        assertTrue(!rs_ret.getBoolean("success"));
    }

    @Ignore
    @Test
    public void testUnRegister() {
        JSONObject rs_params = new JSONObject();
        rs_params.put("userName", "111");
        rs_params.put("password", "123456");

        // 再做注销
        String httpRet = HttpClientUtil.doPostRequest(UNREGISTER_URL, rs_params);
        JSONObject rs_ret = JSONObject.parseObject(httpRet);
        assertTrue(rs_ret.getBoolean("success"));
    }
}
