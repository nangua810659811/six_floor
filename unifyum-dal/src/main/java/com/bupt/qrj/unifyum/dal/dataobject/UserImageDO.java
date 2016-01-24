/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dataobject;

import java.util.Date;

/**
 * @author renjun.qrj  2015年12月29日:上午7:30:12
 * com.bupt.qrj.unifyum.dal.dataobject.UserImageDO
 * unifyum-dal
 * 用途: 
 *
 */
public class UserImageDO {

    private Long   id;

    /**用户名*/
    private String userName;

    /**录入时间*/
    private Date   recordTime;

    /**base64 的字符串 */
    private String image;

    /**图像计算参数1*/
    private String moisten;

    /**图像计算参数2*/
    private String blood;

    /**图像计算参数3*/
    private String color;

    /**图像计算参数4*/
    private String texture;

    /**图像计算参数5*/
    private String satin;

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
     * @return the recordTime
     */
    public Date getRecordTime() {
        return recordTime;
    }

    /**
     * @param recordTime the recordTime to set
     */
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the moisten
     */
    public String getMoisten() {
        return moisten;
    }

    /**
     * @param moisten the moisten to set
     */
    public void setMoisten(String moisten) {
        this.moisten = moisten;
    }

    /**
     * @return the blood
     */
    public String getBlood() {
        return blood;
    }

    /**
     * @param blood the blood to set
     */
    public void setBlood(String blood) {
        this.blood = blood;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the texture
     */
    public String getTexture() {
        return texture;
    }

    /**
     * @param texture the texture to set
     */
    public void setTexture(String texture) {
        this.texture = texture;
    }

    /**
     * @return the satin
     */
    public String getSatin() {
        return satin;
    }

    /**
     * @param satin the satin to set
     */
    public void setSatin(String satin) {
        this.satin = satin;
    }

}
