package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 业务对象业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-28 02:54 ]
 * @Description: [ 业务对象业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessCompanyBO extends BaseBO {

    /**
     * 公司名称编号
     */
    private String companyNum;

    /**
     * 业务对象-公司名称
     */
    private String company;


    /**
     * 可发展业务-科目合计
     */
    private Integer sum;


    /**
     * 可发展业务方向科目集合
     */
    List<BusinessCompanySubBO> businessCompanySubVOs;


    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public List<BusinessCompanySubBO> getBusinessCompanySubVOs() {
        return businessCompanySubVOs;
    }

    public void setBusinessCompanySubVOs(List<BusinessCompanySubBO> businessCompanySubVOs) {
        this.businessCompanySubVOs = businessCompanySubVOs;
    }
}