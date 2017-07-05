package com.bjike.goddess.businesscommission.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * @Author: [zhuangkaiqin]
 * @Date 17-6-16下午4:38
 * @Description: [业务提成权重分配]
 * @Version: [1.0.0]
 */
public class WeightAllotImportExcele extends BaseTO {
    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称", notNull = true)
    private String projectName;

    /**
     * 信息提供占比
     */
    @ExcelHeader(name = "信息提供占比", notNull = false)
    private Double messageProportion;

    /**
     * 业务揽接占比
     */
    @ExcelHeader(name = "业务揽接占比", notNull = false)
    private Double businessProportion;

    /**
     * 业务洽谈占比
     */
    @ExcelHeader(name = "业务洽谈占比", notNull = false)
    private Double talkProportion;

    /**
     * 维护占比
     */
    @ExcelHeader(name = "维护占比", notNull = false)
    private Double maintainProportion;

    /**
     * 剩余占比
     */
    @ExcelHeader(name = "剩余占比", notNull = false)
    private Double surplusProportion;

    /**
     * 总比例
     */
    @ExcelHeader(name = "总比例", notNull = false)
    private Double totalProportion;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注", notNull = false)
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public Double getTotalProportion() {
        return totalProportion;
    }

    public void setTotalProportion(Double totalProportion) {
        this.totalProportion = totalProportion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
