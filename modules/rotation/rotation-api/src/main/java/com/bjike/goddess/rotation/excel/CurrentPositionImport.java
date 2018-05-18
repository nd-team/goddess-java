package com.bjike.goddess.rotation.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 当前岗位传输模板
 * @Author: [caiwenxian]
 * @Date: [2018-01-09 11:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CurrentPositionImport {

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名", notNull = true)
    private String name;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name = "项目组/部门", notNull = true)
    private String department;

    /**
     * 入职时间
     */
    @ExcelHeader(name = "入职时间", notNull = true)
    private String entryTime;

    /**
     * 转正时间
     */
    @ExcelHeader(name = "转正时间", notNull = true)
    private String turnPositiveTime;

    /**
     * 岗位层级
     */
    @ExcelHeader(name = "岗位层级", notNull = true)
    private String arrangement;

    /**
     * 轮岗层级
     */
    @ExcelHeader(name = "轮岗层级", notNull = true)
    private String rotationArrangement;

    /**
     * 获得时间
     */
    @ExcelHeader(name = "获得时间", notNull = true)
    private String getTime;

    /**
     * 在岗时长
     */
    @ExcelHeader(name = "在岗时长", notNull = true)
    private Integer rotationCycle;

    /**
     * 在岗补贴
     */
    @ExcelHeader(name = "在岗补贴", notNull = true)
    private Double subsidy;

    /**
     * 是否应该轮岗
     */
    @ExcelHeader(name = "是否应该轮岗", notNull = true)
    private String rotation;

    /**
     * 是否延后
     */
    @ExcelHeader(name = "是否延后", notNull = true)
    private String delay;

    /**
     * 延后次数
     */
    @ExcelHeader(name = "延后次数")
    private Integer delayTimes;

    /**
     * 延后的在岗时长
     */
    @ExcelHeader(name = "延后的在岗时长")
    private Double delayCycle;

    /**
     * （延后）是否应该轮岗
     */
    @ExcelHeader(name = "（延后）是否应该轮岗")
    private String delayRotation;

    /**
     * 轮岗次数
     */
    @ExcelHeader(name = "轮岗次数")
    private Integer rotationTimes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getTurnPositiveTime() {
        return turnPositiveTime;
    }

    public void setTurnPositiveTime(String turnPositiveTime) {
        this.turnPositiveTime = turnPositiveTime;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getRotationArrangement() {
        return rotationArrangement;
    }

    public void setRotationArrangement(String rotationArrangement) {
        this.rotationArrangement = rotationArrangement;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public Integer getRotationCycle() {
        return rotationCycle;
    }

    public void setRotationCycle(Integer rotationCycle) {
        this.rotationCycle = rotationCycle;
    }

    public Double getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(Double subsidy) {
        this.subsidy = subsidy;
    }

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public Integer getDelayTimes() {
        return delayTimes;
    }

    public void setDelayTimes(Integer delayTimes) {
        this.delayTimes = delayTimes;
    }

    public Double getDelayCycle() {
        return delayCycle;
    }

    public void setDelayCycle(Double delayCycle) {
        this.delayCycle = delayCycle;
    }

    public String getDelayRotation() {
        return delayRotation;
    }

    public void setDelayRotation(String delayRotation) {
        this.delayRotation = delayRotation;
    }

    public Integer getRotationTimes() {
        return rotationTimes;
    }

    public void setRotationTimes(Integer rotationTimes) {
        this.rotationTimes = rotationTimes;
    }

    @Override
    public String toString() {
        return "CurrentPositionExport{" +
                "name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", department='" + department + '\'' +
                ", entryTime='" + entryTime + '\'' +
                ", turnPositiveTime='" + turnPositiveTime + '\'' +
                ", arrangement='" + arrangement + '\'' +
                ", rotationArrangement='" + rotationArrangement + '\'' +
                ", getTime='" + getTime + '\'' +
                ", rotationCycle=" + rotationCycle +
                ", subsidy=" + subsidy +
                ", rotation=" + rotation +
                ", delay=" + delay +
                ", delayTimes=" + delayTimes +
                ", delayCycle=" + delayCycle +
                ", delayRotation=" + delayRotation +
                ", rotationTimes=" + rotationTimes +
                '}';
    }
}

