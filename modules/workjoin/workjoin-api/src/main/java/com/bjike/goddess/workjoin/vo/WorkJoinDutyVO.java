package com.bjike.goddess.workjoin.vo;

/**
 * 工作交接责任义务表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:20 ]
 * @Description: [ 工作交接责任义务表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkJoinDutyVO {

    /**
     * id
     */
    private String id;
    /**
     * 对象
     */
    private String object;

    /**
     * 责任与义务
     */
    private String duty;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}