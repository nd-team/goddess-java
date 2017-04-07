package com.bjike.goddess.attainment.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * 调研方式表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:51 ]
 * @Description: [ 调研方式表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AttainmentWayVO {

    /**
     * id
     */
    private String id;
    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否使用问卷调查
     */
    private Boolean employ;

    /**
     * 状态
     */
    private Status status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isEmploy() {
        return employ;
    }

    public void isEmploy(Boolean employ) {
        this.employ = employ;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}