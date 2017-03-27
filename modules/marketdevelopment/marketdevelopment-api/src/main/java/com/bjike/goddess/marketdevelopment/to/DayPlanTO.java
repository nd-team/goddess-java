package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 天计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:08 ]
 * @Description: [ 天计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DayPlanTO extends BaseTO {

    /**
     * 序号
     */
    @NotNull(message = "序号不能为空",groups = {ADD.class, EDIT.class})
    private String serialNumber;

    /**
     * 时间
     */
    @NotNull(message = "时间不能为空",groups = {ADD.class, EDIT.class})
    private String time;

    /**
     * 业务状态(立项前/立项后)
     */
    @NotNull(message = "业务状态不能为空",groups = {ADD.class, EDIT.class})
    private Boolean business;

    /**
     * 业务类型
     */
    @NotNull(message = "业务类型不能为空",groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 业务方向科目
     */
    @NotNull(message = "业务方向科目不能为空",groups = {ADD.class, EDIT.class})
    private String source;

    /**
     * 公司名称
     */
    @NotNull(message = "公司名称不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 计划工作内容
     */
    @NotNull(message = "计划工作内容不能为空",groups = {ADD.class, EDIT.class})
    private String content;

    /**
     * 所属阶段
     */
    @NotNull(message = "所属阶段不能为空",groups = {ADD.class, EDIT.class})
    private String phase;

    /**
     * 任务量
     */
    @NotNull(message = "任务量不能为空",groups = {ADD.class, EDIT.class})
    private Double quota;

    /**
     * 任务人
     */
    @NotNull(message = "任务人不能为空",groups = {ADD.class, EDIT.class})
    private String own;

    /**
     * 洽谈方式
     */
    @NotNull(message = "洽谈方式不能为空",groups = {ADD.class, EDIT.class})
    private String negotiation;

    /**
     * 商务预算费
     */
    @NotNull(message = "商务预算费不能为空",groups = {ADD.class, EDIT.class})
    private Double budget;

    /**
     * 客户姓名
     */
    @NotNull(message = "客户姓名不能为空",groups = {ADD.class, EDIT.class})
    private String customer;

    /**
     * 客户电话
     */
    @NotNull(message = "客户电话不能为空",groups = {ADD.class, EDIT.class})
    private String phone;

    /**
     * 商务内容
     */
    @NotNull(message = "商务内容不能为空",groups = {ADD.class, EDIT.class})
    private String businessContent;

    /**
     * 配合人
     */
    private String coordinate;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getBusiness() {
        return business;
    }

    public void setBusiness(Boolean business) {
        this.business = business;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public String getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(String negotiation) {
        this.negotiation = negotiation;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessContent() {
        return businessContent;
    }

    public void setBusinessContent(String businessContent) {
        this.businessContent = businessContent;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}