package com.bjike.goddess.businessinteraction.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 资料信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:48 ]
 * @Description: [ 资料信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TalkDetailTO extends BaseTO {

    /**
     * 符合对象公司名称
     */
    @NotBlank(message = "符合对象公司名称不能为空",groups = {ADD.class, EDIT.class})
    private String cooperCompany;

    /**
     * 对象公司所在地区
     */
    @NotBlank(message = "对象公司所在地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 对象公司业务类型
     */
    @NotBlank(message = "对象公司业务类型不能为空",groups = {ADD.class, EDIT.class})
    private String businessTarget;

    /**
     * 对象公司专业
     */
    @NotBlank(message = "对象公司专业不能为空",groups = {ADD.class, EDIT.class})
    private String professional;

    /**
     * 对象公司规模
     */
    @NotBlank(message = "对象公司规模不能为空",groups = {ADD.class, EDIT.class})
    private String objectSize;

    /**
     * 合作方式
     */
    @NotBlank(message = "合作方式不能为空",groups = {ADD.class, EDIT.class})
    private String cooperWay;

    /**
     * 合作时间
     */
    @NotBlank(message = "合作时间不能为空",groups = {ADD.class, EDIT.class})
    private String cooperDate;

    /**
     * 服务费用
     */
    @NotNull(message = "服务费用不能为空",groups = {ADD.class, EDIT.class})
    private Double serviceFee;

    /**
     * 中介费用
     */
    @NotNull(message = "中介费用不能为空",groups = {ADD.class, EDIT.class})
    private Double intermediaryFee;

    /**
     * 是否达成合作
     */
    @NotNull(message = "是否达成合作不能为空",groups = {ADD.class, EDIT.class})
    private Boolean reachedCooper;

    /**
     * 备注
     */
    private String remark;


    public String getCooperCompany() {
        return cooperCompany;
    }

    public void setCooperCompany(String cooperCompany) {
        this.cooperCompany = cooperCompany;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessTarget() {
        return businessTarget;
    }

    public void setBusinessTarget(String businessTarget) {
        this.businessTarget = businessTarget;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getObjectSize() {
        return objectSize;
    }

    public void setObjectSize(String objectSize) {
        this.objectSize = objectSize;
    }

    public String getCooperWay() {
        return cooperWay;
    }

    public void setCooperWay(String cooperWay) {
        this.cooperWay = cooperWay;
    }

    public String getCooperDate() {
        return cooperDate;
    }

    public void setCooperDate(String cooperDate) {
        this.cooperDate = cooperDate;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getIntermediaryFee() {
        return intermediaryFee;
    }

    public void setIntermediaryFee(Double intermediaryFee) {
        this.intermediaryFee = intermediaryFee;
    }

    public Boolean getReachedCooper() {
        return reachedCooper;
    }

    public void setReachedCooper(Boolean reachedCooper) {
        this.reachedCooper = reachedCooper;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}