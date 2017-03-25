package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 业务方向科目业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:21 ]
 * @Description: [ 业务方向科目业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessCourseBO extends BaseBO {

    /**
     * 业务类型ID
     */
    private String type_id;

    /**
     * 业务类型
     */
    private String typeName;

    /**
     * 业务方向科目
     */
    private String course;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Status status;


    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}