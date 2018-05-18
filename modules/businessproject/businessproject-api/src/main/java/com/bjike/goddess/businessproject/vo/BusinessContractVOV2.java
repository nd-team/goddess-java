package com.bjike.goddess.businessproject.vo;

import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.businessproject.enums.TaskContract;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 商务项目合同V2
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2018-02-10 11:36 ]
 * @Description: [ 商务项目合同 ]
 * @Version: [ v2.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessContractVOV2 {

    private String id;

    /**
     * 测算分类
     */
    private String measureClassify;

    /**
     * 签订时间
     */
    private String signedTime;

    /**
     * 地区
     */
    private String area;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务方向科目
     */
    private String businessSubject;

    /**
     * 总包单位名称
     */
    private String majorCompany;

    /**
     * 所属项目组
     */
    private String projectGroup;

    /**
     * 营运商
     */
    private String operator;

    /**
     * 类型
     */
    private String type;

    /**
     * 专业/工期
     */
    private String major;

    /**
     * 派工状态
     */
    private TaskContract taskContract;

    /**
     * 合同状态
     */
    private MakeContract makeContract;

    /**
     * 销售合同编号
     */
    private String salesContractNum;

    /**
     * 单次合同编号
     */
    private String singleContractNum;

    /**
     * 单次合同名称
     */
    private String singleContractName;

    /**
     * 派工界面A
     */
    private String dispatchInterfaceA;

    /**
     * 派工界面B
     */
    private String dispatchInterfaceB;

    /**
     * 派工界面C
     */
    private String dispatchInterfaceC;

    /**
     * 合同规模数量
     */
    private Integer scaleContract;

    /**
     * 合同持续时长
     */
    private Double contractPersist;

    /**
     * 是否为持续
     */
    private Boolean persist;

    /**
     * 计划开工时间
     */
    private String expectStartDate;

    /**
     * 计划完工时间
     */
    private String expectCompleteTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeasureClassify() {
        return measureClassify;
    }

    public void setMeasureClassify(String measureClassify) {
        this.measureClassify = measureClassify;
    }

    public String getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(String signedTime) {
        this.signedTime = signedTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessSubject() {
        return businessSubject;
    }

    public void setBusinessSubject(String businessSubject) {
        this.businessSubject = businessSubject;
    }

    public String getMajorCompany() {
        return majorCompany;
    }

    public void setMajorCompany(String majorCompany) {
        this.majorCompany = majorCompany;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public TaskContract getTaskContract() {
        return taskContract;
    }

    public void setTaskContract(TaskContract taskContract) {
        this.taskContract = taskContract;
    }

    public MakeContract getMakeContract() {
        return makeContract;
    }

    public void setMakeContract(MakeContract makeContract) {
        this.makeContract = makeContract;
    }

    public String getSalesContractNum() {
        return salesContractNum;
    }

    public void setSalesContractNum(String salesContractNum) {
        this.salesContractNum = salesContractNum;
    }

    public String getSingleContractNum() {
        return singleContractNum;
    }

    public void setSingleContractNum(String singleContractNum) {
        this.singleContractNum = singleContractNum;
    }

    public String getSingleContractName() {
        return singleContractName;
    }

    public void setSingleContractName(String singleContractName) {
        this.singleContractName = singleContractName;
    }

    public String getDispatchInterfaceA() {
        return dispatchInterfaceA;
    }

    public void setDispatchInterfaceA(String dispatchInterfaceA) {
        this.dispatchInterfaceA = dispatchInterfaceA;
    }

    public String getDispatchInterfaceB() {
        return dispatchInterfaceB;
    }

    public void setDispatchInterfaceB(String dispatchInterfaceB) {
        this.dispatchInterfaceB = dispatchInterfaceB;
    }

    public String getDispatchInterfaceC() {
        return dispatchInterfaceC;
    }

    public void setDispatchInterfaceC(String dispatchInterfaceC) {
        this.dispatchInterfaceC = dispatchInterfaceC;
    }

    public Integer getScaleContract() {
        return scaleContract;
    }

    public void setScaleContract(Integer scaleContract) {
        this.scaleContract = scaleContract;
    }

    public Double getContractPersist() {
        return contractPersist;
    }

    public void setContractPersist(Double contractPersist) {
        this.contractPersist = contractPersist;
    }

    public Boolean getPersist() {
        return persist;
    }

    public void setPersist(Boolean persist) {
        this.persist = persist;
    }

    public String getExpectStartDate() {
        return expectStartDate;
    }

    public void setExpectStartDate(String expectStartDate) {
        this.expectStartDate = expectStartDate;
    }

    public String getExpectCompleteTime() {
        return expectCompleteTime;
    }

    public void setExpectCompleteTime(String expectCompleteTime) {
        this.expectCompleteTime = expectCompleteTime;
    }
}