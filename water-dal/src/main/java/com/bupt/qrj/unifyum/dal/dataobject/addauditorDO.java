/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dataobject;

/**
 * @author renjun.qrj 2015年10月31日:下午5:52:08 com.bupt.qrj.dataobject.UserMeta
 *
 *
 */
public class addauditorDO {


    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditor_opinion() {
        return auditor_opinion;
    }

    public void setAuditor_opinion(String auditor_opinion) {
        this.auditor_opinion = auditor_opinion;
    }

    public String getAuditor_time() {
        return auditor_time;
    }

    public void setAuditor_time(String auditor_time) {
        this.auditor_time = auditor_time;
    }


    private String auditor;
    private String auditor_opinion;
    private String auditor_time;

    public String getMission_id() {
        return mission_id;
    }

    public void setMission_id(String mission_id) {
        this.mission_id = mission_id;
    }

    private String mission_id;



}
