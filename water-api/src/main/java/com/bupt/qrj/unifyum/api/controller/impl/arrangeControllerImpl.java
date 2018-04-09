/**
 * 
 */
package com.bupt.qrj.unifyum.api.controller.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bupt.qrj.unifyum.api.controller.arrangeController;
import com.bupt.qrj.unifyum.dal.dao.impl.*;
import com.bupt.qrj.unifyum.dal.dataobject.*;
import com.bupt.qrj.unifyum.util.http.HttpOutUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;

@Controller
@RequestMapping("/arrange.req")
public class arrangeControllerImpl implements arrangeController {

	public static ApplicationContext getContext() {
		// 获得Spring中定义的Bean实例，两个以上加 new String[]
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/spring/unifyum-dal-dao.xml", "META-INF/spring/unifyum-dal-db.xml" });
		return context;
	}
	
	ApplicationContext context = getContext();


	/*
	*1.4 insert
	*
	* */
	@RequestMapping(method = { RequestMethod.POST }, params = "action=arrangeinsert")
	public void arrangeinsert(HttpServletRequest request, HttpServletResponse response) {

		//ApplicationContext context = getContext();
        arrangeinsertDAOImpl arrangeinsertDAO = (arrangeinsertDAOImpl) context.getBean("arrangeinsertDAO");

		JSONObject result = new JSONObject();
		result.put("result", 10001);

		
		try {

/*			 * Map map = JSON.parseObject(body, Map.class);
			 * System.out.println(map.get("mobileNo")); String userName =
			 * (String) map.get("mobileNo"); String password = (String)
			 * map.get("password");*/
//            System.out.println("0");
            //map = new HashMap();
//            String map = request.getParameter("data");
            //Map map1 = new HashMap();
            //map1 = request.getParameterMap();
//            ServletInputStream aaa = request.getInputStream();
//            System.out.println(aaa);
            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb);
            String s1=sb.toString();
            JSONObject obj =JSON.parseObject(s1);
            System.out.println(obj.toString());
            String getjsay=obj.getString("data");
            JSONArray obj1 =JSON.parseArray(getjsay);
//            Map<String,String[]> map1=request.getParameterMap();

//            String str = JSON.toJSONString(map1);
//            System.out.println(str);
//            System.out.println(map);
//            String[] str = (String[])map.get("data");
//            System.out.println("2");
//            System.out.println(str[0]+"-"+str[1]);
//            System.out.println("3");

//            String arrayStr = request.getParameter("data");
//            System.out.println(arrayStr);
//            JSONObject json = (JSONObject) JSONObject.parse(sb);

//            JSONObject getJsonObj2 = (JSONObject) JSONObject.toJSON(map1);
//            JSONObject getJsonArray= JSONArray.parseObject(s1);
            //System.out.println(map.toString());
//            net.sf.json.JSONObject getJsonObj1 = getJsonArray.getJSONObject(0);
            //JSONArray getJsonArray = obj.getJSONArray("data");
            System.out.println(obj1.toString());
            //JSONObject obj = new JSONObject(request.getParameterValues("data"));
            List<Object> listinsert = new ArrayList<Object>();
            List<Object> listupdate = new ArrayList<Object>();
            for(int i=0;i<obj1.size();i++){
                JSONObject getJsonObj = obj1.getJSONObject(i);
                String time11 = getJsonObj.getString("startTime");
                String time22 = getJsonObj.getString("endTime");
                String set_worker_time = obj.getString("set_worker_time");
                String worker_name = obj.getString("worker_name");
                String flag = obj.getString("flag");
                String time1 = time11 + " 00:00:00";
                String time2 = time22 + " 00:00:00";

                /*Map<String, String> map = new HashMap<String, String>();
                map.put("time1", time1);
                map.put("time2", time2);
                map.put("set_worker_time", set_worker_time);
                map.put("worker_name", worker_name);
                map.put("flag", flag);*/
                //System.out.println(time1);
                if(flag.equals("1")){
//                    listinsert.add(i, map);
                    arrangeinsertDO arrangeinsertDO = new arrangeinsertDO();
                    arrangeinsertDO.setTime_left(time1);
                    arrangeinsertDO.setTime_right(time2);
                    arrangeinsertDO.setSet_time(set_worker_time);
                    arrangeinsertDO.setWorker_name(worker_name);
                    arrangeinsertDO.setType(flag);
                    arrangeinsertDAO.insert(arrangeinsertDO);

                }else{
//                    listupdate.add(i, map);
                    arrangeinsertDO arrangeinsertDO = new arrangeinsertDO();
                    arrangeinsertDO.setTime_left(time1);
                    arrangeinsertDO.setTime_right(time2);
                    arrangeinsertDO.setSet_time(set_worker_time);
                    arrangeinsertDO.setWorker_name(worker_name);
                    arrangeinsertDO.setType(flag);
                    arrangeinsertDAO.update(arrangeinsertDO);

                }
                System.out.println("arrange-insert-ok");
                result.put("result", 10000);
                result.put("errMsg", "成功");

            }

        } catch (Exception e) {
			result.put("essMsg", e.getMessage());

		}
		// 输出结果
		HttpOutUtil.outData(response, JSONObject.toJSONString(result));
	}

    /*
    *1.1 seek all
    *
    * */
    @RequestMapping(method = { RequestMethod.POST }, params = "action=arrangeseek")
    public void arrangeseek(HttpServletRequest request, HttpServletResponse response) {

        //ApplicationContext context = getContext();
        arrangeseekDAOImpl arrangeseekDAO = (arrangeseekDAOImpl) context.getBean("arrangeseekDAO");

        JSONObject result = new JSONObject();
        result.put("result", 10001);
        JSONObject data = new JSONObject();
        int i =0;
        try {


            String time1 = request.getParameter("time1");
            String time2 = request.getParameter("time2");


            String timemmin;
            String timemmax;
                timemmin = time1 +" 00:00:00";
                timemmax = time2 +" 00:00:00";

            System.out.println(timemmax);
            System.out.println(timemmin);



                List<arrangeseekmisDO> arrangeseekmis = arrangeseekDAO.listarrangeseekmis(timemmin,timemmax);
                if (arrangeseekmis.isEmpty()) {
                    result.put("result", 10002);
                    result.put("errMsg", "没有数据");
                    System.out.println("数据库为空");
                } else {
                    result.put("result", 10000);
                    result.put("errMsg", "成功");
                    System.out.println("arrange-seek");
                    ArrayList<JSONObject> arrangeseekmisdata = new ArrayList<JSONObject>();

                    for (arrangeseekmisDO arrangeseekmisDO : arrangeseekmis) {
                        JSONObject data1 = new JSONObject();
                        data1.put("mission_name", arrangeseekmisDO.getMission());
                        data1.put("mission_condition", arrangeseekmisDO.getMission_condition());
                        data1.put("set_mission_time", arrangeseekmisDO.getSet_start_time());
                        i++;


                        arrangeseekmisdata.add(data1);
                    }

                    data.put("data", arrangeseekmisdata);
                }



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        data.put("count", i);
        data.put("result", result);
        // 输出结果
        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
    }


    @RequestMapping(method = { RequestMethod.POST }, params = "action=arrangeseekall")
    public void arrangeseekall(HttpServletRequest request, HttpServletResponse response) {

        //ApplicationContext context = getContext();
        arrangeseekDAOImpl arrangeseekDAO = (arrangeseekDAOImpl) context.getBean("arrangeseekDAO");

        JSONObject result = new JSONObject();
        result.put("result", 10001);
        JSONObject data = new JSONObject();
        int i =0;
        try {


            String time1 = request.getParameter("time1");
            String time2 = request.getParameter("time2");


            String timemmin;
            String timemmax;
            timemmin = time1 +" 00:00:00";
            timemmax = time2 +" 00:00:00";

            System.out.println(timemmax);
            System.out.println(timemmin);

            List<arrangeseekDO> arrangeseek = arrangeseekDAO.listarrangeseek(timemmin);
            if (arrangeseek.isEmpty()) {
                result.put("result", 10002);
                result.put("errMsg", "没有数据");
                System.out.println("数据库为空");
            } else {
                result.put("result", 10000);
                result.put("errMsg", "成功");
                System.out.println("arrange-seek");
                ArrayList<JSONObject> arrangeseekdata = new ArrayList<JSONObject>();

                for (arrangeseekDO arrangeseekDO : arrangeseek) {
                    JSONObject data1 = new JSONObject();
                    data1.put("worker_name", arrangeseekDO.getWorker_name());
                    data1.put("team", arrangeseekDO.getTeam());
                    data1.put("post", arrangeseekDO.getPost());
                    data1.put("flag", arrangeseekDO.getType());
                    data1.put("colorflage", arrangeseekDO.getWork_type());
                    data1.put("set_worker_time", arrangeseekDO.getSet_time());
                    i++;

                    arrangeseekdata.add(data1);
                }





                    data.put("data", arrangeseekdata);


            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        data.put("count", i);
        data.put("result", result);
        // 输出结果
        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
    }




    /*
*1.1 seek per mis
*
* */
    @RequestMapping(method = { RequestMethod.POST }, params = "action=arrangepermis")
    public void arrangepermis(HttpServletRequest request, HttpServletResponse response) {

        //ApplicationContext context = getContext();
        arrangepermisDAOImpl arrangepermisDAO = (arrangepermisDAOImpl) context.getBean("arrangepermisDAO");

        JSONObject result = new JSONObject();
        result.put("result", 10001);
        JSONObject data = new JSONObject();
        int i =0;
        try {


            String time1 = request.getParameter("time1");
            String time2 = request.getParameter("time2");
            String set_start_time_code = request.getParameter("set_worker_time");
            String worker_name = request.getParameter("worker_name");
            String timemmin;
            String timemmax;
            timemmin = time1 +" 00:00:00";
            timemmax = time2 +" 00:00:00";

            System.out.println(timemmax);
            System.out.println(timemmin);

            List<arrangepermisDO> arrangepermis = arrangepermisDAO.listarrangepermis(worker_name,timemmin,timemmax,set_start_time_code);
            if (arrangepermis.isEmpty()) {
                result.put("result", 10002);
                result.put("errMsg", "没有数据");
                System.out.println("数据库为空");
            } else {
                result.put("result", 10000);
                result.put("errMsg", "成功");
                System.out.println("arrange-seek");
                ArrayList<JSONObject> arrangepermisdata = new ArrayList<JSONObject>();

                for (arrangepermisDO arrangepermisDO : arrangepermis) {
                    JSONObject data1 = new JSONObject();
                    data1.put("mission_name", arrangepermisDO.getMission());
                    data1.put("mission_condition", arrangepermisDO.getMission_condition());


                    i++;


                    arrangepermisdata.add(data1);
                }
                    data.put("data", arrangepermisdata);


            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        data.put("count", i);
        data.put("result", result);
        // 输出结果
        HttpOutUtil.outData(response, JSONObject.toJSONString(data));
    }




    @RequestMapping(method = { RequestMethod.POST }, params = "action=MissionReturn")
    public void MissionReturn(HttpServletRequest request, HttpServletResponse response) {

//    	ApplicationContext context=getContext();
        MissionReturnArrDAOImpl MissionReturnArrDAO=(MissionReturnArrDAOImpl) context.getBean("MissionReturnArrDAO");

        JSONObject result = new JSONObject();
        result.put("result",10001);
        System.out.println("11");

        try {

            String MissionId = request.getParameter("mission_name");

            if (MissionId==null||MissionId.isEmpty()) {
                result.put("errMsg","输入参数有误");
            } else {
                List<String> missionReturn = MissionReturnArrDAO.list(MissionId);
                if(missionReturn.isEmpty()){
                    result.put("errMsg","请联系重新设置页面");
                }else{
                    for(String mr : missionReturn){

                        String temp =  MissionReturnArrDAO.get(MissionId,mr);
                        MissionReturnDO missionReturnDO = new MissionReturnDO();
                        missionReturnDO.setColumn_key(mr);
                        missionReturnDO.setNote_content(temp);
                        MissionReturnArrDAO.update(missionReturnDO);
                    }

                    ArrayList<JSONObject> eventData = new ArrayList<JSONObject>();
                    System.out.println("1111");
                    String event_id = MissionReturnArrDAO.get_event(MissionId);
                    List<EventInfoDO> eventInfo = MissionReturnArrDAO.Event_list(event_id);
//                    System.out.println(event_id);
//                    for(EventInfoDO EventInfoDO : eventInfo){
//                        JSONObject data1 = new JSONObject();
//                        data1.put("event_ID",EventInfoDO.getEvent_id());
//                        data1.put("event_name",EventInfoDO.getEvent_name());
//                        data1.put("font_color",EventInfoDO.getFont_color());
//                        data1.put("font_size",EventInfoDO.getFont_size());
////                        data1.put("work",JSONObject.toJSONString(EventInfoDO.getAdditions()));
//                        data1.put("work",EventInfoDO.getAdditions());
//                        System.out.println(EventInfoDO.getAdditions());
//                        eventData.add(data1);
//                    }

                    ArrayList<JSONObject> noteData = new ArrayList<JSONObject>();
                    List<MissionReturnDO> noteInfo =MissionReturnArrDAO.Return_list("2","0");
                    for(MissionReturnDO MissionReturnDO : noteInfo){
                        JSONObject data2 = new JSONObject();
                        data2.put("note_name",MissionReturnDO.getNote_name());
                        data2.put("note_content",MissionReturnDO.getNote_content());
                        data2.put("font_color",MissionReturnDO.getFont_color());
                        data2.put("font_size",MissionReturnDO.getFont_size());
                        noteData.add(data2);
                    }

//                    MissionReturnDO task_info =MissionReturnArrDAO.get_first();
                    JSONObject data = new JSONObject();
//                    data.put("task_ID",MissionId);
//                    data.put("font_size",task_info.getFont_size());
//                    data.put("font_color",task_info.getFont_color());
                    data.put("format",noteInfo);
//                    data.put("event",eventData);

                    result.put("data",data);
                    result.put("essMsg","10000");
                    result.put("result","10000");
                }
            }
        } catch (Exception e) {
            result.put("essMsg", e.getMessage());

        }
        // 输出结果
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
    }



}
