//package com.bupt.qrj.unifyum.api.controller.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.bupt.qrj.unifyum.util.http.HttpOutUtil;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import sun.misc.BASE64Encoder;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//
//@Controller
//@RequestMapping("/imageBase64.req")
//public class download extends HttpServlet{
//
//    public static ApplicationContext getContext() {
//        // ���Spring�ж����Beanʵ�����������ϼ� new String[]
//        ApplicationContext context = new ClassPathXmlApplicationContext(
//                new String[] { "META-INF/spring/unifyum-dal-dao.xml", "META-INF/spring/unifyum-dal-db.xml" });
//        return context;
//    }
//
//    ApplicationContext context = getContext();
//
//
//	@RequestMapping(method = { RequestMethod.POST}, params = "action=download")
//	public void showImage(HttpServletRequest request, HttpServletResponse response) {
//
//
//		findpicDAOImpl findpicDAO = (findpicDAOImpl) context.getBean("findpicDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//        JSONObject data = new JSONObject();
//		try {
//
//			String mission_id = request.getParameter("mission_id");
//			String pic_class = request.getParameter("pic_class");
//
//
//			picinfoDO picinfoDO = new picinfoDO();
//			picinfoDO.setMission_id(mission_id);
//			picinfoDO.setPic_class(pic_class);
//            System.out.println(mission_id);
//
//			picinfoDO user = findpicDAO.get(picinfoDO);
//			String patha = user.getPic();
//
//            String path = "C:\\apache-tomcat-8.5.23\\webapps\\water" +patha;
//
//
//            System.out.println(path);
//
//            String photo64=GetImageStr(path);
//
//
//            result.put("errMsg", "success");
//			result.put("result","10000");
//            data.put("photo64", photo64);
//
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		data.put("result", result);
//        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//	}
//
//	public static String GetImageStr(String imgFilePath) {// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
//		byte[] data = null;
//
//// ��ȡͼƬ�ֽ�����
//		try {
//			InputStream in = new FileInputStream(imgFilePath);
//			data = new byte[in.available()];
//			in.read(data);
//			in.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//// ���ֽ�����Base64����
//		BASE64Encoder encoder = new BASE64Encoder();
//		return encoder.encode(data);// ����Base64��������ֽ������ַ���
//	}
//	}
//
//
