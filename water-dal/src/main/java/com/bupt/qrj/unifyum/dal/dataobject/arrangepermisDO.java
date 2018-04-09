/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dataobject;

/**
 * @author renjun.qrj 2015年10月31日:下午5:52:08 com.bupt.qrj.dataobject.UserMeta
 *
 *
 */
public class arrangepermisDO {


    public String getSet_start_time() {
        return set_start_time;
    }

    public void setSet_start_time(String set_start_time) {
        this.set_start_time = set_start_time;
    }

    public String getMission_set_time() {
        return mission_set_time;
    }

    public void setMission_set_time(String mission_set_time) {
        this.mission_set_time = mission_set_time;
    }

    public String getMission_condition() {
        return mission_condition;
    }

    public void setMission_condition(String mission_condition) {
        this.mission_condition = mission_condition;
    }


    private String set_start_time;
    private String mission_set_time;
    private String mission_condition;

    private String worker_name;

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    private String mission;

}
