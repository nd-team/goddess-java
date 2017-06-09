package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 增员前
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-27 10:10 ]
 * @Description: [ 增员前 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BeforeAddTO extends BaseTO {
    public interface complete {
    }

    /**
     * 资质名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "资质名称不能为空")
    private String intelligence;

    /**
     * 公司名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "公司名称不能为空")
    private String company;

    /**
     * 社保需增地市
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "社保需增地市不能为空")
    private String arrival;

    /**
     * 社保地市特殊说明
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "社保地市特殊说明不能为空")
    private String arrivalDescrption;

    /**
     * 需增员时间
     */
    @NotBlank(groups = {BeforeAddTO.complete.class, EDIT.class}, message = "需增员时间不能为空")
    private String addTime;

    /**
     * 需参保时长
     */
    @NotBlank(groups = {EDIT.class}, message = "需参保时长不能为空")
    private Double time;

    /**
     * 增员参保周期特殊要求
     */
    @NotBlank(groups = {BeforeAddTO.complete.class, EDIT.class}, message = "增员参保周期特殊要求不能为空")
    private String request;

    /**
     * 当前各公司参保总人数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "当前各公司参保总人数不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "当前各公司参保总人数必须大于等于0")
    private Integer companyCount;

    /**
     * 当前各地市总参保人员
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "当前各地市总参保人员不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "当前各地市总参保人员必须大于等于0")
    private Integer cityCount;

    /**
     * 可参保人员姓名
     */
    @NotBlank(groups = {BeforeAddTO.complete.class, EDIT.class}, message = "可参保人员姓名不能为空")
    private String name;

    /**
     * 可参保人员新增的地市
     */
    @NotBlank(groups = {BeforeAddTO.complete.class, EDIT.class}, message = "可参保人员新增的地市不能为空")
    private String addCity;

    /**
     * 备注
     */
    @NotBlank(groups = {BeforeAddTO.complete.class, EDIT.class}, message = "备注不能为空")
    private String description;

    /**
     * 是否增员
     */
    private boolean increase;


    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getArrivalDescrption() {
        return arrivalDescrption;
    }

    public void setArrivalDescrption(String arrivalDescrption) {
        this.arrivalDescrption = arrivalDescrption;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Integer getCompanyCount() {
        return companyCount;
    }

    public void setCompanyCount(Integer companyCount) {
        this.companyCount = companyCount;
    }

    public Integer getCityCount() {
        return cityCount;
    }

    public void setCityCount(Integer cityCount) {
        this.cityCount = cityCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddCity() {
        return addCity;
    }

    public void setAddCity(String addCity) {
        this.addCity = addCity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIncrease() {
        return increase;
    }

    public void setIncrease(boolean increase) {
        this.increase = increase;
    }
}