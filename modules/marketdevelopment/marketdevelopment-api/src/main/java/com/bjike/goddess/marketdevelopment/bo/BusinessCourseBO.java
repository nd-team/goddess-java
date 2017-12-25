package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

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
     * 业务方向编号
     */
    private String businessNum;

    /**
     * 业务方向分类
     */
    private String businessType;

    /**
     * 业务方向科目集合
     */
    private List<BusinessSubjectBO> businessSubjectVOs;

    public String getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(String businessNum) {
        this.businessNum = businessNum;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public List<BusinessSubjectBO> getBusinessSubjectVOs() {
        return businessSubjectVOs;
    }

    public void setBusinessSubjectVOs(List<BusinessSubjectBO> businessSubjectVOs) {
        this.businessSubjectVOs = businessSubjectVOs;
    }
}