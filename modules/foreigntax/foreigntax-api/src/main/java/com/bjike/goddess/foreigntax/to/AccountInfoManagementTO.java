package com.bjike.goddess.foreigntax.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 外账资料管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:52 ]
 * @Description: [ 外账资料管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountInfoManagementTO extends BaseTO {

    /**
     * 公司
     */
    private String company;

    /**
     * 所属月份
     */
    private String month;

    /**
     * 资料名称
     */
    private String dataName;

    /**
     * 跟进人
     */
    private String followUpPeople;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getFollowUpPeople() {
        return followUpPeople;
    }

    public void setFollowUpPeople(String followUpPeople) {
        this.followUpPeople = followUpPeople;
    }
}