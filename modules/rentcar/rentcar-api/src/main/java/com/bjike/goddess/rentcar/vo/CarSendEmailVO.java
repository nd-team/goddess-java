package com.bjike.goddess.rentcar.vo;

/**
 * 邮箱发送
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-25 09:50 ]
 * @Description: [ 邮箱发送 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CarSendEmailVO {

    /**
     * id
     */
    private String id;

    /**
     * 项目组Id
     */
    private String projectManagerId;
    /**
     * 岗位Id
     */
    private String[] positionNameId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(String projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    public String[] getPositionNameId() {
        return positionNameId;
    }

    public void setPositionNameId(String[] positionNameId) {
        this.positionNameId = positionNameId;
    }
}