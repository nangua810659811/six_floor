///**
// *
// */
//package com.bupt.qrj.unifyum.api.controller.impl;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.bupt.qrj.unifyum.api.controller.lockController;
//import com.bupt.qrj.unifyum.dal.dao.*;
//import com.bupt.qrj.unifyum.dal.dao.impl.*;
//import com.bupt.qrj.unifyum.dal.dataobject.*;
//import com.bupt.qrj.unifyum.util.CollectionTools;
//import com.bupt.qrj.unifyum.util.SendMessage;
//import com.bupt.qrj.unifyum.util.http.HttpOutUtil;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/lock.req")
//public class lockControllerImpl implements lockController {
//
//	public static ApplicationContext getContext() {
//		// 获得Spring中定义的Bean实例，两个以上加 new String[]
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				new String[] { "META-INF/spring/unifyum-dal-dao.xml", "META-INF/spring/unifyum-dal-db.xml" });
//		return context;
//	}
//
//	ApplicationContext context = getContext();
//
//
//	/*
//	*1.1 工人登录
//	*
//	* */
//	@RequestMapping(method = { RequestMethod.POST }, params = "action=login")
//	public void login(HttpServletRequest request, HttpServletResponse response) {
//
//		//ApplicationContext context = getContext();
//		workerloginDAOImpl workerloginDAO = (workerloginDAOImpl) context.getBean("workerloginDAO");
//
//		JSONObject result = new JSONObject();
//		result.put("result", 10001);
//
//
//		try {
//
//			//String body = request.getParameter("login");
//			//System.out.println("login=" + body);
//
//			/*
//			 * Map map = JSON.parseObject(body, Map.class);
//			 * System.out.println(map.get("mobileNo")); String userName =
//			 * (String) map.get("mobileNo"); String password = (String)
//			 * map.get("password");
//			 */
//
//			//从dbinfo.properties文件中读取配置信息
//
//			String phone = request.getParameter("phone");
//			String password = request.getParameter("password");
//            String check_code = request.getParameter("check_code");
//            //System.out.println(phone);
//            //System.out.println(password);
//            // 先是参数检查
//			if (phone == null || phone.isEmpty() || password == null || password.isEmpty()) {
//				result.put("errMsg", "输入参数有误");
//				result.put("result","10004");
//			} else {
//			    if(check_code == null || check_code.isEmpty()){
//                    // second time and so on
//                    workerloginDO workerloginDO = new workerloginDO();
//                    workerloginDO.setPhone(phone);
//
//                    workerloginDO user = workerloginDAO.get(workerloginDO);
//                /*System.out.println(user);*/
//
//                    if (user == null) {
//                        result.put("errMsg", "用户名输入错误");
//                        result.put("result","10002");
//                    } else {
//                        // 检查用户密码是否正确
//                        if (password.equals(user.getPassword())) {
//                            result.put("result","10000");
//                            result.put("errMsg", "登录成功");
//
//						/*
//						 * result.setAuthToken(authToken);
//						 * result.setLoginTime(loginTime);
//						 */
//                        } else {
//                            result.put("errMsg", "密码错误");
//                            result.put("result","10003");
//                        }
//                    }
//                }else{
//			        //first check code
//                    checkcodeDO checkcodeDO = new checkcodeDO();
//                    checkcodeDO.setPhone(phone);
//                    checkcodeDO codenumber = workerloginDAO.getfir(checkcodeDO);
//                    if(codenumber == null){
//                        result.put("errMsg", "请重新发送验证码");
//                        result.put("result","10004");
//                    }else{
//                        if(check_code.equals(codenumber.getCheck_code())){
//                            System.out.println("check-access"+phone);
//
//                            workerloginDO workerloginDO = new workerloginDO();
//                            workerloginDO.setPhone(phone);
//                            workerloginDO user = workerloginDAO.get(workerloginDO);
//
//                            if (user == null) {
//                                result.put("errMsg", "用户名输入错误");
//                                result.put("result","10002");
//                            } else {
//                                // 检查用户密码是否正确
//                                if (password.equals(user.getPassword())) {
//                                    result.put("errMsg", "登录成功");
//                                    result.put("result", "10000");
//                                } else {
//                                    result.put("result", "10003");
//                                    result.put("errMsg", "密码错误");
//                                }
//                            }
//                        }
//                    }
//
//				}
//			}
//		} catch (Exception e) {
//			result.put("essMsg", e.getMessage());
//
//		}
//		// 输出结果
//		HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//	}
//
//    /*
//    *1.2 工人修改
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=signin")
//    public void signin(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        workersigninDAOImpl workersigninDAO = (workersigninDAOImpl) context.getBean("workersigninDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//
//        try {
//
//            String phone = request.getParameter("phone");
//            String name = request.getParameter("name");
//			String sex = request.getParameter("sex");
//			String birthday = request.getParameter("birthday");
//			String address = request.getParameter("address");
//			String idcard = request.getParameter("idcard");
//
//
//
//            if (phone == null || phone.isEmpty() ||sex == null || sex.isEmpty() ||
//                    birthday == null || birthday.isEmpty() ||address == null || address.isEmpty() ||name == null || name.isEmpty() ||
//                    idcard == null || idcard.isEmpty() ) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//                // 获取用户的数据
//                workersigninDO workersigninDO = new workersigninDO();
//                workersigninDO.setPhone(phone);
//                workersigninDO.setName(name);
//                workersigninDO.setSex(sex);
//                workersigninDO.setBirthday(birthday);
//                workersigninDO.setAddress(address);
//                workersigninDO.setIdcard(idcard);
//
//
//                workersigninDAO.update(workersigninDO);
//                result.put("errMsg", "保存成功！");
//                result.put("result","10000");
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//
//
//	/*
//    *1.3 1.3	查询未接收任务单
//    *
//    * */
//	@RequestMapping(method = { RequestMethod.POST }, params = "action=seekwjs")
//	public void seekwjs(HttpServletRequest request, HttpServletResponse response) {
//
//		//ApplicationContext context = getContext();
//        seekwjsDAOImpl seekwjsDAO = (seekwjsDAOImpl) context.getBean("seekwjsDAO");
//
//		JSONObject result = new JSONObject();
//		result.put("result", 10001);
//
//        JSONObject data = new JSONObject();
//		try {
//
//			String phone = request.getParameter("phone");
//
//
//
//			if (phone == null || phone.isEmpty() ) {
//				result.put("errMsg", "失败");
//				result.put("result","10001");
//			} else {
//				// 获取用户的数据
//
//                List<seekwjsDO> seekwjs = seekwjsDAO.list(phone);
//                if (seekwjs.isEmpty()) {
//                    result.put("result", 10002);
//                    result.put("errMsg", "没有数据");
//                    System.out.println("数据库为空");
//                } else {
//                    result.put("result", 10000);
//                    result.put("errMsg", "成功");
//                    System.out.println("seek-wjs-ok");
//                    ArrayList<JSONObject> seekwjsdata = new ArrayList<JSONObject>();
//
//                    for (seekwjsDO seekwjsDO : seekwjs){
//                        JSONObject data1 = new JSONObject();
//                        data1.put("missionID", seekwjsDO.getMission_id());
//                        data1.put("addressID", seekwjsDO.getHouse_id());
//                        data1.put("address", seekwjsDO.getHouse_address());
//
//
//                        seekwjsdata.add(data1);
//                    }
//                    data.put("data", seekwjsdata);
//                }
//
//			}
//		} catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//		}
//            data.put("result", result);
//		// 输出结果
//		HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//	}
//	/*
//    *1.4	1.4	查询已接收任务单
//    *
//    * */
//	@RequestMapping(method = { RequestMethod.POST }, params = "action=seekyjs")
//	public void seekyjs(HttpServletRequest request, HttpServletResponse response) {
//
//		//ApplicationContext context = getContext();
//		seekyjsDAOImpl seekyjsDAO = (seekyjsDAOImpl) context.getBean("seekyjsDAO");
//
//		JSONObject result = new JSONObject();
//		result.put("result", 10001);
//        int i=0;
//
//		JSONObject data = new JSONObject();
//		try {
//
//			String worker_phone = request.getParameter("phone");
//			String type = request.getParameter("status");
//			String timea = request.getParameter("time1");
//			String timeb = request.getParameter("time2");
//			String timemin = timea + " 00:00:00";
//			String timemax = timeb + " 00:00:00";
//
//
//
//
//			if (worker_phone == null || worker_phone.isEmpty() ||type == null || type.isEmpty() ) {
//				result.put("errMsg", "失败");
//				result.put("result","10001");
//			} else {
//			    if(type.equals("1")){
//                    // 拒单
//
//                    List<seekyjsDO> seekyjs = seekyjsDAO.list(worker_phone,timemin,timemax);
//                    if (seekyjs.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("seek-yjs-refuse-ok");
//                        ArrayList<JSONObject> seekyjsdata = new ArrayList<JSONObject>();
//
//                        for (seekyjsDO seekyjsDO : seekyjs){
//                            JSONObject data1 = new JSONObject();
//                            data1.put("missionID", seekyjsDO.getMission_id());
//                            data1.put("addressID", seekyjsDO.getHouse_id());
//                            data1.put("address", seekyjsDO.getHouse_address());
//                            data1.put("refuse_time", seekyjsDO.getRefuse_time());
//                            i++;
//
//                            seekyjsdata.add(data1);
//                        }
//                        data.put("data", seekyjsdata);
//                    }
//                }
//                if(type.equals("2")){
//                    // 接收
//                    List<seekyjsDO> seekyjs = seekyjsDAO.list1(worker_phone);
//                    if (seekyjs.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("seek-yjs-lock-1-ok");
//                        ArrayList<JSONObject> seekyjsdata = new ArrayList<JSONObject>();
//
//                        for (seekyjsDO seekyjsDO : seekyjs){
//                            JSONObject data1 = new JSONObject();
//                            data1.put("missionID", seekyjsDO.getMission_id());
//                            data1.put("addressID", seekyjsDO.getHouse_id());
//                            data1.put("address", seekyjsDO.getHouse_address());
//                            data1.put("accept_time", seekyjsDO.getAccept_time());
//                            i++;
//                            seekyjsdata.add(data1);
//                        }
//                        data.put("data", seekyjsdata);
//                    }
//                }
//                if(type.equals("3")){
//                    // 取锁
//                    List<seekyjsDO> seekyjs = seekyjsDAO.list2(worker_phone);
//                    if (seekyjs.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("seek-yjs-lock-2-ok");
//                        ArrayList<JSONObject> seekyjsdata = new ArrayList<JSONObject>();
//
//                        for (seekyjsDO seekyjsDO : seekyjs){
//                            JSONObject data1 = new JSONObject();
//                            data1.put("missionID", seekyjsDO.getMission_id());
//                            data1.put("addressID", seekyjsDO.getHouse_id());
//                            data1.put("address", seekyjsDO.getHouse_address());
//                            data1.put("accept_time", seekyjsDO.getAccept_time());
//                            data1.put("install_time", seekyjsDO.getStart_time());
//                            i++;
//                            seekyjsdata.add(data1);
//                        }
//                        data.put("data", seekyjsdata);
//                    }
//                }
//                if(type.equals("4")){
//                    // 完成
//                    List<seekyjsDO> seekyjs = seekyjsDAO.list3(worker_phone,timemin,timemax);
//                    if (seekyjs.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("seek-yjs-finish-ok");
//                        ArrayList<JSONObject> seekyjsdata = new ArrayList<JSONObject>();
//
//                        for (seekyjsDO seekyjsDO : seekyjs){
//                            JSONObject data1 = new JSONObject();
//                            data1.put("missionID", seekyjsDO.getMission_id());
//                            data1.put("addressID", seekyjsDO.getHouse_id());
//                            data1.put("address", seekyjsDO.getHouse_address());
//                            data1.put("accept_time", seekyjsDO.getAccept_time());
//                            data1.put("finish_time", seekyjsDO.getFinish_time());
//                            i++;
//                            seekyjsdata.add(data1);
//                        }
//                        data.put("data", seekyjsdata);
//                    }
//                }
//                if(type.equals("5")){
//                    // 整改
//                    List<seekyjsDO> seekyjs = seekyjsDAO.list4(worker_phone);
//                    if (seekyjs.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("seek-yjs-wrong-ok");
//                        ArrayList<JSONObject> seekyjsdata = new ArrayList<JSONObject>();
//
//                        for (seekyjsDO seekyjsDO : seekyjs){
//                            JSONObject data1 = new JSONObject();
//                            data1.put("missionID", seekyjsDO.getMission_id());
//                            data1.put("addressID", seekyjsDO.getHouse_id());
//                            data1.put("address", seekyjsDO.getHouse_address());
//                            data1.put("accept_time", seekyjsDO.getAccept_time());
//                            data1.put("reform_time", seekyjsDO.getReform_time());
//                            i++;
//                            seekyjsdata.add(data1);
//                        }
//                        data.put("data", seekyjsdata);
//                    }
//                }
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        data.put("number", i);
//		data.put("result", result);
//		// 输出结果
//		HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//	}
//    /*
//    *1.5	上传任务单处理
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=refusemission")
//    public void refusemission(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        refusemissionDAOImpl refusemissionDAO = (refusemissionDAOImpl) context.getBean("refusemissionDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//
//        try {
//
//            String phone = request.getParameter("phone");
//            String mission_id = request.getParameter("missionID");
//            String type = request.getParameter("status");
//
//
//
//            if (phone == null || phone.isEmpty() ||mission_id == null || mission_id.isEmpty() ||type == null || type.isEmpty()  ) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//                if(type.equals("1")){// update mission condition 1
//                    refusemissionDO refusemissionDO = new refusemissionDO();
//                    refusemissionDO.setMission_id(mission_id);
//
//
//                    refusemissionDAO.update(refusemissionDO);
//                    result.put("errMsg", "保存成功！");
//                    result.put("result","10000");
//                    System.out.println("拒单");
//                }
//                if(type.equals("2")){// update mission condition 1
//                    refusemissionDO refusemissionDO = new refusemissionDO();
//                    refusemissionDO.setMission_id(mission_id);
//
//
//                    refusemissionDAO.update1(refusemissionDO);
//                    result.put("errMsg", "保存成功！");
//                    result.put("result","10000");
//                    System.out.println("接收未取锁");
//                }
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//
//
//    /*
//    *1.6 位置上传
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=location")
//    public void location(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        locationDAOImpl locationDAO = (locationDAOImpl) context.getBean("locationDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//
//        try {
//
//            String worker_phone = request.getParameter("phone");
//            String longitude = request.getParameter("longitude");
//            String latitude = request.getParameter("latitude");
//            String time = request.getParameter("time");
//
//
//            if (worker_phone == null || worker_phone.isEmpty() ||longitude == null || longitude.isEmpty() ||latitude == null || latitude.isEmpty()
//                    ||time == null || time.isEmpty()) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//
//                // get worker name
//                locationnameDO locationnameDO = new locationnameDO();
//                locationnameDO.setPhone(worker_phone);
//                locationnameDO usr = locationDAO.getname(locationnameDO);
//                String worker_name = usr.getName();
//                System.out.println(worker_name);
//                // find isn't it have?
//
//                List<locationDO> locationfind = locationDAO.find(worker_phone);
//                System.out.println(locationfind);
//                if ( locationfind == null ||locationfind.isEmpty()) {
//                    //isn't have,then? insert
//                    locationDO locationDO = new locationDO();
//                    locationDO.setWorker_phone(worker_phone);
//                    locationDO.setLatitude(latitude);
//                    locationDO.setLongitude(longitude);
//                    locationDO.setWorker_name(worker_name);
//                    locationDO.setTime(time);
//                    locationDAO.insert(locationDO);
//                    System.out.println("newest-location-insert-ok");
//                    result.put("errMsg", "保存成功！");
//                    result.put("result","10000");
//                }else{
//                    //have,update
//                    locationDO locationDO = new locationDO();
//                    locationDO.setWorker_phone(worker_phone);
//                    locationDO.setLatitude(latitude);
//                    locationDO.setLongitude(longitude);
//                    locationDO.setWorker_name(worker_name);
//                    locationDO.setTime(time);
//                    locationDAO.update(locationDO);
//                    System.out.println("newest-location-update-ok");
//                    result.put("errMsg", "保存成功！");
//                    result.put("result","10000");
//                }
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//    /*
//      *1.7 任务反馈
//      *
//      * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=finish")
//    public void finish(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        finishDAOImpl finishDAO = (finishDAOImpl) context.getBean("finishDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//        try {
//
//            String worker_phone = request.getParameter("phone");
//            String mission_id = request.getParameter("missionID");
//            String longitude = request.getParameter("longitude");
//            String latitude = request.getParameter("latitude");
//            String time = request.getParameter("time");
//            String note = request.getParameter("note");
//
//            if (worker_phone == null || worker_phone.isEmpty() ||longitude == null || longitude.isEmpty() ||latitude == null || latitude.isEmpty()
//                    ||time == null || time.isEmpty()||mission_id == null || mission_id.isEmpty()) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//
//                // insert his
//                finishDO finishDO = new finishDO();
//                finishDO.setWorker_phone(worker_phone);
//                finishDO.setMission_id(mission_id);
//                finishDO.setLongitude(longitude);
//                finishDO.setLatitude(latitude);
//                finishDO.setTime(time);
//                finishDO.setNote(note);
//                finishDAO.insert(finishDO);
//                System.out.println("finish-insert-ok");
//                //update mission
//                String finish_time = time;
//                finishmisDO finishmisDO = new finishmisDO();
//
//                finishmisDO.setFinish_time(finish_time);
//                finishmisDO.setMission_id(mission_id);
//                finishDAO.update(finishmisDO);
//                System.out.println("mission-finish-update-ok");
//                result.put("errMsg", "保存成功！");
//                result.put("result","10000");
//
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//    /*
//      *1.10 拒单
//      *
//      * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=refuseinfo")
//    public void refuseinfo(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        refuseinfoDAOImpl refuseinfoDAO = (refuseinfoDAOImpl) context.getBean("refuseinfoDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//        try {
//
//            String worker_phone = request.getParameter("phone");
//            String number = request.getParameter("number");
//            String worker_name = request.getParameter("worker");
//            String refuse_reason = request.getParameter("reason");
//            String refuse_time = request.getParameter("refuse_time");
//
//
//            if (worker_phone == null || worker_phone.isEmpty() ||worker_name == null || worker_name.isEmpty()
//                    ||refuse_time == null || refuse_time.isEmpty()||number == null || number.isEmpty()) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//                System.out.println(number);
//                System.out.println(refuse_time);
//                //update mission
//                refuseinfoDO refuseinfoDO = new refuseinfoDO();
//                refuseinfoDO.setRefuse_time(refuse_time);
//                refuseinfoDO.setMission_id(number);
//                refuseinfoDO.setRefuse_reason(refuse_reason);
//                refuseinfoDAO.update(refuseinfoDO);
//                System.out.println("mission-refuse-update-ok");
//                result.put("errMsg", "保存成功！");
//                result.put("result","10000");
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//    /*
//    *1.11 任务下载
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=missiondownload")
//    public void missiondownload(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        missiondownloadDAOImpl missiondownloadDAO = (missiondownloadDAOImpl) context.getBean("missiondownloadDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//        JSONObject data = new JSONObject();
//        try {
//
//            String worker_phone = request.getParameter("phone");
//            String mission_id = request.getParameter("missionID");
//
//
//            if (worker_phone == null || worker_phone.isEmpty()||mission_id == null || mission_id.isEmpty() ) {
//                result.put("errMsg", "失败");
//                result.put("result","10001");
//            } else {
//                // 获取用户的数据
//
//                List<missiondownloadDO> missiondownload = missiondownloadDAO.list(mission_id);
//                if (missiondownload.isEmpty()) {
//                    result.put("result", 10002);
//                    result.put("errMsg", "没有数据");
//                    System.out.println("数据库为空");
//                } else {
//                    result.put("result", 10000);
//                    result.put("errMsg", "成功");
//                    System.out.println("mission-download-ok");
//                    ArrayList<JSONObject> missiondata = new ArrayList<JSONObject>();
//
//                    for (missiondownloadDO missiondownloadDO : missiondownload){
//                        JSONObject data1 = new JSONObject();
//                        data1.put("missionID", missiondownloadDO.getMission_id());
//                        data1.put("qrcode",missiondownloadDO.getMission_id()+"!"+missiondownloadDO.getRandom_code()+"!"+missiondownloadDO.getSet_start_time());
//                        data1.put("authcode", missiondownloadDO.getRandom_code());
//                        data1.put("men_suo_hao", missiondownloadDO.getLock_code());
//                        data1.put("team", missiondownloadDO.getTeam());
//                        data1.put("worker", missiondownloadDO.getWorker_name());
//                        data1.put("phone", missiondownloadDO.getWorker_phone());
//                        data1.put("task_nature", missiondownloadDO.getMission_type());
//                        data1.put("task_level", missiondownloadDO.getMission_level());
//                        data1.put("door_type", missiondownloadDO.getDoor_type());
//                        data1.put("lock_type", missiondownloadDO.getLock_code());
//                        data1.put("expected_start_time", missiondownloadDO.getSet_start_time());
//                        data1.put("expected_finish_time", missiondownloadDO.getSet_finish_time());
//                        data1.put("remark", missiondownloadDO.getMission_addition());
//                        data1.put("addressID", missiondownloadDO.getHouse_id());
//                        data1.put("address", missiondownloadDO.getHouse_address());
//                        data1.put("reform_opinion", missiondownloadDO.getReform_reason());
//
//                        missiondata.add(data1);
//                    }
//                    data.put("data", missiondata);
//                }
//
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        data.put("result", result);
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//    }
//    /*
//       *2.1上传待安装房源信息
//       *
//       * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=houseinfo")
//    public void houseinfo(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        houseinfoDAOImpl houseinfoDAO = (houseinfoDAOImpl) context.getBean("houseinfoDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//        try {
//
//            String worker_phone = request.getParameter("phone");
//            String house_address = request.getParameter("address");
//            String house_owner = request.getParameter("landlord");
//            String owner_phone = request.getParameter("cellphone");
//
//
//            if (worker_phone == null || worker_phone.isEmpty() ||house_owner == null || house_owner.isEmpty() ||house_address == null || house_address.isEmpty()
//                    ||owner_phone == null || owner_phone.isEmpty()) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//
//                // insert his
//                houseinfoDO houseinfoDO = new houseinfoDO();
//                houseinfoDO.setHouse_owner(house_owner);
//                houseinfoDO.setHouse_address(house_address);
//                houseinfoDO.setOwner_phone(owner_phone);
//                houseinfoDO.setSet_condition("1");
//                houseinfoDAO.insert(houseinfoDO);
//                System.out.println("house-info-insert-ok");
//                result.put("result", 10000);
//                result.put("errMsg", "成功");
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//    /*
//    *2.2 待安装房源
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=readytodo")
//    public void readytodo(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        readytodoDAOImpl readytodoDAO = (readytodoDAOImpl) context.getBean("readytodoDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//        int i=0;
//        JSONObject data = new JSONObject();
//        try {
//
//            String phone = request.getParameter("phone");
//
//
//
//            if (phone == null || phone.isEmpty() ) {
//                result.put("errMsg", "失败");
//                result.put("result","10001");
//            } else {
//                // 获取用户的数据
//
//                List<readytodoDO> readytodo = readytodoDAO.list();
//                if (readytodo.isEmpty()) {
//                    result.put("result", 10002);
//                    result.put("errMsg", "没有数据");
//                    System.out.println("数据库为空");
//                } else {
//                    result.put("result", 10000);
//                    result.put("errMsg", "成功");
//                    System.out.println("ready-to-do");
//                    ArrayList<JSONObject> readytododata = new ArrayList<JSONObject>();
//
//                    for (readytodoDO readytodoDO : readytodo){
//                        JSONObject data1 = new JSONObject();
//                        data1.put("addressID", readytodoDO.getHouse_id());
//                        data1.put("address", readytodoDO.getHouse_address());
//                        data1.put("landlord", readytodoDO.getHouse_owner());
//                        data1.put("cellphone", readytodoDO.getOwner_phone());
//                        i++;
//
//                        readytododata.add(data1);
//                    }
//                    data.put("data", readytododata);
//                }
//
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        data.put("number",i);
//        data.put("result", result);
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//    }
//
//
//    /*
//    *2.3 搜索已安装房源
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=alreadydone")
//    public void alreadydone(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        alreadydoneDAOImpl alreadydoneDAO = (alreadydoneDAOImpl) context.getBean("alreadydoneDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//        int i=0;
//        JSONObject data = new JSONObject();
//        try {
//
//            String phone = request.getParameter("phone");
//            String time1 = request.getParameter("time1");
//            String time2 = request.getParameter("time2");
//            String worker_name = request.getParameter("worker");
//            String house_owner_name = request.getParameter("landlord");
//            String house_address = request.getParameter("key");
//            String type = request.getParameter("type");
//
//
//            if (type == null || type.isEmpty() ) {
//                result.put("errMsg", "失败");
//                result.put("result","10001");
//            } else {
//                if(type.equals("3")){
//                    //  time1 time2 worker_name
//
//                    String timemin = time1 +" 00:00:00";
//                    String timemax = time2 +" 00:00:00";
//
//                    List<alreadydoneDO> alreadydone = alreadydoneDAO.list(worker_name,timemin,timemax);
//                    if (alreadydone.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("already-done-list-3");
//                        ArrayList<JSONObject> alreadydonedata = new ArrayList<JSONObject>();
//
//                        for (alreadydoneDO alreadydoneDO : alreadydone){
//                            JSONObject data1 = new JSONObject();
//                            data1.put("missionID", alreadydoneDO.getMission_id());
//                            data1.put("worker", alreadydoneDO.getWorker_name());
//                            data1.put("addressID", alreadydoneDO.getHouse_id());
//                            data1.put("address", alreadydoneDO.getHouse_address());
//                            data1.put("install_time", alreadydoneDO.getStart_time());
//                            data1.put("cellphone", alreadydoneDO.getHouse_owner_phone());
//                            data1.put("landlord", alreadydoneDO.getHouse_owner_name());
//
//                            i++;
//                            alreadydonedata.add(data1);
//                        }
//                        data.put("data", alreadydonedata);
//                    }
//                }
//                if(type.equals("2")) {
//                    // address
//
//
//                    List<alreadydoneDO> alreadydone = alreadydoneDAO.list2(house_address);
//                    if (alreadydone.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("already-done-list-2");
//                        ArrayList<JSONObject> alreadydonedata1 = new ArrayList<JSONObject>();
//
//                        for (alreadydoneDO alreadydoneDO : alreadydone) {
//                            JSONObject data2 = new JSONObject();
//                            data2.put("missionID", alreadydoneDO.getMission_id());
//                            data2.put("worker", alreadydoneDO.getWorker_name());
//                            data2.put("addressID", alreadydoneDO.getHouse_id());
//                            data2.put("address", alreadydoneDO.getHouse_address());
//                            data2.put("install_time", alreadydoneDO.getStart_time());
//                            data2.put("landlord", alreadydoneDO.getHouse_owner_name());
//                            data2.put("cellphone", alreadydoneDO.getHouse_owner_phone());
//
//                            i++;
//
//                            alreadydonedata1.add(data2);
//                        }
//                        data.put("data", alreadydonedata1);
//                    }
//                }
//
//                if(type.equals("1")) {
//                    //  house_owner_name
//
//
//
//                    List<alreadydoneDO> alreadydone = alreadydoneDAO.list1(house_owner_name);
//                    if (alreadydone.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("already-done-list-1");
//                        ArrayList<JSONObject> alreadydonedata2 = new ArrayList<JSONObject>();
//
//                        for (alreadydoneDO alreadydoneDO : alreadydone) {
//                            JSONObject data3 = new JSONObject();
//                            data3.put("missionID", alreadydoneDO.getMission_id());
//                            data3.put("addressID", alreadydoneDO.getHouse_id());
//                            data3.put("address", alreadydoneDO.getHouse_address());
//                            data3.put("worker", alreadydoneDO.getWorker_name());
//                            data3.put("install_time", alreadydoneDO.getStart_time());
//                            data3.put("cellphone", alreadydoneDO.getHouse_owner_phone());
//                            data3.put("landlord", alreadydoneDO.getHouse_owner_name());
//
//                            i++;
//
//                            alreadydonedata2.add(data3);
//                        }
//                        data.put("data", alreadydonedata2);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        data.put("number",i);
//        data.put("result", result);
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//    }
//
//
//    /*
//    *2.4地图查询
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=mapshow")
//    public void mapshow(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        mapshowDAOImpl mapshowDAO = (mapshowDAOImpl) context.getBean("mapshowDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//        JSONObject data = new JSONObject();
//        try {
//
//            String phone = request.getParameter("phone");
//
//
//
//                    List<mapshowDO> mapshow = mapshowDAO.list();
//                    if (mapshow.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//
//                        ArrayList<JSONObject> mapshowdata = new ArrayList<JSONObject>();
//
//                        for (mapshowDO mapshowDO : mapshow){
//                            JSONObject data1 = new JSONObject();
//                            data1.put("worker", mapshowDO.getWorker_name());
//                            data1.put("phone", mapshowDO.getWorker_phone());
//                            data1.put("longitude", mapshowDO.getLongitude());
//                            data1.put("latitude", mapshowDO.getLatitude());
//                            mapshowdata.add(data1);
//                        }
//                        data.put("worker_data", mapshowdata);
//                    }
//            List<mapshow1DO> mapshow1 = mapshowDAO.list1();
//            if (mapshow1.isEmpty()) {
//                result.put("result", 10002);
//                result.put("errMsg", "没有数据");
//                System.out.println("数据库为空");
//            } else {
//                result.put("result", 10000);
//                result.put("errMsg", "成功");
//                System.out.println("map-info");
//                ArrayList<JSONObject> mapshow1data = new ArrayList<JSONObject>();
//
//                for (mapshow1DO mapshow1DO : mapshow1){
//                    JSONObject data1 = new JSONObject();
//                    data1.put("addressID", mapshow1DO.getHouse_id());
//                    data1.put("address", mapshow1DO.getHouse_address());
//                    data1.put("jd", mapshow1DO.getLongitude());
//                    data1.put("wd", mapshow1DO.getLatitude());
//                    mapshow1data.add(data1);
//                }
//                data.put("house_data", mapshow1data);
//            }
//
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        data.put("result", result);
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//    }
//
//    /*
//    *2.9拒单查询
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=refusefind")
//    public void refusefind(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        refusefindDAOImpl refusefindDAO = (refusefindDAOImpl) context.getBean("refusefindDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//        JSONObject data = new JSONObject();
//        int i =0;
//        try {
//
//            String phone = request.getParameter("phone");
//            String worker_name = request.getParameter("worker");
//            String time1 = request.getParameter("time1");
//            String time2 = request.getParameter("time2");
//
//            worker_name = "";
//                String timemmin;
//                String timemmax;
//                if (time1 == null || time1.isEmpty()) {
//                    timemmin = "2017-06-12 09:30:00";
//                } else {
//                    timemmin = time1 +" 00:00:00";
//                }
//                if (time2 == null || time2.isEmpty()) {
//                    timemmax = "2020-10-12 09:30:00";
//                } else {
//                    timemmax = time2 +" 00:00:00";
//                }
//                System.out.println(timemmax);
//                System.out.println(timemmin);
//
//                List<refusefindDO> refusefind = refusefindDAO.listrefusefind(worker_name,timemmin,timemmax);
//                if (refusefind.isEmpty()) {
//                    result.put("result", 10002);
//                    result.put("errMsg", "没有数据");
//                    System.out.println("数据库为空");
//                } else {
//                    result.put("result", 10000);
//                    result.put("errMsg", "成功");
//                    System.out.println("find-refuse");
//                    ArrayList<JSONObject> refusefinddata = new ArrayList<JSONObject>();
//
//                    for (refusefindDO refusefindDO : refusefind) {
//                        JSONObject data1 = new JSONObject();
//                        data1.put("mission_id", refusefindDO.getMission_id());
//                        data1.put("worker", refusefindDO.getWorker_name());
//                        data1.put("addressID", refusefindDO.getHouse_id());
//                        data1.put("address", refusefindDO.getHouse_address());
//                        data1.put("reason", refusefindDO.getRefuse_reason());
//                        data1.put("time", refusefindDO.getRefuse_time());
//                        data1.put("mission_condition", refusefindDO.getMission_condition());
//                        i++;
//
//                        refusefinddata.add(data1);
//                    }
//                    data.put("data", refusefinddata);
//                }
//
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        data.put("count", i);
//        data.put("result", result);
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//    }
//
//    /*
//        *2.5派单
//        *
//        * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=setmission")
//    public void setmission(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        setmissionDAOImpl setmissionDAO = (setmissionDAOImpl) context.getBean("setmissionDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//        try {
//
//            String set_phone = request.getParameter("phone");
//            String team = request.getParameter("team");
//            String worker_name = request.getParameter("worker");
//            String worker_phone = request.getParameter("worker_phone");
//            String mission_type = request.getParameter("task_nature");
//            String set_start_time = request.getParameter("expected_start_time");
//            String set_finish_time = request.getParameter("expected_finish_time");
//            String mission_addition = request.getParameter("remark");
//            String house_id = request.getParameter("addressID");
//            String house_address = request.getParameter("address");
//            String lock_code = request.getParameter("door_num");
//            String door_type = request.getParameter("door_type");
//            String lock_type = request.getParameter("lock_type");
//            String lock_model = request.getParameter("lock_model");
//            String mission_set_time = request.getParameter("send_time");
//            String mission_level = request.getParameter("task_level");
//            String mission_time_code = request.getParameter("mission_time_code");
//
//
//
//            if (set_phone == null || set_phone.isEmpty() ||team == null || team.isEmpty() ||
//                    worker_name == null || worker_name.isEmpty()||worker_phone == null || worker_phone.isEmpty()||
//                    mission_type == null || mission_type.isEmpty()||set_start_time == null || set_start_time.isEmpty()||
//                    set_finish_time == null || set_finish_time.isEmpty()||
//                    house_id == null || house_id.isEmpty()||lock_code == null || lock_code.isEmpty()||
//                    door_type == null || door_type.isEmpty()||house_address == null || house_address.isEmpty()||
//                    lock_type == null || lock_type.isEmpty()||lock_model == null || lock_model.isEmpty()||
//                    mission_set_time == null || mission_set_time.isEmpty()||mission_level == null || mission_level.isEmpty()
//                    ) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//                //find number
//                int i = 1;
//
//                    List<setmissionDO> setmission = setmissionDAO.list(house_id,lock_code);
//                    if (setmission.isEmpty()) {
//                    }else{
//                //refuse
//
//
//                        ArrayList<JSONObject> setmissiondata = new ArrayList<JSONObject>();
//
//                        for (setmissionDO setmissionDO : setmission) {
//                            i++;
//                        }
//
//                        setmissionDO setmissionDO = new setmissionDO();
//
//                        setmissionDO.setFlow_number(house_id);
//                        setmissionDO.setMission_condition("7");
//
//                        setmissionDAO.update2(setmissionDO);
//                    }
//                int random_code_int = (int)((Math.random()*9+1)*1000);
//                String random_code = String.valueOf(random_code_int);
//                String s =Integer.toString(i);
//                    String number = house_id+lock_code+s;
//                System.out.println(number);
//                // insert
//                setmissionDO setmissionDO = new setmissionDO();
//                setmissionDO.setRandom_code(random_code);
//                setmissionDO.setSet_phone(set_phone);
//                setmissionDO.setTeam(team);
//                setmissionDO.setFlow_number(number);
//                setmissionDO.setWorker_name(worker_name);
//                setmissionDO.setWorker_phone(worker_phone);
//                setmissionDO.setMission_type(mission_type);
//                setmissionDO.setSet_start_time(set_start_time);
//                setmissionDO.setSet_finish_time(set_finish_time);
//                setmissionDO.setHouse_id(house_id);
//                setmissionDO.setLock_code(lock_code);
//                setmissionDO.setDoor_type(door_type);
//                setmissionDO.setHouse_address(house_address);
//                setmissionDO.setLock_type(lock_type);
//                setmissionDO.setLock_model(lock_model);
//                setmissionDO.setMission_addition(mission_addition);
//                setmissionDO.setMission_set_time(mission_set_time);
//                setmissionDO.setMission_level(mission_level);
//                setmissionDO.setMission_condition("0");
//                setmissionDAO.insert(setmissionDO);
//
//                arrangeinsertDO arrangeinsertDO = new arrangeinsertDO();
//
//                arrangeinsertDO.setTime_left(set_start_time);
//                arrangeinsertDO.setTime_right(set_start_time);
//                arrangeinsertDO.setSet_time(mission_time_code);
//                arrangeinsertDO.setWorker_name(worker_name);
//
//                setmissionDAO.update(arrangeinsertDO);
//
//                readytodoDO readytodoDO = new readytodoDO();
//                readytodoDO.setHouse_id(house_id);
//                readytodoDO.setSet_condition("2");
//                setmissionDAO.update1(readytodoDO);
//
//
//
//                System.out.println("mission-insert-ok");
//                result.put("result", 10000);
//                result.put("errMsg", "成功");
//
//                SendMessage.sendMessage(worker_phone,"您收到任务，请打开微信小程序查看");
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//    /*
//    *2.6任务详情
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=missiondetail")
//    public void missiondetail(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        missiondetailDAOImpl missiondetailDAO = (missiondetailDAOImpl) context.getBean("missiondetailDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//        JSONObject data = new JSONObject();
//        int i = 0;
//        try {
//
//
//            String worker_name = request.getParameter("worker");
//            String time1 = request.getParameter("time1");
//            String time2 = request.getParameter("time2");
//            String mission_condition = "";
//
//            if (time1 == null || time1.isEmpty()||time2 == null || time2.isEmpty()) {
//                result.put("errMsg", "请输入名称");
//                result.put("result","10001");
//            } else {
//                String ymmin = time1 +" 00:00:00";
//                String ymmax = time2 +" 00:00:00";
//                //all select
//                if(mission_condition ==null ||mission_condition.isEmpty()){
//                    List<missiondetailDO> missiondetail = missiondetailDAO.list1(worker_name,ymmin,ymmax);
//                    if (missiondetail.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空all");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("mission-detail-all");
//                        ArrayList<JSONObject> missiondetaildata = new ArrayList<JSONObject>();
//
//                        for (missiondetailDO missiondetailDO : missiondetail) {
//                            JSONObject data1 = new JSONObject();
//                            data1.put("number", missiondetailDO.getFlow_number());
//                            data1.put("team", missiondetailDO.getTeam());
//                            data1.put("worker", missiondetailDO.getWorker_name());
//                            data1.put("phone", missiondetailDO.getWorker_phone());
//                            data1.put("task_nature", missiondetailDO.getMission_type());
//                            data1.put("task_level", missiondetailDO.getMission_level());
//                            data1.put("door_num", missiondetailDO.getLock_code());
//                            data1.put("door_type", missiondetailDO.getDoor_type());
//                            data1.put("lock_type", missiondetailDO.getLock_type());
//                            data1.put("lock_model", missiondetailDO.getLock_model());
//                            data1.put("expected_start_time", missiondetailDO.getSet_start_time());
//                            data1.put("expected_finish_time", missiondetailDO.getSet_finish_time());
//                            data1.put("remark", missiondetailDO.getMission_addition());
//                            data1.put("addressID", missiondetailDO.getHouse_id());
//                            data1.put("address", missiondetailDO.getHouse_address());
//                            data1.put("send_time", missiondetailDO.getMission_set_time());
//                            data1.put("final_time", missiondetailDO.getFinish_time());
//                            data1.put("auditor", missiondetailDO.getAuditor());
//                            data1.put("audit_opinion", missiondetailDO.getAuditor_opinion());
//                            data1.put("audit_time", missiondetailDO.getAuditor_time());
//                            data1.put("mission_condition", missiondetailDO.getMission_condition());
//                            i++;
//
//                            missiondetaildata.add(data1);
//                        }
//                        data.put("data", missiondetaildata);
//                    }
//                }
//                else{
//                    List<missiondetailDO> missiondetail = missiondetailDAO.list(mission_condition,worker_name,ymmin,ymmax);
//                    if (missiondetail.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空sp");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("mission-detail-sp");
//                        ArrayList<JSONObject> missiondetaildata = new ArrayList<JSONObject>();
//
//                        for (missiondetailDO missiondetailDO : missiondetail) {
//                            JSONObject data1 = new JSONObject();
//                            i++;
//                            data1.put("number", missiondetailDO.getFlow_number());
//                            data1.put("team", missiondetailDO.getTeam());
//                            data1.put("worker", missiondetailDO.getWorker_name());
//                            data1.put("phone", missiondetailDO.getWorker_phone());
//                            data1.put("task_nature", missiondetailDO.getMission_type());
//                            data1.put("task_level", missiondetailDO.getMission_level());
//                            data1.put("door_num", missiondetailDO.getLock_code());
//                            data1.put("door_type", missiondetailDO.getDoor_type());
//                            data1.put("lock_type", missiondetailDO.getLock_type());
//                            data1.put("lock_model", missiondetailDO.getLock_model());
//                            data1.put("expected_start_time", missiondetailDO.getSet_start_time());
//                            data1.put("expected_finish_time", missiondetailDO.getSet_finish_time());
//                            data1.put("remark", missiondetailDO.getMission_addition());
//                            data1.put("addressID", missiondetailDO.getHouse_id());
//                            data1.put("address", missiondetailDO.getHouse_address());
//                            data1.put("send_time", missiondetailDO.getMission_set_time());
//                            data1.put("final_time", missiondetailDO.getFinish_time());
//                            data1.put("auditor", missiondetailDO.getAuditor());
//                            data1.put("audit_opinion", missiondetailDO.getAuditor_opinion());
//                            data1.put("audit_time", missiondetailDO.getAuditor_time());
//
//
//                            missiondetaildata.add(data1);
//                        }
//                        data.put("data", missiondetaildata);
//                    }
//                }
//
//            }
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        data.put("count", i);
//        data.put("result", result);
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//    }
//
//    /*
//    *2.10 任务完成查询
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=findfinish")
//    public void findfinish(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        findfinishDAOImpl findfinishDAO = (findfinishDAOImpl) context.getBean("findfinishDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//        JSONObject data = new JSONObject();
//        int i =0;
//        try {
//
//            String phone = request.getParameter("phone");
//            String worker_name = request.getParameter("worker");
//            String time1 = request.getParameter("time1");
//            String time2 = request.getParameter("time2");
//            String mission_condition = request.getParameter("search_status");
//            String timemmin;
//            String timemmax;
//            if (time1 == null || time1.isEmpty()) {
//                timemmin = "2017-06-12 09:30:00";
//            } else {
//                timemmin = time1 +" 00:00:00";
//            }
//            if (time2 == null || time2.isEmpty()) {
//                timemmax = "2019-10-12 09:30:00";
//            } else {
//                timemmax = time2 +" 00:00:00";
//            }
//
//
//            if (worker_name == null || worker_name.isEmpty()) {
//                //seekall
//                if(mission_condition.equals("all")){
//                    List<findfinishDO> findfinish = findfinishDAO.list1(timemmin,timemmax);
//                    if (findfinish.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("find-finish");
//                        ArrayList<JSONObject> findfinishdata = new ArrayList<JSONObject>();
//
//                        for (findfinishDO findfinishDO : findfinish) {
//                            JSONObject data1 = new JSONObject();
//
//                            data1.put("mission_id", findfinishDO.getMission_id());
//                            data1.put("team", findfinishDO.getTeam());
//                            data1.put("worker", findfinishDO.getWorker_name());
//                            data1.put("phone", findfinishDO.getWorker_phone());
//                            data1.put("task_nature", findfinishDO.getMission_type());
//                            data1.put("task_level", findfinishDO.getMission_level());
//                            data1.put("door_num", findfinishDO.getLock_code());
//                            data1.put("door_type", findfinishDO.getDoor_type());
//                            data1.put("lock_type", findfinishDO.getLock_type());
//                            data1.put("lock_model", findfinishDO.getLock_model());
//                            data1.put("expected_start_time", findfinishDO.getSet_start_time());
//                            data1.put("expected_finish_time", findfinishDO.getSet_finish_time());
//                            data1.put("remark", findfinishDO.getMission_addition());
//                            data1.put("addressID", findfinishDO.getHouse_id());
//                            data1.put("address", findfinishDO.getHouse_address());
//                            data1.put("send_time", findfinishDO.getMission_set_time());
//                            data1.put("final_time", findfinishDO.getFinish_time());
//                            data1.put("mission_condition", findfinishDO.getMission_condition());
//                            data1.put("note", findfinishDO.getNote());
//                            data1.put("auditor", findfinishDO.getAuditor());
//                            data1.put("auditor_opinion", findfinishDO.getAuditor_opinion());
//                            data1.put("auditor_time", findfinishDO.getAuditor_time());
//                            i++;
//                            findfinishdata.add(data1);
//                        }
//                        data.put("data", findfinishdata);
//                    }
//                }else{
//                    List<findfinishDO> findfinish = findfinishDAO.list2(timemmin,timemmax,mission_condition);
//                    if (findfinish.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("find-refuse-mission-condition-4or5");
//                        ArrayList<JSONObject> findfinishdata = new ArrayList<JSONObject>();
//
//                        for (findfinishDO findfinishDO : findfinish) {
//                            JSONObject data1 = new JSONObject();
//
//                            data1.put("mission_id", findfinishDO.getMission_id());
//                            data1.put("team", findfinishDO.getTeam());
//                            i++;
//                            data1.put("worker", findfinishDO.getWorker_name());
//                            data1.put("phone", findfinishDO.getWorker_phone());
//                            data1.put("task_nature", findfinishDO.getMission_type());
//                            data1.put("task_level", findfinishDO.getMission_level());
//                            data1.put("door_num", findfinishDO.getLock_code());
//                            data1.put("door_type", findfinishDO.getDoor_type());
//                            data1.put("lock_type", findfinishDO.getLock_type());
//                            data1.put("lock_model", findfinishDO.getLock_model());
//                            data1.put("expected_start_time", findfinishDO.getSet_start_time());
//                            data1.put("expected_finish_time", findfinishDO.getSet_finish_time());
//                            data1.put("remark", findfinishDO.getMission_addition());
//                            data1.put("addressID", findfinishDO.getHouse_id());
//                            data1.put("address", findfinishDO.getHouse_address());
//                            data1.put("send_time", findfinishDO.getMission_set_time());
//                            data1.put("final_time", findfinishDO.getFinish_time());
//                            data1.put("mission_condition", findfinishDO.getMission_condition());
//                            data1.put("auditor", findfinishDO.getAuditor());
//                            data1.put("auditor_opinion", findfinishDO.getAuditor_opinion());
//                            data1.put("auditor_time", findfinishDO.getAuditor_time());
//                            data1.put("note", findfinishDO.getNote());
//
//                            findfinishdata.add(data1);
//                        }
//                        data.put("data", findfinishdata);
//                    }
//                }
//                } else {
//                if(mission_condition.equals("all")){
//
//                    System.out.println(timemmax);
//                    System.out.println(timemmin);
//
//                    List<findfinishDO> findfinish = findfinishDAO.list(worker_name,timemmin,timemmax);
//                    if (findfinish.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("find-finish-know-name");
//                        ArrayList<JSONObject> findfinishdata = new ArrayList<JSONObject>();
//
//                        for (findfinishDO findfinishDO : findfinish) {
//                            JSONObject data1 = new JSONObject();
//                            i++;
//                            data1.put("mission_id", findfinishDO.getMission_id());
//                            data1.put("team", findfinishDO.getTeam());
//                            data1.put("worker", findfinishDO.getWorker_name());
//                            data1.put("phone", findfinishDO.getWorker_phone());
//                            data1.put("task_nature", findfinishDO.getMission_type());
//                            data1.put("task_level", findfinishDO.getMission_level());
//                            data1.put("door_num", findfinishDO.getLock_code());
//                            data1.put("door_type", findfinishDO.getDoor_type());
//                            data1.put("lock_type", findfinishDO.getLock_type());
//                            data1.put("lock_model", findfinishDO.getLock_model());
//                            data1.put("expected_start_time", findfinishDO.getSet_start_time());
//                            data1.put("expected_finish_time", findfinishDO.getSet_finish_time());
//                            data1.put("remark", findfinishDO.getMission_addition());
//                            data1.put("addressID", findfinishDO.getHouse_id());
//                            data1.put("address", findfinishDO.getHouse_address());
//                            data1.put("send_time", findfinishDO.getMission_set_time());
//                            data1.put("final_time", findfinishDO.getFinish_time());
//                            data1.put("mission_condition", findfinishDO.getMission_condition());
//                            data1.put("auditor", findfinishDO.getAuditor());
//                            data1.put("auditor_opinion", findfinishDO.getAuditor_opinion());
//                            data1.put("auditor_time", findfinishDO.getAuditor_time());
//                            data1.put("note", findfinishDO.getNote());
//
//                            findfinishdata.add(data1);
//                        }
//                        data.put("data", findfinishdata);
//                    }
//                }else{
//
//
//                    List<findfinishDO> findfinish = findfinishDAO.list3(worker_name,timemmin,timemmax,mission_condition);
//                    if (findfinish.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("find-finish-know-name-mission-condition-4or5");
//                        ArrayList<JSONObject> findfinishdata = new ArrayList<JSONObject>();
//
//                        for (findfinishDO findfinishDO : findfinish) {
//                            JSONObject data1 = new JSONObject();
//
//                            data1.put("mission_id", findfinishDO.getMission_id());
//                            data1.put("team", findfinishDO.getTeam());
//                            data1.put("worker", findfinishDO.getWorker_name());
//                            data1.put("phone", findfinishDO.getWorker_phone());
//                            data1.put("task_nature", findfinishDO.getMission_type());
//                            data1.put("task_level", findfinishDO.getMission_level());
//                            data1.put("door_num", findfinishDO.getLock_code());
//                            data1.put("door_type", findfinishDO.getDoor_type());
//                            data1.put("lock_type", findfinishDO.getLock_type());
//                            i++;
//                            data1.put("lock_model", findfinishDO.getLock_model());
//                            data1.put("expected_start_time", findfinishDO.getSet_start_time());
//                            data1.put("expected_finish_time", findfinishDO.getSet_finish_time());
//                            data1.put("remark", findfinishDO.getMission_addition());
//                            data1.put("addressID", findfinishDO.getHouse_id());
//                            data1.put("address", findfinishDO.getHouse_address());
//                            data1.put("send_time", findfinishDO.getMission_set_time());
//                            data1.put("final_time", findfinishDO.getFinish_time());
//                            data1.put("mission_condition", findfinishDO.getMission_condition());
//                            data1.put("auditor", findfinishDO.getAuditor());
//                            data1.put("auditor_opinion", findfinishDO.getAuditor_opinion());
//                            data1.put("auditor_time", findfinishDO.getAuditor_time());
//                            data1.put("note", findfinishDO.getNote());
//
//                            findfinishdata.add(data1);
//                        }
//                        data.put("data", findfinishdata);
//                    }
//                }
//
//            }
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        data.put("count", i);
//        data.put("result", result);
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//    }
//
//     /*
//        *2.11 任务完成审核
//        *
//        * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=checkmission")
//    public void checkmission(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        checkmissionDAOImpl checkmissionDAO = (checkmissionDAOImpl) context.getBean("checkmissionDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//        try {
//            String auditor_opinion = "";
//            String phone = request.getParameter("phone");
//            String mission_id = request.getParameter("mission_id");
//            String auditor = request.getParameter("auditor");
//            auditor_opinion = request.getParameter("audit_opinion");
//            String auditor_time = request.getParameter("audit_time");
//            String type = request.getParameter("status");
//
//
//
//            if (mission_id == null || mission_id.isEmpty()) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//                if(type.equals("5")){
//                    //finish
//                    checkmissionDO checkmissionDO = new checkmissionDO();
//                    checkmissionDO.setMission_id(mission_id);
//                    checkmissionDO.setAuditor(auditor);
//                    checkmissionDO.setAuditor_opinion(auditor_opinion);
//                    checkmissionDO.setAuditor_time(auditor_time);
//                    checkmissionDAO.update1(checkmissionDO);
//                    result.put("errMsg", "保存成功！");
//                    result.put("result","10000");
//                    System.out.println("finish-check-mission-1");
//                }else{
//                    //check-mission-wrong
//                    checkmissionDO checkmissionDO = new checkmissionDO();
//                    checkmissionDO.setMission_id(mission_id);
//                    checkmissionDO.setAuditor(auditor);
//                    checkmissionDO.setAuditor_opinion(auditor_opinion);
//                    checkmissionDO.setAuditor_time(auditor_time);
//                    checkmissionDAO.update1(checkmissionDO);
//                    result.put("errMsg", "保存成功！");
//                    result.put("result","10000");
//                    System.out.println("finish-check-mission-2");
//                }
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//    /*
//   *2.12 管理人员登录
//   *
//   * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=loginss")
//    public void loginss(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        loginssDAOImpl loginssDAO = (loginssDAOImpl) context.getBean("loginssDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//        try {
//
//            String phone = request.getParameter("phone");
//            String password = request.getParameter("password");
//
//
//
//
//            if (phone == null || phone.isEmpty()||password == null || password.isEmpty()) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//                // 获取用户的数据
//                loginssDO loginssDO = new loginssDO();
//                loginssDO.setPhone(phone);
//
//                loginssDO userr = loginssDAO.get(loginssDO);
//                /*System.out.println(user);*/
//
//                if (userr == null) {
//                    result.put("errMsg", "用户名输入错误");
//                    result.put("result","10002");
//                } else {
//                    // 检查用户密码是否正确
//                    if (password.equals(userr.getPassword())) {
//                        result.put("errMsg", "登录成功");
//                        result.put("result","10000");
//
//                    } else {
//                        result.put("errMsg", "密码错误");
//                        result.put("result","10003");
//                        //wrong
//                    }
//                }
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//
//
//    /*
//*1.3修改密码
//*
//* */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=changepass")
//    public void changepass(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        changepassDAOImpl changepassDAO = (changepassDAOImpl) context.getBean("changepassDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//        try {
//
//            String phone = request.getParameter("phone");
//            String old_password = request.getParameter("password1");
//            String new_password = request.getParameter("password2");
//
//
//
//            if (phone == null || phone.isEmpty()||old_password == null || old_password.isEmpty()||new_password == null || new_password.isEmpty()) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//                // 获取用户的数据
//                changepassDO changepassDO = new changepassDO();
//                changepassDO.setPhone(phone);
//
//                changepassDO userrr = changepassDAO.get(changepassDO);
//                /*System.out.println(user);*/
//
//                if (userrr == null) {
//                    result.put("errMsg", "用户名输入错误");
//                    result.put("result","10002");
//                } else {
//                    // 检查用户密码是否正确
//                    if (old_password.equals(userrr.getPassword())) {
//
//                        changepassDO changepassDO1 = new changepassDO();
//                        changepassDO1.setPhone(phone);
//                        changepassDO1.setPassword(new_password);
//
//                        changepassDAO.update(changepassDO1);
//                        result.put("errMsg", "保存成功！");
//                        result.put("result","10000");
//                        System.out.println("change-password-finish");
//
//                    } else {
//                        result.put("errMsg", "密码错误");
//                        result.put("result","10003");
//                        //wrong
//                    }
//                }
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//
//    /*
//    *1.5	查询已接收任务单
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=seekall")
//    public void seekall(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        seekallDAOImpl seekallDAO = (seekallDAOImpl) context.getBean("seekallDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//        int i=0;
//
//        JSONObject data = new JSONObject();
//        try {
//
//            String worker_phone = request.getParameter("phone");
//
//
//
//
//            if (worker_phone == null || worker_phone.isEmpty()) {
//                result.put("errMsg", "失败");
//                result.put("result","10001");
//            } else {
//
//
//                    List<seekallDO> seekall = seekallDAO.list(worker_phone);
//                    if (seekall.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("seek-all-ok");
//                        ArrayList<JSONObject> seekalldata = new ArrayList<JSONObject>();
//
//                        for (seekallDO seekallDO : seekall){
//                            JSONObject data1 = new JSONObject();
//                            data1.put("missionID", seekallDO.getMission_id());
//                            data1.put("number", seekallDO.getFlow_number());
//                            data1.put("status", seekallDO.getMission_condition());
//                            data1.put("addressID", seekallDO.getHouse_id());
//                            data1.put("address", seekallDO.getHouse_address());
//
//                            data1.put("install_time", seekallDO.getFinish_time());
//                            data1.put("refuse_time", seekallDO.getRefuse_time());
//                            i++;
//
//                            seekalldata.add(data1);
//                        }
//                        data.put("data", seekalldata);
//                    }
//                }
//
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        data.put("number", i);
//        data.put("result", result);
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//    }
//
//
//    /*
//    *2.6任务详情
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=missionfin")
//    public void missionfin(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        missionfinDAOImpl missionfinDAO = (missionfinDAOImpl) context.getBean("missionfinDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//        JSONObject data = new JSONObject();
//        int i = 0;
//        try {
//
//
//            String worker_name = request.getParameter("worker");
//            String time1 = request.getParameter("time1");
//            String time2 = request.getParameter("time2");
//            String mission_condition = "4";
//                if(worker_name == null || worker_name.isEmpty())
//                 worker_name = "";
//
//                if(time1 == null || time1.isEmpty()){
//                    time1 = "2017-01-01";
//                    time2 = "2021-12-01";
//                }
//                String ymmin = time1 +" 00:00:00";
//                String ymmax = time2 +" 00:00:00";
//                //all select
//                /*            System.out.println(ymmin);
//                         System.out.println(ymmax);*/
//                    List<missionfinDO> missionfin = missionfinDAO.list(mission_condition,worker_name,ymmin,ymmax);
//                    if (missionfin.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空all");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("mission-fin-all");
//                        ArrayList<JSONObject> missionfindata = new ArrayList<JSONObject>();
//
//                        for (missionfinDO missionfinDO : missionfin) {
//                            JSONObject data1 = new JSONObject();
//                            data1.put("mission_id", missionfinDO.getMission_id());
//                            data1.put("team", missionfinDO.getTeam());
//                            data1.put("worker", missionfinDO.getWorker_name());
//                            data1.put("phone", missionfinDO.getWorker_phone());
//                            data1.put("task_nature", missionfinDO.getMission_type());
//                            data1.put("task_level", missionfinDO.getMission_level());
//                            data1.put("door_num", missionfinDO.getLock_code());
//                            data1.put("door_type", missionfinDO.getDoor_type());
//                            data1.put("lock_type", missionfinDO.getLock_type());
//                            data1.put("lock_model", missionfinDO.getLock_model());
//                            data1.put("expected_start_time", missionfinDO.getSet_start_time());
//                            data1.put("expected_finish_time", missionfinDO.getSet_finish_time());
//                            data1.put("remark", missionfinDO.getMission_addition());
//                            data1.put("addressID", missionfinDO.getHouse_id());
//                            data1.put("address", missionfinDO.getHouse_address());
//
//                            data1.put("send_time", missionfinDO.getMission_set_time());
//                            data1.put("final_time", missionfinDO.getSet_finish_time());
//                            data1.put("auditor", missionfinDO.getAuditor());
//                            data1.put("audit_opinion", missionfinDO.getAuditor_opinion());
//                            data1.put("audit_time", missionfinDO.getAuditor_time());
//                            data1.put("note", missionfinDO.getNote());
//
//                            i++;
//
//                            missionfindata.add(data1);
//                        }
//                        data.put("data", missionfindata);
//                    }
//
//
//
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        data.put("count", i);
//        data.put("result", result);
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//    }
//
//
//    /*
//    *2.7 图片上传
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=picinfo")
//    public void picinfo(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        picinfoDAOImpl picinfoDAO = (picinfoDAOImpl) context.getBean("picinfoDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//        try {
//            String phone = request.getParameter("phone");
//            String mission_id = request.getParameter("mission_id");
//            String pic_class = request.getParameter("pic_class");
//            String pic_name = request.getParameter("pic_name");
//            String time = request.getParameter("time");
//
//            /*System.out.println(phone);*/
//
//            if (pic_class == null || pic_class.isEmpty() ||
//                    pic_name == null || pic_name.isEmpty()||time == null || time.isEmpty()
//                     ) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//                /*System.out.println("0000");*/
//                //create location
//                if (phone==null || phone.isEmpty()) {
//                    //weichat
//                    /*System.out.println("1234");*/
//                    //String pic_namereal = pic_name.substring(11,pic_name.length());
//
//                    String pic_loc = "\\WEB-INF\\upload\\" + pic_name;
///*                    System.out.println(pic_name);
//
//                    System.out.println(mission_id);*/
//
//                    // insert
//                    picinfoDO picinfoDO = new picinfoDO();
//                    picinfoDO.setPic_class(pic_class);
//                    picinfoDO.setMission_id(mission_id);
//                    picinfoDO.setPic(pic_loc);
//                    picinfoDO.setTime(time);
//
//                    picinfoDAO.insert(picinfoDO);
//                    System.out.println("pic-info-insert-ok-2");
//                    result.put("result", 10000);
//                    result.put("errMsg", "成功");
//                }else{
//                    /*System.out.println("111333");*/
//                    /*System.out.println(phone);*/
//                    findmisDO findmisDO = new findmisDO();
//                    findmisDO.setSet_phone(phone);
//                    /*System.out.println("123");*/
//                    findmisDO user = picinfoDAO.get(findmisDO);
//                    /*System.out.println("456");*/
//
//
//                    String pic_loc = "\\WEB-INF\\upload\\" + pic_name;
//                    mission_id = user.getMission_id();
//                    /*System.out.println(mission_id);*/
//                    /*System.out.println(pic_loc);*/
//                    // insert
//                    picinfoDO picinfoDO = new picinfoDO();
//                    picinfoDO.setPic_class(pic_class);
//                    picinfoDO.setMission_id(mission_id);
//                    picinfoDO.setPic(pic_loc);
//                    picinfoDO.setTime(time);
//
//                    picinfoDAO.insert(picinfoDO);
//                    System.out.println("pic-info-insert-ok-1");
//                    result.put("result", 10000);
//                    result.put("errMsg", "成功");
//                }
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//    /*
//*2.13 recent mission number = five
//*
//* */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=recentmis")
//    public void recentmis(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        recentmisDAOImpl recentmisDAO = (recentmisDAOImpl) context.getBean("recentmisDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//        JSONObject data = new JSONObject();
//        int i = 0;
//        try {
//
//
//            String phone = request.getParameter("phone");
//
//
//            if (phone == null || phone.isEmpty()) {
//                result.put("errMsg", "shibai");
//                result.put("result","10001");
//            } else {
//
//                //all select
//
//                    List<recentmisDO> recentmis = recentmisDAO.list();
//                    if (recentmis.isEmpty()) {
//                        result.put("result", 10002);
//                        result.put("errMsg", "没有数据");
//                        System.out.println("数据库为空all");
//                    } else {
//                        result.put("result", 10000);
//                        result.put("errMsg", "成功");
//                        System.out.println("recent-mission-five");
//                        ArrayList<JSONObject> recentmisdata = new ArrayList<JSONObject>();
//
//                        for (recentmisDO recentmisDO : recentmis) {
//                            JSONObject data1 = new JSONObject();
//                            i++;
//                            data1.put("mission_id", recentmisDO.getMission_id());
//                            data1.put("team", recentmisDO.getTeam());
//                            data1.put("worker", recentmisDO.getWorker_name());
//                            data1.put("phone", recentmisDO.getWorker_phone());
//                            data1.put("task_nature", recentmisDO.getMission_type());
//                            data1.put("task_level", recentmisDO.getMission_level());
//                            data1.put("door_num", recentmisDO.getLock_code());
//                            data1.put("door_type", recentmisDO.getDoor_type());
//                            data1.put("lock_type", recentmisDO.getLock_type());
//                            data1.put("lock_model", recentmisDO.getLock_model());
//                            data1.put("expected_start_time", recentmisDO.getSet_start_time());
//                            data1.put("expected_finish_time", recentmisDO.getSet_finish_time());
//                            data1.put("remark", recentmisDO.getMission_addition());
//                            data1.put("addressID", recentmisDO.getHouse_id());
//                            data1.put("address", recentmisDO.getHouse_address());
//                            data1.put("send_time", recentmisDO.getMission_set_time());
//                            data1.put("final_time", recentmisDO.getFinish_time());
//                            data1.put("auditor", recentmisDO.getAuditor());
//                            data1.put("audit_opinion", recentmisDO.getAuditor_opinion());
//                            data1.put("audit_time", recentmisDO.getAuditor_time());
//                            data1.put("mission_condition", recentmisDO.getMission_condition());
//                            data1.put("note", recentmisDO.getNote());
//
//
//                            recentmisdata.add(data1);
//                        }
//                        data.put("data", recentmisdata);
//                    }
//
//                }
//
//
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        data.put("count", i);
//        data.put("result", result);
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
//    }
//
//    /*
//*2.14 sweep one sweep
//*
//* */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=sweeponesweep")
//    public void sweeponesweep(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        sweeponesweepDAOImpl sweeponesweepDAO = (sweeponesweepDAOImpl) context.getBean("sweeponesweepDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//        JSONObject all = new JSONObject();
//
//        try {
//
//
//            String phone = request.getParameter("phone");
//            String code1 = request.getParameter("code");
//
//
//            if (phone == null || phone.isEmpty()) {
//                result.put("errMsg", "failed");
//                result.put("result","10001");
//            } else {
//
//                //split
//                String[] code2 = code1.split("!");
//                String code = code2[0];
//                sweeponesweepDO sweeponesweepDO = new sweeponesweepDO();
//                sweeponesweepDO.setMission_id(code);
//
//                sweeponesweepDO user = sweeponesweepDAO.get(sweeponesweepDO);
//
//                if (user.getFlow_number().isEmpty()) {
//                    result.put("result", 10002);
//                    result.put("errMsg", "没有数据");
//                    System.out.println("数据库为空all");
//                } else {
//                    result.put("result", 10000);
//                    result.put("errMsg", "成功");
//                    System.out.println("sweep-one-sweep");
//
//                    //ArrayList<JSONObject> sweeponesweepdata = new ArrayList<JSONObject>();
//
//
//                    JSONObject data1 = new JSONObject();
//                          data1.put("team", user.getTeam());
//                        data1.put("mission_id", user.getMission_id());
//                        data1.put("worker", user.getWorker_name());
//                        data1.put("phone", user.getWorker_phone());
//                        data1.put("task_nature", user.getMission_type());
//                        data1.put("task_level", user.getMission_level());
//                        data1.put("door_num", user.getLock_code());
//                        data1.put("door_type", user.getDoor_type());
//                        data1.put("lock_type", user.getLock_type());
//                        data1.put("lock_model", user.getLock_model());
//                        data1.put("expected_start_time", user.getSet_start_time());
//                        data1.put("expected_finish_time", user.getSet_finish_time());
//                        data1.put("remark", user.getMission_addition());
//                        data1.put("addressID", user.getHouse_id());
//                        data1.put("address", user.getHouse_address());
//                        data1.put("send_time", user.getMission_set_time());
//                        data1.put("final_time", user.getFinish_time());
//                        data1.put("auditor", user.getAuditor());
//                        data1.put("audit_opinion", user.getAuditor_opinion());
//                        data1.put("audit_time", user.getAuditor_time());
//                        data1.put("mission_condition", user.getMission_condition());
//                        data1.put("note", user.getNote());
//
//
//                        //sweeponesweepdata.add(data1);
//
//                    all.put("data", data1);
//                }
//
//            }
//
//
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        all.put("result", result);
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(all));
//    }
//
//
//    /*
//    *2.31修改密码app
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=changepassapp")
//    public void changepassapp(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        changepassappDAOImpl changepassappDAO = (changepassappDAOImpl) context.getBean("changepassappDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//        try {
//
//            String phone = request.getParameter("phone");
//            String old_password = request.getParameter("password1");
//            String new_password = request.getParameter("password2");
//
//
//
//            if (phone == null || phone.isEmpty()||old_password == null || old_password.isEmpty()||new_password == null || new_password.isEmpty()) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//                // 获取用户的数据
//                changepassappDO changepassappDO = new changepassappDO();
//                changepassappDO.setPhone(phone);
//
//                changepassappDO userrrr = changepassappDAO.get(changepassappDO);
//                /*System.out.println(user);*/
//
//                if (userrrr == null) {
//                    result.put("errMsg", "用户名输入错误");
//                    result.put("result","10002");
//                } else {
//                    // 检查用户密码是否正确
//                    if (old_password.equals(userrrr.getPassword())) {
//
//                        changepassappDO changepassappDO1 = new changepassappDO();
//                        changepassappDO1.setPhone(phone);
//                        changepassappDO1.setPassword(new_password);
//
//                        changepassappDAO.update(changepassappDO1);
//                        result.put("errMsg", "保存成功！");
//                        result.put("result","10000");
//                        System.out.println("change-password-finish");
//
//                    } else {
//                        result.put("errMsg", "密码错误");
//                        result.put("result","10003");
//                        //wrong
//                    }
//                }
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//    /*
//    *2.61请求班组人员分组
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=postteam")
//    public void postteam(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        postteamDAOImpl postteamDAO = (postteamDAOImpl) context.getBean("postteamDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//        try {
//
//            String phone = request.getParameter("phone");
//
//
//
//
//            if (phone == null || phone.isEmpty()) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//                // 获取用户的数据
//                List<String> postteam = postteamDAO.list();
//                /*System.out.println(user);*/
//
//                if (postteam == null) {
//                    result.put("errMsg", "未找到人员信息");
//                    result.put("result","10002");
//                } else {
//                    Map map = CollectionTools.classifyList(postteam,"team");
//                    result.put("data",map);
//
//
//
//                        result.put("errMsg", "查询成功");
//                        result.put("result","10000");
//                        System.out.println("post-team-finish");
//
//                }
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//
//    /*
//    *1.21 worker's opinion feedback
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=opinionfeedback")
//    public void opinionfeedback(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        opinionfeedbackDAOImpl opinionfeedbackDAO = (opinionfeedbackDAOImpl) context.getBean("opinionfeedbackDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//
//        try {
//
//            String phone = request.getParameter("phone");
//            String yijian = request.getParameter("yijian");
//
//            if (phone == null || phone.isEmpty() ) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//
//
//
//                // 获取用户的数据
//                opinionfeedbackDO opinionfeedbackDO = new opinionfeedbackDO();
//                opinionfeedbackDO.setPhone(phone);
//                opinionfeedbackDO.setYijian(yijian);
//                opinionfeedbackDAO.insert(opinionfeedbackDO);
//                result.put("errMsg", "保存成功！");
//                result.put("result","10000");
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//    /*
//    *1.22 worker's opinion feedback
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=checkcode")
//    public void checkcode(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        checkcodeDAOImpl checkcodeDAO = (checkcodeDAOImpl) context.getBean("checkcodeDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//
//        try {
//
//            String phone = request.getParameter("phone");
//
//            if (phone == null || phone.isEmpty() ) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//                int random_code_int = (int)((Math.random()*9+1)*1000);
//                String random_code = String.valueOf(random_code_int);
//
//                // 获取用户的数据
//                checkcodeDO checkcodeDO = new checkcodeDO();
//                checkcodeDO.setPhone(phone);
//                checkcodeDO.setCheck_code(random_code);
//                checkcodeDAO.update(checkcodeDO);
//                result.put("errMsg", "保存成功！");
//                result.put("result","10000");
//
//                SendMessage.sendMessage(phone,"您的随机验证码为："+ random_code);
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//    /*
//    *2.71 图片上传
//    *
//    * */
//    @RequestMapping(method = { RequestMethod.POST }, params = "action=headpic")
//    public void headpic(HttpServletRequest request, HttpServletResponse response) {
//
//        //ApplicationContext context = getContext();
//        headpicDAOImpl headpicDAO = (headpicDAOImpl) context.getBean("headpicDAO");
//
//        JSONObject result = new JSONObject();
//        result.put("result", 10001);
//
//        try {
//            String phone = request.getParameter("phone");
//            String pic_name = request.getParameter("pic_name");
//
//
//            /*System.out.println(phone);*/
//
//            if (pic_name == null || pic_name.isEmpty()) {
//                result.put("errMsg", "输入参数有误");
//                result.put("result","10001");
//            } else {
//                /*System.out.println("0000");*/
//
//
//                    //weichat
//                    /*System.out.println("1234");*/
//                    String pic_namereal = pic_name.substring(9,pic_name.length());
//
//                    String pic_loc = "\\WEB-INF\\upload\\" + pic_namereal;
//    /*                    System.out.println(pic_name);
//
//                    System.out.println(mission_id);*/
//
//                    // insert
//                headpicDO headpicDO = new headpicDO();
//                headpicDO.setPhone(phone);
//                headpicDO.setHead_pic(pic_loc);
//
//
//                headpicDAO.update(headpicDO);
//                    System.out.println("worker-pic-info-update-ok");
//                    result.put("result", 10000);
//                    result.put("errMsg", "成功");
//
//            }
//        } catch (Exception e) {
//            result.put("essMsg", e.getMessage());
//
//        }
//        // 输出结果
//        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
//    }
//
//}
