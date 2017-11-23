package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 业务方向科目
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:21 ]
 * @Description: [ 业务方向科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessCourseTO extends BaseTO {

    /**
     * 业务方向分类
     */
    @NotNull(message = "业务方向分类不能为空",groups = {ADD.class, EDIT.class})
    private String businessType;


    /**
     * 业务方向科目
     */
    @NotNull(message = "业务方向科目不能为空",groups = {ADD.class, EDIT.class})
    private String course;

    /**
     * 所属类别
     */
    @NotNull(message = "所属类别不能为空",groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 可以做的具体业务
     */
    private String business;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
}