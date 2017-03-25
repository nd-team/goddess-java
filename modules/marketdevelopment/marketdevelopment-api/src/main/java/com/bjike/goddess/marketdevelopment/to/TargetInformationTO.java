package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 确定目标信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:12 ]
 * @Description: [ 确定目标信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TargetInformationTO extends BaseTO {

    /**
     * 地区
     */
    @NotNull(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 业务类型
     */
    @NotNull(message = "业务类型不能为空",groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 业务方向科目
     */
    @NotNull(message = "业务方向科目不能为空",groups = {ADD.class, EDIT.class})
    private String course;

    /**
     * 客户基本信息
     */
    @NotNull(message = "客户基本信息不能为空",groups = {ADD.class, EDIT.class})
    private String customer_id;

    /**
     * 备注
     */
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}