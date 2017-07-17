package com.bjike.goddess.checkhost.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 离宿申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 04:51 ]
 * @Description: [ 离宿申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HostApplyTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组不能为空")
    private String projectGroup;

    /**
     * 宿舍地址
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "宿舍地址不能为空")
    private String address;

    /**
     * 员工姓名
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "员工姓名不能为空")
    private String name;

    /**
     * 员工编号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "员工编号不能为空")
    private String num;

    /**
     * 离宿原因
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "离宿原因不能为空")
    private String hostCause;
    /**
     * 离宿时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "离宿时间不能为空")
    private String hostTime;

    /**
     * 是否归还钥匙
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否归还钥匙不能为空")
    private Boolean returnKey;
    /**
     * 三件套是否收费
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "三件套是否收费不能为空")
    private Boolean suitToll;
    /**
     * 收费金额
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "收费金额不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "收费金额不能小于0")
    private Double tollMoney;
    /**
     * 水电费，燃气费总额
     */
    private Double amount;

    /**
     * 合计总额（收费金额+水电费，燃气费总额）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "合计总额（收费金额+水电费，燃气费总额）不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "合计总额（收费金额+水电费，燃气费总额）不能小于0")
    private Double totalAmount;

    /**
     * 模块责任人审核
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "模块责任人审核不能为空")
    private String headAudit;

    /**
     * 备注
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getHostCause() {
        return hostCause;
    }

    public void setHostCause(String hostCause) {
        this.hostCause = hostCause;
    }

    public String getHostTime() {
        return hostTime;
    }

    public void setHostTime(String hostTime) {
        this.hostTime = hostTime;
    }

    public Boolean getReturnKey() {
        return returnKey;
    }

    public void setReturnKey(Boolean returnKey) {
        this.returnKey = returnKey;
    }

    public Boolean getSuitToll() {
        return suitToll;
    }

    public void setSuitToll(Boolean suitToll) {
        this.suitToll = suitToll;
    }

    public Double getTollMoney() {
        return tollMoney;
    }

    public void setTollMoney(Double tollMoney) {
        this.tollMoney = tollMoney;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getHeadAudit() {
        return headAudit;
    }

    public void setHeadAudit(String headAudit) {
        this.headAudit = headAudit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}