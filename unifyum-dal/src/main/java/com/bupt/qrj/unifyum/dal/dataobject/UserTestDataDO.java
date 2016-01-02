/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dataobject;

import java.util.Date;

/**
 * @author renjun.qrj  2016年1月2日:下午9:09:39
 * com.bupt.qrj.unifyum.dal.dataobject.UserTestDataDO
 * unifyum-dal
 * 用途: 
 *
 */
public class UserTestDataDO {
    private Long   id;

    private String userName;

    private String testData;

    private Date   gmtCreated;

    private Date   gmtModified;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the testData
     */
    public String getTestData() {
        return testData;
    }

    /**
     * @param testData the testData to set
     */
    public void setTestData(String testData) {
        this.testData = testData;
    }

    /**
     * @return the gmtCreated
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * @param gmtCreated the gmtCreated to set
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * @return the gmtModified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * @param gmtModified the gmtModified to set
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

}
