package com.bjike.goddess.competitormanage.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.competitormanage.enums.BusinessType;

/**
 * 竞争对手信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-12 11:35 ]
 * @Description: [ 竞争对手信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompetitorExcel extends BaseTO {

    /**
     * 市场信息收集序号
     */
    @ExcelHeader(name = "市场信息收集序号", notNull = false)
    private String markInfoCode;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称", notNull = true)
    private String project;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 竞争对手名称
     */
    @ExcelHeader(name = "竞争对手名称", notNull = true)
    private String competitor;

    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型", notNull = true)
    private BusinessType businessType;

    /**
     * 基本资料描述
     */
    @ExcelHeader(name = "基本资料描述", notNull = true)
    private String basicInfoDesc;

    /**
     * 电话
     */
    @ExcelHeader(name = "电话", notNull = true)
    private String phoneNum;

    /**
     * 地址
     */
    @ExcelHeader(name = "地址", notNull = true)
    private String address;

    /**
     * 组织结构
     */
    @ExcelHeader(name = "组织结构", notNull = true)
    private String organization;

    /**
     * 级别定义方式
     */
    @ExcelHeader(name = "级别定义方式", notNull = true)
    private String leveldefinition;

    /**
     * 其他备注
     */
    @ExcelHeader(name = "其他备注", notNull = false)
    private String remark;

    /**
     * 主管部门
     */
    @ExcelHeader(name = "主管部门", notNull = true)
    private String directDepartment;

    /**
     * 主管名称
     */
    @ExcelHeader(name = "主管名称", notNull = true)
    private String director;

    /**
     * 主管职权
     */
    @ExcelHeader(name = "主管职权", notNull = true)
    private String directAuthority;

    /**
     * 负责事项
     */
    @ExcelHeader(name = "负责事项", notNull = true)
    private String chargeItems;

    /**
     * 客户信息表序号
     */
    @ExcelHeader(name = "客户信息表序号", notNull = true)
    private String customerInfoCode;

    /**
     * 分管部门
     */
    @ExcelHeader(name = "分管部门", notNull = true)
    private String branchedDepartment;

    /**
     * 负责人名称
     */
    @ExcelHeader(name = "负责人名称", notNull = true)
    private String chargeMan;

    /**
     * 负责人职权
     */
    @ExcelHeader(name = "负责人职权", notNull = true)
    private String chargeManAuthority;

    /**
     * 接口人
     */
    @ExcelHeader(name = "接口人", notNull = true)
    private String interfaceMan;


    public String getMarkInfoCode() {
        return markInfoCode;
    }

    public void setMarkInfoCode(String markInfoCode) {
        this.markInfoCode = markInfoCode;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompetitor() {
        return competitor;
    }

    public void setCompetitor(String competitor) {
        this.competitor = competitor;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getBasicInfoDesc() {
        return basicInfoDesc;
    }

    public void setBasicInfoDesc(String basicInfoDesc) {
        this.basicInfoDesc = basicInfoDesc;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLeveldefinition() {
        return leveldefinition;
    }

    public void setLeveldefinition(String leveldefinition) {
        this.leveldefinition = leveldefinition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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