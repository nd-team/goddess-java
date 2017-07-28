package com.bjike.goddess.competitormanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.competitormanage.enums.BusinessType;

/**
 * 竞争对手信息业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-03-21 04:49 ]
 * @Description: [ 竞争对手信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OrganizationBO extends BaseBO {
    /**
     * 主管部门
     */
    private String directDepartment;

    /**
     * 主管名称
     */
    private String director;

    /**
     * 主管职权
     */
    private String directAuthority;

    /**
     * 负责事项
     */
    private String chargeItems;

    /**
     * 客户信息表序号
     */
    private String customerInfoCode;

    /**
     * 分管部门
     */
    private String branchedDepartment;

    /**
     * 负责人名称
     */
    private String chargeMan;

    /**
     * 负责人职权
     */
    private String chargeManAuthority;

    /**
     * 接口人
     */
    private String interfaceMan;


    public String getDirectDepartment() {
        return directDepartment;
    }

    public void setDirectDepartment(String directDepartment) {
        this.directDepartment = directDepartment;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirectAuthority() {
        return directAuthority;
    }

    public void setDirectAuthority(String directAuthority) {
        this.directAuthority = directAuthority;
    }

    public String getChargeItems() {
        return chargeItems;
    }

    public void setChargeItems(String chargeItems) {
        this.chargeItems = chargeItems;
    }

    public String getCustomerInfoCode() {
        return customerInfoCode;
    }

    public void setCustomerInfoCode(String customerInfoCode) {
        this.customerInfoCode = customerInfoCode;
    }

    public String getBranchedDepartment() {
        return branchedDepartment;
    }

    public void setBranchedDepartment(String branchedDepartment) {
        this.branchedDepartment = branchedDepartment;
    }

    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
    }

    public String getChargeManAuthority() {
        return chargeManAuthority;
    }

    public void setChargeManAuthority(String chargeManAuthority) {
        this.chargeManAuthority = chargeManAuthority;
    }

    public String getInterfaceMan() {
        return interfaceMan;
    }

    public void setInterfaceMan(String interfaceMan) {
        this.interfaceMan = interfaceMan;
    }

}