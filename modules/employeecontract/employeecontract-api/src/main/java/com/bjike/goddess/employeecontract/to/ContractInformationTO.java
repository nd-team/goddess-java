package com.bjike.goddess.employeecontract.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.employeecontract.enums.AgreeProbationTime;
import com.bjike.goddess.employeecontract.enums.ContractCharacter;
import com.bjike.goddess.employeecontract.enums.ContractStatus;


/**
* 员工合同信息
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-08 10:56 ]
* @Description:	[ 员工合同信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ContractInformationTO extends BaseTO {
    /**
     * 合同性质
     */
    private ContractCharacter  contractCharacter;

    /**
     * 合同编号
     */
    private String  ContractNumber;

    /**
     * 姓名
     */
    private String  name;

    /**
     * 员工编号
     */
    private String  employeeNumber;

    /**
     * 地区
     */
    private String  area;

    /**
     * 项目组/部门
     */
    private String  department;

    /**
     * 岗位
     */
    private String  position;

    /**
     * 入职日期
     */
    private String  entryDate;

    /**
     * 同意试用期时长
     */
    private AgreeProbationTime agreeProbationTime;


    /**
     * 转正时间
     */
    private String  conversionTime;

    /**
     * 离职日期
     */
    private String  dimissionDate;

    /**
     * 是否签订劳动合同
     */
    private Boolean  ifSignContract;

    /**
     * 签订所属公司
     */
    private String  signBelongCompany;

    /**
     * 签订劳动合同开始日期
     */
    private String  signContractStartDate;

    /**
     * 原件存储位置
     */
    private String  attachmentStorageLocation;


    public ContractCharacter getContractCharacter() {
        return contractCharacter;
    }

    public void setContractCharacter(ContractCharacter contractCharacter) {
        this.contractCharacter = contractCharacter;
    }

    public String getContractNumber() {
        return ContractNumber;
    }

    public void setContractNumber(String contractNumber) {
        ContractNumber = contractNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public AgreeProbationTime getAgreeProbationTime() {
        return agreeProbationTime;
    }

    public void setAgreeProbationTime(AgreeProbationTime agreeProbationTime) {
        this.agreeProbationTime = agreeProbationTime;
    }

    public String getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(String conversionTime) {
        this.conversionTime = conversionTime;
    }

    public String getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(String dimissionDate) {
        this.dimissionDate = dimissionDate;
    }

    public Boolean getIfSignContract() {
        return ifSignContract;
    }

    public void setIfSignContract(Boolean ifSignContract) {
        this.ifSignContract = ifSignContract;
    }

    public String getSignBelongCompany() {
        return signBelongCompany;
    }

    public void setSignBelongCompany(String signBelongCompany) {
        this.signBelongCompany = signBelongCompany;
    }

    public String getSignContractStartDate() {
        return signContractStartDate;
    }

    public void setSignContractStartDate(String signContractStartDate) {
        this.signContractStartDate = signContractStartDate;
    }

    public String getAttachmentStorageLocation() {
        return attachmentStorageLocation;
    }

    public void setAttachmentStorageLocation(String attachmentStorageLocation) {
        this.attachmentStorageLocation = attachmentStorageLocation;
    }
}