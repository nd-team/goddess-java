package com.bjike.goddess.competitorsmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 组织结构详情业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-14 02:33 ]
 * @Description: [ 组织结构详情业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OrganizationSDBO extends BaseBO {

    /**
     * 企业部门结构
     */
    private String CDstructure;

    /**
     * 客户名称
     */
    private String CustomerName;

    /**
     * 负责事项
     */
    private String affairsOR;

    /**
     * 主管部门
     */
    private String departmentIC;

    /**
     * 主管名称
     */
    private String supervisorName;

    /**
     * 主管职权
     */
    private String Spowers;

    /**
     * 分管部门
     */
    private String departmentICO;

    /**
     * 负责人名称
     */
    private String ICpersonName;

    /**
     * 分管职权
     */
    private String DOpower;

    /**
     * 接口人
     */
    private String INperson;


    public String getCDstructure() {
        return CDstructure;
    }

    public void setCDstructure(String CDstructure) {
        this.CDstructure = CDstructure;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getAffairsOR() {
        return affairsOR;
    }

    public void setAffairsOR(String affairsOR) {
        this.affairsOR = affairsOR;
    }

    public String getDepartmentIC() {
        return departmentIC;
    }

    public void setDepartmentIC(String departmentIC) {
        this.departmentIC = departmentIC;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getSpowers() {
        return Spowers;
    }

    public void setSpowers(String Spowers) {
        this.Spowers = Spowers;
    }

    public String getDepartmentICO() {
        return departmentICO;
    }

    public void setDepartmentICO(String departmentICO) {
        this.departmentICO = departmentICO;
    }

    public String getICpersonName() {
        return ICpersonName;
    }

    public void setICpersonName(String ICpersonName) {
        this.ICpersonName = ICpersonName;
    }

    public String getDOpower() {
        return DOpower;
    }

    public void setDOpower(String DOpower) {
        this.DOpower = DOpower;
    }

    public String getINperson() {
        return INperson;
    }

    public void setINperson(String INperson) {
        this.INperson = INperson;
    }

    @Override
    public String toString() {
        return "OrganizationSDBO{" +
                "CDstructure='" + CDstructure + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", affairsOR='" + affairsOR + '\'' +
                ", departmentIC='" + departmentIC + '\'' +
                ", supervisorName='" + supervisorName + '\'' +
                ", Spowers='" + Spowers + '\'' +
                ", departmentICO='" + departmentICO + '\'' +
                ", ICpersonName='" + ICpersonName + '\'' +
                ", DOpower='" + DOpower + '\'' +
                ", INperson='" + INperson + '\'' +
                '}';
    }
}