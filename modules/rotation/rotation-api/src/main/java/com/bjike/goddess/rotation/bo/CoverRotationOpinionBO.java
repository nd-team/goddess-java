package com.bjike.goddess.rotation.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 岗位轮换自荐意见业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 04:04 ]
 * @Description: [ 岗位轮换自荐意见业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CoverRotationOpinionBO extends BaseBO {

    /**
     * 岗位轮换自荐
     */
    private String coverId;

    /**
     * 姓名
     */
    private String username;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组部门
     */
    private String department;

    /**
     * 岗位
     */
    private String position;

    /**
     * 意见
     */
    private String opinion;

    /**
     * 时间
     *
     * @return
     */
    private String time;


    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}