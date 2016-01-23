/**
 * 
 */
package com.bupt.qrj.unifyum.api.controller.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.bupt.qrj.unifyum.api.controller.UserImageController;
import com.bupt.qrj.unifyum.dal.dao.UserImageDAO;
import com.bupt.qrj.unifyum.dal.dataobject.UserImageDO;
import com.bupt.qrj.unifyum.util.http.HttpOutUtil;

/**
 * @author renjun.qrj  2015年12月30日:上午7:19:36
 * com.bupt.qrj.unifyum.api.controller.impl.UserImageControllerImpl
 * unifyum-api
 * 用途: 
 *
 */
@Controller
@RequestMapping("/imagemanagement.req")
public class UserImageControllerImpl implements UserImageController {

    private UserImageDAO userImageDAO;

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.UserImageController#upload(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @RequestMapping(method = { RequestMethod.POST }, params = "action=upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        String userName = (String) request.getAttribute("username");
        try {
            String imageStr = request.getParameter("image");
            if (imageStr == null || imageStr.isEmpty()) {
                result.put("errMsg", "输入参数错误");
            } else {
                UserImageDO image = new UserImageDO();
                image.setUserName(userName);
                image.setImage(imageStr);
                userImageDAO.addImage(image);
                result.put("success", true);

            }
        } catch (Exception e) {
            result.put("errMsg", e);
        }
        //输出结果
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.UserImageController#list(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @RequestMapping(method = { RequestMethod.POST }, params = "action=list")
    public void list(HttpServletRequest request, HttpServletResponse response) {
        String userName = (String) request.getAttribute("username");
        JSONObject result = new JSONObject();
        result.put("success", false);
        try {
            List<UserImageDO> imgDOList = userImageDAO.queryImageByUser(userName);
            if (imgDOList == null || imgDOList.isEmpty()) {
                result.put("errMsg", "no data");
            } else {
                result.put("success", true);
                result.put("images", imgDOList);
            }
        } catch (Exception e) {
            result.put("errMsg", e);
        }
        //输出结果
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.UserImageController#view(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @RequestMapping(method = { RequestMethod.POST }, params = "action=view")
    public void view(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        try {
            Long imageId = Long.parseLong(request.getParameter("imgId"));
            if (imageId == null) {
                result.put("errMsg", "参数输入错误");
            } else {
                UserImageDO image = userImageDAO.getImage(imageId);
                if (image == null) {
                    result.put("errMsg", "没有查找到图像");
                } else {
                    result.put("success", true);
                    result.put("image", image);
                }
            }
        } catch (Exception e) {
            result.put("errMsg", e);
        }
        //输出结果
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
    }

    /**
     * @param userImageDAO the userImageDAO to set
     */
    public void setUserImageDAO(UserImageDAO userImageDAO) {
        this.userImageDAO = userImageDAO;
    }

}
