/**
 * 
 */
package com.bupt.qrj.unifyum.api.controller.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

import com.alibaba.fastjson.JSONArray;
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

    private UserImageDAO              userImageDAO;

    private static JSONObject         solutionData = new JSONObject();

    private static JSONObject         adviceData   = new JSONObject();

    /**
     * jqgrid 的op 到 sql 语句的 map
     * */
    private final Map<String, String> jqOpMapping  = new HashMap<String, String>() {
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

    {
        solutionData.put("productSuggest", "建议您使用A产品");
        solutionData.put("foodSuggest", "建议您多吃蔬菜");
        solutionData.put("liveSuggest", "建议您按时吃饭，好好休息");

        adviceData.put("moistenAdvice", "温润的肌肤，表现出了您良好的生活习惯");
        adviceData.put("satinAdvice", "皮肤光泽良好");
        adviceData.put("bloodAdvice", "气血旺盛，精力充沛");
        adviceData.put("colorAdvice", "色泽红润，可以继续保持良好作息");
        adviceData.put("textureAdvice", "皮肤具有良好质感");
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.UserImageController#upload(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */

    private JSONObject processImageStr(String userName, String imageStr) {
        JSONObject picData = new JSONObject();
        //生成随机数
        Random rand = new Random();
        int moisten = rand.nextInt(10) + 1;
        int blood = rand.nextInt(10) + 1;
        int color = rand.nextInt(10) + 1;
        int texture = rand.nextInt(10) + 1;
        int satin = rand.nextInt(10) + 1;
        //保存图像
        UserImageDO image = new UserImageDO();
        image.setUserName(userName);
        image.setImage(imageStr);
        image.setMoisten(String.valueOf(moisten));
        image.setBlood(String.valueOf(blood));
        image.setTexture(String.valueOf(texture));
        image.setSatin(String.valueOf(satin));
        image.setColor(String.valueOf(color));
        userImageDAO.addImage(image);
        //返回结果json
        picData.put("moisten", String.valueOf(moisten));
        picData.put("blood", String.valueOf(blood));
        picData.put("color", String.valueOf(color));
        picData.put("texture", String.valueOf(texture));
        picData.put("satin", String.valueOf(satin));
        return picData;
    }

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
                JSONObject picData = processImageStr(userName, imageStr);
                result.put("adviceData", adviceData);
                result.put("solutionData", solutionData);
                result.put("picData", picData);
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

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.UserImageController#showImage(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("restriction")
    @RequestMapping(method = { RequestMethod.GET }, params = "action=showImg")
    public void showImage(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long imageId = Long.parseLong(request.getParameter("id"));
            if (imageId != null) {
                UserImageDO image = userImageDAO.getImage(imageId);
                if (image != null) {
                    byte[] imageData = null;
                    BASE64Decoder decoder = new BASE64Decoder();
                    imageData = decoder.decodeBuffer(image.getImage());
                    if (imageData != null) {
                        response.setContentType("image/jpg");
                        ServletOutputStream outputStream = response.getOutputStream();
                        outputStream.write(imageData);
                        outputStream.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String imageStr = "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";
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] imageData = decoder.decodeBuffer(imageStr);
        FileOutputStream outputStream = new FileOutputStream("d:/test.jpg");
        outputStream.write(imageData);
        outputStream.close();
        System.out.println("over");

    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.UserImageController#updateImage(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.web.multipart.MultipartFile)
     */
    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, params = "action=imageUpload")
    public void updateImage(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam MultipartFile file) {
        JSONObject result = new JSONObject();
        JSONArray filesArr = new JSONArray();
        result.put("files", filesArr);
        JSONObject testFile = new JSONObject();
        filesArr.add(testFile);
        testFile.put("name", file.getName());

        try {
            byte[] imageBytes = file.getBytes();
            Encoder encoder = Base64.getEncoder();
            String imageStr = encoder.encodeToString(imageBytes);
            //保存文件
            processImageStr("Administrator", imageStr);
            testFile.put("size", file.getSize());
            testFile.put("url", "viewImage");
            testFile.put("thumbnailUrl", "");
            testFile.put("deleteUrl", "");
            testFile.put("deleteType", "DELETE");
        } catch (Exception exp) {
            testFile.put("error", exp.getMessage());
        }
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
    }

    /* (non-Javadoc)
     * @see com.bupt.qrj.unifyum.api.controller.UserImageController#jqList(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, params = "action=jqList")
    public void jqList(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> paraMap = new HashMap<String, Object>();

        processFilterParams(request, paraMap);
        JSONObject result = null;
        Integer count = 0;
        List<UserImageDO> imageDOList = null;
        try {
            count = userImageDAO.count(paraMap);
            if (count > 0) {
                //更新页码信息
                processPageParams(request, paraMap, count);
                imageDOList = userImageDAO.queryImageByProps(paraMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = buildJqResult(imageDOList, paraMap, count);
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
    }

    /**
     * processFilterParams 2016年3月6日
     * 2016年3月6日.上午9:58:42
     * return: void 
     */
    private void processFilterParams(HttpServletRequest request, HashMap<String, Object> paraMap) {
        Map reqMap = request.getParameterMap();
        String searchSwitch = request.getParameter("_search");
        StringBuffer filterBuffer = new StringBuffer();
        if (searchSwitch.equalsIgnoreCase("true")) {

            String filters = request.getParameter("filters");
            JSONObject filterJSON = JSONObject.parseObject(filters);
            String groupOp = filterJSON.getString("groupOp");
            JSONArray rules = filterJSON.getJSONArray("rules");
            int iterCount = 1;
            for (Object rule : rules) {
                JSONObject ruleJSON = (JSONObject) rule;
                String keyName = ruleJSON.getString("field");
                String op = ruleJSON.getString("op");
                String sqlOp = jqOpMapping.get(op) == null ? " = " : jqOpMapping.get(op);
                String data = ruleJSON.getString("data");
                //记录个数，拼接groupOp
                filterBuffer.append(" ");
                filterBuffer.append(keyName);
                filterBuffer.append(sqlOp);
                filterBuffer.append(prepareFilterData(data, op));
                filterBuffer.append(" ");
                if (iterCount != rules.size()) {
                    filterBuffer.append(groupOp);
                }
                iterCount++;
            }
            //输出结果
            String filterStr = filterBuffer.toString();
            if (filterStr != null && !filterStr.isEmpty()) {
                paraMap.put("filterStr", filterBuffer.toString());
            }
        }
    }

    private String prepareFilterData(String data, String op) {
        if (op.equals("bw") || op.equals("bn")) {
            //开始于
            data = data + "%";
        } else if (op.equals("ew") || op.equals("en")) {
            //结束于
            data = "%" + data;
        } else if (op.equals("cn") || op.equals("nc")) {
            //包含于
            data = "%" + data + "%";
        }
        //字符串类型的处理
        data = "'" + data + "'";

        return data;
    }

    /**
     * processRequestParams 2016年3月6日
     * 2016年3月6日.上午9:44:45
     * return: void 
     * @param count 
     */
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

    /**
     * buildJqResult 2016年3月6日
     * 2016年3月6日.上午12:25:41
     * return: JSONObject 
     * @param count 
     * @param paraMap 
     */
    private JSONObject buildJqResult(List<UserImageDO> imageDOList,
                                     HashMap<String, Object> paraMap, Integer count) {
        JSONObject result = new JSONObject();
        JSONArray rows = new JSONArray();
        result.put("records", count);
        result.put("rows", rows);

        if (imageDOList == null || imageDOList.isEmpty()) {
            result.put("total", 0);
            result.put("page", 0);
        } else {
            Integer offset = ((Long) paraMap.get("offset")).intValue();
            int total = count / offset;
            if (count % offset != 0) {
                total++;
            }

            result.put("total", total);
            if (paraMap.containsKey("page")) {
                result.put("page", paraMap.get("page"));
            } else {
                result.put("page", 1);
            }
            //开始转换
            for (UserImageDO imageDO : imageDOList) {
                JSONObject data = new JSONObject();
                data.put("user_name", imageDO.getUserName());
                data.put("blood", imageDO.getBlood());
                data.put("moisten", imageDO.getMoisten());
                data.put("color", imageDO.getColor());
                data.put("texture", imageDO.getTexture());
                data.put("satin", imageDO.getSatin());
                data.put("record_time", imageDO.getRecordTime());
                data.put("id", imageDO.getId());
                rows.add(data);
            }
        }
        return result;
    }

    /**
     * @param userImageDAO the userImageDAO to set
     */
    public void setUserImageDAO(UserImageDAO userImageDAO) {
        this.userImageDAO = userImageDAO;
    }
}
