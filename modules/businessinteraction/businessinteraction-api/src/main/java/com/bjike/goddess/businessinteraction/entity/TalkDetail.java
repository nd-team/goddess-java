package com.bjike.goddess.businessinteraction.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资料信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:48 ]
 * @Description: [ 资料信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessinteraction_talkdetail")
public class TalkDetail extends BaseEntity {

    /**
     * 符合对象公司名称
     */
    @Column(name = "cooperCompany", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '符合对象公司名称'")
    private String cooperCompany;

    /**
     * 对象公司所在地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '对象公司所在地区'")
    private String area;

    /**
     * 对象公司业务类型
     */
    @Column(name = "businessTarget", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '对象公司业务类型'")
    private String businessTarget;

    /**
     * 对象公司专业
     */
    @Column(name = "professional", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '对象公司专业'")
    private String professional;

    /**
     * 对象公司规模
     */
    @Column(name = "objectSize", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '对象公司规模'")
    private String objectSize;

    /**
     * 合作方式
     */
    @Column(name = "cooperWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合作方式'")
    private String cooperWay;

    /**
     * 合作时间
     */
    @Column(name = "cooperDate", nullable = false, columnDefinition = "DATE   COMMENT '合作时间'")
    private LocalDate cooperDate;

    /**
     * 服务费用
     */
    @Column(name = "serviceFee", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '服务费用'")
    private Double serviceFee;

    /**
     * 中介费用
     */
    @Column(name = "intermediaryFee", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '中介费用'")
    private Double intermediaryFee;

    /**
     * 是否达成合作
     */
    @Column(name = "is_reachedCooper", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否达成合作'", insertable = false)
    private Boolean reachedCooper;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
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

    public LocalDate getCooperDate() {
        return cooperDate;
    }

    public void setCooperDate(LocalDate cooperDate) {
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