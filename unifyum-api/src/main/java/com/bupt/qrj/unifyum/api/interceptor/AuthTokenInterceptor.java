/**
 * 
 */
package com.bupt.qrj.unifyum.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.bupt.qrj.unifyum.api.result.usermeta.CheckLoginResult;
import com.bupt.qrj.unifyum.dal.dao.UserMetaDAO;
import com.bupt.qrj.unifyum.dal.dataobject.UserMetaDO;

/**
 * @author renjun.qrj  2015年12月29日:上午6:24:55
 * com.bupt.qrj.unifyum.api.interceptor.LoginInterceptor
 * unifyum-api
 * 用途: 
 *
 */
public class AuthTokenInterceptor extends HandlerInterceptorAdapter {
    private UserMetaDAO userMetaDAO;

    /**
     * @return the userMetaDAO
     */
    public UserMetaDAO getUserMetaDAO() {
        return userMetaDAO;
    }

    /**
     * @param userMetaDAO the userMetaDAO to set
     */
    public void setUserMetaDAO(UserMetaDAO userMetaDAO) {
        this.userMetaDAO = userMetaDAO;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler)
                                                                                                  throws Exception {
        CheckLoginResult result = new CheckLoginResult();
        result.setSuccess(false);

        String[] notCheckURL = new String[] { "/usermanagement.req?action=login",
                "/usermanagement.req?action=register", "/usermanagement.req?action=unregister",
                "/imagemanagement.req?action=jqList", "/imagemanagement.req?action=showImg" };
        String cPath = req.getContextPath();
        String URI = req.getRequestURI();
        String testURL = URI.substring(cPath.length());
        String action = req.getParameter("action");
        testURL = testURL + "?action=" + action;

        for (String whiteList : notCheckURL) {
            if (testURL.equals(whiteList))
                return true;
        }

        String authToken = req.getParameter("authToken");
        try {
            if (authToken == null || authToken.isEmpty()) {
                result.setErrMsg("token 为空,不允许使用接口");
            } else {
                // 获取用户的数据
                UserMetaDO uMetaDO = new UserMetaDO();
                uMetaDO.setAuthToken(authToken);
                UserMetaDO userMeta = userMetaDAO.get(uMetaDO);
                if (userMeta == null) {
                    result.setErrMsg("登录信息检查失败");
                } else {
                    req.setAttribute("username", userMeta.getUserName());
                    return true;
                }
            }
        } catch (Exception e) {
            result.setErrMsg("登录信息检查失败，" + e.getCause());
        }

        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(JSONObject.toJSONString(result));
        return false;
    }
}
