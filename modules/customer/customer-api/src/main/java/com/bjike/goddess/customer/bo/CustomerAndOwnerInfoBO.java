package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.customer.entity.CustomerDetail;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.enums.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 业主客户信息
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T16:23:28.041 ]
 * @Description: [ 业主客户信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerAndOwnerInfoBO extends BaseBO {

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 性别
     */
    private CustomerSex customerSex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 客户状态
     */
    private CustomerStatus customerStatus;
    /**
     * 组织机构规模
     */
    private String origanizationSize;
    /**
     * 客户级别
     */
    private CustomerLevel customerLevel;

    /**
     * 地区
     */
    private String area;

    /**
     * 地区经纬度
     */
    private String areaLatitude;
    /**
     * 电话
     */
    private String tel;

    /**
     * 微信
     */
    private String weChart;
    /**
     * 客户性格
     */
    private String customerCharacter;
    /**
     * 客户脾气
     */
    private String customerTemper;
    /**
     * 客户兴趣
     */
    private String customerInterest;
    /**
     * 共同认识的人
     */
    private String knowEachOther;

    /**
     * 业主提供的身份证明
     */
    private String proof;
    /**
     * 与业主签订的合同
     */
    private String landlordContract;
    /**
     * 甲方和业主的合同
     */
    private String firstAndLandlordContract;
    /**
     * 数据来源
     */
    private String dataSource;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public CustomerSex getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(CustomerSex customerSex) {
        this.customerSex = customerSex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public CustomerStatus getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(CustomerStatus customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getOriganizationSize() {
        return origanizationSize;
    }

    public void setOriganizationSize(String origanizationSize) {
        this.origanizationSize = origanizationSize;
    }

    public CustomerLevel getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(CustomerLevel customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaLatitude() {
        return areaLatitude;
    }

    public void setAreaLatitude(String areaLatitude) {
        this.areaLatitude = areaLatitude;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWeChart() {
        return weChart;
    }

    public void setWeChart(String weChart) {
        this.weChart = weChart;
    }

    public String getCustomerCharacter() {
        return customerCharacter;
    }

    public void setCustomerCharacter(String customerCharacter) {
        this.customerCharacter = customerCharacter;
    }

    public String getCustomerTemper() {
        return customerTemper;
    }

    public void setCustomerTemper(String customerTemper) {
        this.customerTemper = customerTemper;
    }

    public String getCustomerInterest() {
        return customerInterest;
    }

    public void setCustomerInterest(String customerInterest) {
        this.customerInterest = customerInterest;
    }

    public String getKnowEachOther() {
        return knowEachOther;
    }

    public void setKnowEachOther(String knowEachOther) {
        this.knowEachOther = knowEachOther;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getLandlordContract() {
        return landlordContract;
    }

    public void setLandlordContract(String landlordContract) {
        this.landlordContract = landlordContract;
    }

    public String getFirstAndLandlordContract() {
        return firstAndLandlordContract;
    }

    public void setFirstAndLandlordContract(String firstAndLandlordContract) {
        this.firstAndLandlordContract = firstAndLandlordContract;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}