package com.bjike.goddess.businessproject.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 商务项目合同业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessContractADetailBO extends BaseBO {

    /**
     * 所属项目组
     */
    private String projectGroup;
    /**
     * 内部项目名称
     */
    private String innerProject;
    /**
     * 总金额
     */
    private Double totalMoney;
    /**
     * 总规模数量
     */
    private Double scaleContract;

    /**
     * 实际完成规模数量
     */
    private Double finishScale;
    /**
     * 商务合同汇总数据
     */
    private List<BusinessContractBDetailBO> businessContractBDetailBOS;

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public Double getScaleContract() {
        return scaleContract;
    }

    public void setScaleContract(Double scaleContract) {
        this.scaleContract = scaleContract;
    }

    public Double getFinishScale() {
        return finishScale;
    }

    public void setFinishScale(Double finishScale) {
        this.finishScale = finishScale;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<BusinessContractBDetailBO> getBusinessContractBDetailBOS() {
        return businessContractBDetailBOS;
    }

    public void setBusinessContractBDetailBOS(List<BusinessContractBDetailBO> businessContractBDetailBOS) {
        this.businessContractBDetailBOS = businessContractBDetailBOS;
    }
}