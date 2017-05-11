package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 社保卡基本信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 01:44 ]
 * @Description: [ 社保卡基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SecureCartTO extends BaseTO {

    /**
     * 姓名
     */
    @NotBlank(groups = {EDIT.class}, message = "姓名不能为空")
    private String name;

    /**
     * 员工编号
     */
    @NotBlank(groups = {EDIT.class}, message = "员工编号不能为空")
    private String employeeId;

    /**
     * 社保卡管理分类
     */
    @NotBlank(groups = {EDIT.class}, message = "社保卡管理分类不能为空")
    private String cart;

    /**
     * 地区
     */
    @NotBlank(groups = {EDIT.class}, message = "地区不能为空")
    private String arrival;

    /**
     * 社保帐户号码
     */
    @NotBlank(groups = {EDIT.class}, message = "社保帐户号码不能为空")
    private String cartNum;

    /**
     * 领卡机构
     */
    @NotBlank(groups = {EDIT.class}, message = "领卡机构不能为空")
    private String institutions;

    /**
     * 物品状态
     */
    @NotBlank(groups = {EDIT.class}, message = "物品状态不能为空")
    private String status;

    /**
     * 储存位置
     */
    @NotBlank(groups = {EDIT.class}, message = "储存位置不能为空")
    private String location;

    /**
     * 曾补办时间
     */
    @NotBlank(groups = {EDIT.class}, message = "曾补办时间不能为空")
    private String reapply;

    /**
     * 曾补办次数
     */
    @NotNull(groups = {EDIT.class}, message = "曾补办次数不能为空")
    @Min(value = 0, groups = {EDIT.class}, message = "曾补办次数必须大于等于0")
    private Integer reapplyNum;

    /**
     * 曾补办原因明细
     */
    @NotBlank(groups = {EDIT.class}, message = "曾补办原因明细不能为空")
    private String reason;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getCartNum() {
        return cartNum;
    }

    public void setCartNum(String cartNum) {
        this.cartNum = cartNum;
    }

    public String getInstitutions() {
        return institutions;
    }

    public void setInstitutions(String institutions) {
        this.institutions = institutions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReapply() {
        return reapply;
    }

    public void setReapply(String reapply) {
        this.reapply = reapply;
    }

    public Integer getReapplyNum() {
        return reapplyNum;
    }

    public void setReapplyNum(Integer reapplyNum) {
        this.reapplyNum = reapplyNum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}