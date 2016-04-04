/**
 * 
 */
package com.bupt.qrj.unifyum.api.controller.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bupt.qrj.unifyum.api.controller.UserTestDataController;
import com.bupt.qrj.unifyum.dal.dao.UserTestDataDAO;
import com.bupt.qrj.unifyum.dal.dataobject.UserTestDataDO;
import com.bupt.qrj.unifyum.util.http.HttpOutUtil;

/**
 * @author renjun.qrj  2016年1月2日:下午9:48:33
 * com.bupt.qrj.unifyum.api.controller.impl.UserTestDataControllerImpl
 * unifyum-api
 * 用途: 
 *
 */
@Controller
@RequestMapping("/usertestdata.req")
public class UserTestDataControllerImpl implements UserTestDataController {

    private UserTestDataDAO           userTestDataDAO;

    /** 日志 **/
    private static final Logger       LOGGER      = LoggerFactory
                                                      .getLogger(UserTestDataControllerImpl.class);

    /**
     * jqgrid 的op 到 sql 语句的 map
     * */
    private final Map<String, String> jqOpMapping = new HashMap<String, String>() {
                                                      {
                                                          //等于
                                                          put("eq", " = ");
                                                          //不等于
                                                          put("ne", " != ");
                                                          //开始于
                                                          put("bw", " like ");
                                                          //不开始于
                                                          put("bn", " not like ");
                                                          //结束于
                                                          put("ew", " like ");
                                                          //不结束于
                                                          put("en", " not like ");
                                                          //包含
                                                          put("cn", " like ");
                                                          //不包含
                                                          put("nc", " not like ");
                                                          //大于等于
                                                          put("ge", " >= ");
                                                          //小于等于
                                                          put("le", " <= ");
                                                      }
                                                  };

    private void processPageParams(HttpServletRequest request, HashMap<String, Object> paraMap,
                                   Integer count) {
        //当前处于第几页
        Long page = Long.valueOf(request.getParameter("page"));
        //每一页中的记录个数
        Long rows = Long.valueOf(request.getParameter("rows"));
        paraMap.put("page", page);
        paraMap.put("limit", (page - 1) * rows);
        paraMap.put("offset", rows);
        //计算
        //排序的列
        String sortKey = request.getParameter("sidx");
        paraMap.put("sortKey", sortKey);
        //排序的顺序
        String order = request.getParameter("sord");
        paraMap.put("order", order);
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.UserTestDataController#updateTestData(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @RequestMapping(method = { RequestMethod.POST }, params = "action=update")
    public void updateTestData(HttpServletRequest request, HttpServletResponse response)
                                                                                        throws UnsupportedEncodingException {
        JSONObject result = new JSONObject();
        request.setCharacterEncoding("UTF-8");
        result.put("success", false);
        String userName = (String) request.getAttribute("username");
        try {
            Map<String, String[]> paramsMap = request.getParameterMap();
            JSONObject testDataMap = new JSONObject();
            Iterator<String> paramKeyIter = paramsMap.keySet().iterator();
            //处理param
            while (paramKeyIter.hasNext()) {
                String key = paramKeyIter.next();
                if (!key.equals("authToken") && !key.equals("action")) {
                    String value = String.valueOf(request.getParameter(key));
                    testDataMap.put(key, value);
                }
            }
            if (testDataMap.isEmpty()) {
                result.put("errMsg", "未输入任何测试数据");
            } else {
                UserTestDataDO testDataDO = new UserTestDataDO();
                testDataDO.setUserName(userName);
                JSONObject oldTestDataMap = new JSONObject();

                oldTestDataMap.putAll(testDataMap);
                Iterator<Map.Entry<String, Object>> testDIter = oldTestDataMap.entrySet()
                    .iterator();
                //处理testData,为空的值就要去掉~!!
                while (testDIter.hasNext()) {
                    Map.Entry<String, Object> entry = testDIter.next();
                    String value = String.valueOf(entry.getValue());

                    if (value == null || value.isEmpty() || value.equals("\"\"")) {
                        testDIter.remove();
                    }
                }

                if (oldTestDataMap.isEmpty()) {
                    testDataDO.setTestData("".getBytes());
                } else {
                    testDataDO.setTestData(oldTestDataMap.toJSONString().getBytes("utf8"));
                }
                userTestDataDAO.insert(testDataDO);
                result.put("success", true);
            }
        } catch (Exception e) {
            LOGGER.warn("exp happened: ", e);
            result.put("errMsg", e);
        }
        // 输出结果        
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.UserTestDataController#getTestData(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @RequestMapping(method = { RequestMethod.POST }, params = "action=get")
    public void getTestData(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        String userName = (String) request.getAttribute("username");
        try {
            UserTestDataDO testDO = userTestDataDAO.get(userName);
            if (testDO == null || testDO.getTestData().length == 0) {
                result.put("errMsg", "目前没有任何测试数据");
            } else {
                String testDataStr = new String(testDO.getTestData(), StandardCharsets.UTF_8);
                //测试一下能不能解析
                JSONObject testDataJstr = JSONObject.parseObject(testDataStr);
                result.put("data", testDataJstr);
                result.put("success", true);
            }

        } catch (Exception e) {
            result.put("errMsg", e);
        }
        // 输出结果       
        HttpOutUtil.outData(response, result.toString());
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.UserTestDataController#jqList(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @RequestMapping(method = { RequestMethod.POST }, params = "action=jqList")
    public void jqList(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> paraMap = new HashMap<String, Object>();
        String userName = (String) request.getAttribute("username");
        JSONObject result = new JSONObject();
        result.put("success", false);
        List<UserTestDataDO> testDOList = null;
        try {
            paraMap.put("userName", userName);
            testDOList = userTestDataDAO.list(paraMap);
            if (testDOList == null || testDOList.size() == 0) {
                result.put("errMsg", "没有此用户的数据");
            } else {
                JSONArray jArray = new JSONArray();
                result.put("datas", jArray);
                for (UserTestDataDO testDO : testDOList) {
                    String testDataStr = new String(testDO.getTestData(), StandardCharsets.UTF_8);
                    //测试一下能不能解析
                    JSONObject testDataJObj = JSONObject.parseObject(testDataStr);
                    jArray.add(testDataJObj);
                }
                result.put("result", true);
            }

        } catch (Exception e) {
            result.put("errMsg", e.getMessage());
        }
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));

    }

    /**
     * @return the userTestDataDAO
     */
    public UserTestDataDAO getUserTestDataDAO() {
        return userTestDataDAO;
    }

    /**
     * @param userTestDataDAO the userTestDataDAO to set
     */
    public void setUserTestDataDAO(UserTestDataDAO userTestDataDAO) {
        this.userTestDataDAO = userTestDataDAO;
    }

}
