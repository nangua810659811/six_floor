/**
 * 
 */
package com.bupt.qrj.unifyum.api.controller.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bupt.qrj.unifyum.dal.dao.impl.UserDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bupt.qrj.unifyum.api.controller.UserController;
import com.bupt.qrj.unifyum.dal.dataobject.UserDO;
import com.bupt.qrj.unifyum.util.http.HttpOutUtil;

/**
 * @author renjun.qrj 2015年10月31日:下午8:43:02
 *         com.bupt.qrj.unifyum.api.controller.impl.UserMetaControllerImpl
 *         unifyum-api 用途:
 *
 */
@Controller
@RequestMapping("/user.req")
public class UserControllerImpl implements UserController {

//    private UserDAO    userDAO;
//
//    public void setUserDAO(UserDAO userDAO) {
//		this.userDAO = userDAO;
//	}

	/** 日志 **/
    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerImpl.class);
    
    public static ApplicationContext getContext() {
		//获得Spring中定义的Bean实例，两个以上加 new String[]
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/spring/unifyum-dal-dao.xml", "META-INF/spring/unifyum-dal-db.xml" });
		return context;
	}


    @RequestMapping(method = { RequestMethod.POST }, params = "action=login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
    	
    	ApplicationContext context=getContext();
    	UserDAOImpl userDAO=(UserDAOImpl) context.getBean("UserDAO");

        JSONObject result = new JSONObject();
        result.put("result",10001);
   

        try {
        	
//        	HttpURLConnection urlConnection=null;
//			
//        	InputStream inputStream = urlConnection.getInputStream();  
//        	String encoding = urlConnection.getContentEncoding();  
//        	String body = IOUtils.toString(inputStream, encoding);  
//        	
//        	System.out.println("body="+body);  
        	
        	
        	String body = request.getParameter("login");
        	System.out.println("login="+body);
        	
//        	JSONObject  map = JSON.parseObject(body);
//        	System.out.println(map.getString("mobileNo")); 

        	Map map = JSON.parseObject(body, Map.class);
        	System.out.println(map.get("mobileNo"));
        	String userName = (String) map.get("mobileNo");
        	String password = (String) map.get("password");
        	
//        	String userName = map.getString("mobileNo");
//        	String password = map.getString("password");
        	
           // String userName = request.getParameter("mobileNo");
            //String password = request.getParameter("password");
            // 先是参数检查
            if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
                result.put("errMsg","输入参数有误");
            } else {
                // 获取用户的数据
                UserDO userDO = new UserDO();
                userDO.setUserName(userName);
                
                UserDO user = userDAO.get(userDO);
                
                if (user == null) {
                    result.put("errMsg","用户名输入错误");
                } else {				
                    // 检查用户密码是否正确
                    if (password.equals(user.getPassword())) {
                    	userDO.setPassword(password);
                        /*Date loginTime = new Date();
                        String authToken = UserAuthTokenGenerator.generate(userName, loginTime);
                        user.setAuthToken(authToken);
                        user.setLoginTime(loginTime);
                        user.setGmtModified(new Date());
                        int count = user.getLoginCount();
                        user.setLoginCount(++count);
                        userDAO.update(user);*/
                    	result.put("authToken",userName);
                    	result.put("errMsg","登录成功");
                        result.put("result",10000);                      
                       /* result.setAuthToken(authToken);
                        result.setLoginTime(loginTime);*/
                    }else{
                    	result.put("errMsg", "密码错误");
                    }
                }
            }
        } catch (Exception e) {
            result.put("essMsg", e.getMessage());
            LOGGER.warn("exception when login: " + e.getMessage());
        }
        // 输出结果
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
    }

    

 
//    public UserDAO getUserDAO() {
//        return userDAO;
//    }

    /**
     * @param userMetaDAO
     *            the userMetaDAO to set
     */
}
