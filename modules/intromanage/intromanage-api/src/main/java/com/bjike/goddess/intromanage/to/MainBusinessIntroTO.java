package com.bjike.goddess.intromanage.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 主业介绍
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:41 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MainBusinessIntroTO extends BaseTO {

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