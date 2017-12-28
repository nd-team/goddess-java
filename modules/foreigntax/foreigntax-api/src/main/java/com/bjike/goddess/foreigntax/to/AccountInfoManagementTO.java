package com.bjike.goddess.foreigntax.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

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
    @NotBlank(message = "公司不能为空",groups = {ADD.class, EDIT.class})
    private String company;

    /**
     * 时间
     */
    @NotBlank(message = "时间不能为空",groups = {ADD.class, EDIT.class})
    private String time;
    /**
     * 资料名称
     */
    @NotBlank(message = "资料名称不能为空",groups = {ADD.class, EDIT.class})
    private String dataName;
    /**
     * 办税员
     */
    @NotBlank(message = "办税员不能为空",groups = {ADD.class, EDIT.class})
    private String taxAgent;

    /**
     * 跟进人
     */
    @NotBlank(message = "跟进人不能为空",groups = {ADD.class, EDIT.class})
    private String followUpPeople;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getTaxAgent() {
        return taxAgent;
    }

    public void setTaxAgent(String taxAgent) {
        this.taxAgent = taxAgent;
    }

    public String getFollowUpPeople() {
        return followUpPeople;
    }

    public void setFollowUpPeople(String followUpPeople) {
        this.followUpPeople = followUpPeople;
    }
}