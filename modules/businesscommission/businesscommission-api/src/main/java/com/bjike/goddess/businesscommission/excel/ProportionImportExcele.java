package com.bjike.goddess.businesscommission.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * @Author: [zhuangkaiqin]
 * @Date 17-6-16下午4:38
 * @Description: [业务提成分配比例]
 * @Version: [1.0.0]
 */
public class ProportionImportExcele extends BaseTO {
//    /**
//     * 业务提成分配比例协商时间
//     */
//    @ExcelHeader(name = "业务提成分配比例协商时间", notNull = true)
//    private String time;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 部门/项目组
     */
    @ExcelHeader(name = "部门/项目组", notNull = true)
    private String department;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称", notNull = true)
    private String projectName;

    /**
     * 影响因素
     */
    @ExcelHeader(name = "影响因素", notNull = true)
    private String factors;

    /**
     * 类型影响因素权重
     */
    @ExcelHeader(name = "类型影响因素权重", notNull = true)
    private Double typeFactors;

    /**
     * 信息提供人
     */
    @ExcelHeader(name = "信息提供人", notNull = true)
    private Double messageProportion;

    /**
     * 介绍关系揽接
     */
    @ExcelHeader(name = "介绍关系揽接", notNull = true)
    private Double businessProportion;

    /**
     * 出面接洽
     */
    @ExcelHeader(name = "出面接洽", notNull = true)
    private Double talkProportion;

    /**
     * 维护
     */
    @ExcelHeader(name = "维护", notNull = true)
    private Double maintainProportion;

    /**
     * 剩余分配比例
     */
    @ExcelHeader(name = "剩余分配比例", notNull = true)
    private Double surplusProportion;

    /**
     * 参与协商人
     */
    @ExcelHeader(name = "参与协商人", notNull = true)
    private String consultants;

    /**
     * 提成分配比例确认单是否全部确认
     */
    @ExcelHeader(name = "提成分配比例确认单是否全部确认", notNull = true)
    private String confirm;

    /**
     * 已确认人
     */
    @ExcelHeader(name = "已确认人")
    private String confirmed;

    /**
     * 未确认人
     */
    @ExcelHeader(name = "未确认人")
    private String notConfirmed;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    public Double getTypeFactors() {
        return typeFactors;
    }

    public void setTypeFactors(Double typeFactors) {
        this.typeFactors = typeFactors;
    }

    public Double getMessageProportion() {
        return messageProportion;
    }

    public void setMessageProportion(Double messageProportion) {
        this.messageProportion = messageProportion;
    }

    public Double getBusinessProportion() {
        return businessProportion;
    }

    public void setBusinessProportion(Double businessProportion) {
        this.businessProportion = businessProportion;
    }

    public Double getTalkProportion() {
        return talkProportion;
    }

    public void setTalkProportion(Double talkProportion) {
        this.talkProportion = talkProportion;
    }

    public Double getMaintainProportion() {
        return maintainProportion;
    }

    public void setMaintainProportion(Double maintainProportion) {
        this.maintainProportion = maintainProportion;
    }

    public Double getSurplusProportion() {
        return surplusProportion;
    }

    public void setSurplusProportion(Double surplusProportion) {
        this.surplusProportion = surplusProportion;
    }

    public String getConsultants() {
        return consultants;
    }

    public void setConsultants(String consultants) {
        this.consultants = consultants;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getNotConfirmed() {
        return notConfirmed;
    }

    public void setNotConfirmed(String notConfirmed) {
        this.notConfirmed = notConfirmed;
    }
}
