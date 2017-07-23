package com.bjike.goddess.accommodation.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 16:16]
 * @Description: [租房信息  业务传输]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectBO extends BaseBO {
    /**
     * 地区
     */
    private String area;
    /**
     * 项目组
     */
    private String projectGroup;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 租房地址
     */
    private String address;
    /**
     * 房租
     */
    private Double rent;
    /**
     * 中介费
     */
    private Double agency;
    /**
     * 押金
     */
    private Double deposit;
    /**
     * 管理费
     */
    private Double managementFee;
    /**
     * 卫生费
     */
    private Double healthFee;

    /**
     * 网络套餐费用
     */
    private Double network;
    /**
     * 管道燃气费充值额度
     */
    private Double gas;
    /**
     * 合计
     */
    private String remark;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getAgency() {
        return agency;
    }

    public void setAgency(Double agency) {
        this.agency = agency;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    public Double getHealthFee() {
        return healthFee;
    }

    public void setHealthFee(Double healthFee) {
        this.healthFee = healthFee;
    }

    public Double getNetwork() {
        return network;
    }

    public void setNetwork(Double network) {
        this.network = network;
    }

    public Double getGas() {
        return gas;
    }

    public void setGas(Double gas) {
        this.gas = gas;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
