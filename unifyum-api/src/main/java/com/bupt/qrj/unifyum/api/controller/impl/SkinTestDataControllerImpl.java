/**
 * 
 */
package com.bupt.qrj.unifyum.api.controller.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bupt.qrj.unifyum.api.controller.SkinTestDataController;
import com.bupt.qrj.unifyum.dal.dao.SkinTestDataDAO;
import com.bupt.qrj.unifyum.dal.dataobject.SkinTestDataDO;
import com.bupt.qrj.unifyum.util.http.HttpOutUtil;

/**
 * @author renjun.qrj  2016年4月4日:下午11:40:50
 * com.bupt.qrj.unifyum.api.controller.impl.SkinTestDataControllerImpl
 * unifyum-api
 * 用途: 
 *
 */
@Controller
@RequestMapping("/skintestdata.req")
public class SkinTestDataControllerImpl implements SkinTestDataController {

    private SkinTestDataDAO skinTestDataDAO;

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.SkinTestDataController#update(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @RequestMapping(method = { RequestMethod.POST }, params = "action=update")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        String userName = (String) request.getAttribute("username");
        try {
            String type = String.valueOf(request.getParameter("type"));
            String value = String.valueOf(request.getParameter("value"));
            if (StringUtils.isBlank(type) || StringUtils.isBlank(value)) {
                result.put("errMsg", "输入参数不正确");
            } else {
                SkinTestDataDO testData = new SkinTestDataDO();
                testData.setType(type);
                testData.setValue(value);
                testData.setUserName(userName);
                skinTestDataDAO.insert(testData);
                result.put("success", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("errMsg", e.getMessage());
        }
        //输出结果
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.SkinTestDataController#list(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @RequestMapping(method = { RequestMethod.POST }, params = "action=view")
    public void list(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        String userName = (String) request.getAttribute("username");
        Date lastModified = null;
        try {
            List<SkinTestDataDO> testDatas = skinTestDataDAO.list(userName);
            if (testDatas == null || testDatas.size() == 0) {
                result.put("errMsg", "no data");
            } else {
                //把这个人的结果合并输出
                JSONObject data = new JSONObject();
                result.put("data", data);
                for (SkinTestDataDO testData : testDatas) {
                    data.put(testData.getType(), testData.getValue());
                    if (lastModified == null) {
                        lastModified = testData.getGmtModified();
                    } else if (lastModified.getTime() < testData.getGmtModified().getTime()) {
                        lastModified = testData.getGmtModified();
                    }
                }
                //把最后更新时间记录上
                result.put("lastModifiedTime", lastModified.getTime());
            }
        } catch (Exception e) {
            result.put("errMsg", e.getMessage());
            e.printStackTrace();
        }
        //输出结果
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.SkinTestDataController#jqList(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @RequestMapping(method = { RequestMethod.POST }, params = "action=jqList")
    public void jqList(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        //测量人员个数
        Integer uCount = 0;
        try {
            List<String> userNames = skinTestDataDAO.listUsers();
            if (userNames == null || userNames.size() == 0) {
                result.put("errMsg", "没有此用户的数据");
            } else {
                JSONArray jArray = new JSONArray();
                result.put("datas", jArray);
                for (String userName : userNames) {
                    JSONObject dataJson = new JSONObject();
                    dataJson.put("userName", userName);
                    jArray.add(dataJson);
                    List<SkinTestDataDO> skinDOs = skinTestDataDAO.list(userName);
                    if (skinDOs != null && skinDOs.size() > 0) {
                        for (SkinTestDataDO skinDO : skinDOs) {
                            dataJson.put(skinDO.getType(), skinDO.getValue());
                        }
                    }
                }
                result.put("success", true);

            }
        } catch (Exception e) {
            result.put("errMsg", e.getMessage());
            e.printStackTrace();
        }
        //输出结果
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
    }

    /**
     * @param skinTestDataDAO the skinTestDataDAO to set
     */
    public void setSkinTestDataDAO(SkinTestDataDAO skinTestDataDAO) {
        this.skinTestDataDAO = skinTestDataDAO;
    }

}
