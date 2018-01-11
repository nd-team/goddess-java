package com.bjike.goddess.businessinteraction.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;


/**
 * 资料信息导出
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:48 ]
 * @Description: [ 资料信息导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TalkDetailExcel extends BaseBO {

    /**
     * 符合对象公司名称
     */
    @ExcelHeader(name = "符合对象公司名称",notNull = true)
    private String cooperCompany;

    /**
     * 对象公司所在地区
     */
    @ExcelHeader(name = "对象公司所在地区",notNull = true)
    private String area;

    /**
     * 对象公司业务类型
     */
    @ExcelHeader(name = "对象公司业务类型",notNull = true)
    private String businessTarget;

    /**
     * 对象公司专业
     */
    @ExcelHeader(name = "对象公司专业",notNull = true)
    private String professional;

    /**
     * 对象公司规模
     */
    @ExcelHeader(name = "对象公司规模",notNull = true)
    private String objectSize;

    /**
     * 合作方式
     */
    @ExcelHeader(name = "合作方式",notNull = true)
    private String cooperWay;

    /**
     * 合作时间
     */
    @ExcelHeader(name = "合作时间",notNull = true)
    private LocalDate cooperDate;

    /**
     * 服务费用
     */
    @ExcelHeader(name = "服务费用",notNull = true)
    private Double serviceFee;

    /**
     * 中介费用
     */
    @ExcelHeader(name = "中介费用",notNull = true)
    private Double intermediaryFee;

    /**
     * 是否达成合作
     */
    @ExcelHeader(name = "是否达成合作",notNull = true)
    private Boolean reachedCooper;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注")
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