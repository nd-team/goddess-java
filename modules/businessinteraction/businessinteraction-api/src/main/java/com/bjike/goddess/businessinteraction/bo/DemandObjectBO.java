package com.bjike.goddess.businessinteraction.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 获取合作对象联系方式
 * @Author: [tanghaixiang]
 * @Date: [2017-03-29 10:13]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DemandObjectBO extends BaseBO{

    /**
     * 符合对象公司名
     */
    private String companyName;

    /**
     * 符合对象公司所在地区
     */
    private String area;
    /**
     * 对象公司业务方向
     */
    private String businessTarget;
    /**
     * 对象公司专业
     */
    private String profession;
    /**
     * 对象公司规模
     */
    private String size;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessTarget() {
        return businessTarget;
    }

    public void setBusinessTarget(String businessTarget) {
        this.businessTarget = businessTarget;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
