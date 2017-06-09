package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 购买社保人员
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:45 ]
 * @Description: [ 购买社保人员 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BuyTO extends BaseTO {

    /**
     * 姓名
     */
    @NotBlank(groups = {EDIT.class}, message = "购买方式不能为空")
    private String name;

    /**
     * 参保公司
     */
    @NotBlank(groups = {EDIT.class}, message = "购买方式不能为空")
    private String company;

    /**
     * 参保地市
     */
    @NotBlank(groups = {EDIT.class}, message = "购买方式不能为空")
    private String city;

    /**
     * 参保户口
     */
    @NotBlank(groups = {EDIT.class}, message = "购买方式不能为空")
    private String bornLocal;

    /**
     * 参保类型
     */
    @NotBlank(groups = {EDIT.class}, message = "购买方式不能为空")
    private String type;

    /**
     * 购买方式
     */
    @NotBlank(groups = {EDIT.class}, message = "购买方式不能为空")
    private String payType;

    /**
     * 社保状态
     */
    @NotBlank(groups = {EDIT.class}, message = "购买方式不能为空")
    private String status;

    /**
     * 审批状态
     */
    private boolean examine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBornLocal() {
        return bornLocal;
    }

    public void setBornLocal(String bornLocal) {
        this.bornLocal = bornLocal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isExamine() {
        return examine;
    }

    public void setExamine(boolean examine) {
        this.examine = examine;
    }
}