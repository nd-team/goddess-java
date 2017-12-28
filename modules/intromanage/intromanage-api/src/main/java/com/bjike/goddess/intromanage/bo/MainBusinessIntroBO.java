package com.bjike.goddess.intromanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 主业介绍业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:41 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MainBusinessIntroBO extends BaseBO {

    /**
     * 公司记录id
     */
    private String firmId;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 项目科目
     */
    private String projectSubject;

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getProjectSubject() {
        return projectSubject;
    }

    public void setProjectSubject(String projectSubject) {
        this.projectSubject = projectSubject;
    }
}