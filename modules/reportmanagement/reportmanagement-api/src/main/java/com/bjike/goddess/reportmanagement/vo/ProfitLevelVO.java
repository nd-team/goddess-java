package com.bjike.goddess.reportmanagement.vo;

/**
 * 利润水平分析
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-26 17:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProfitLevelVO {
    /**
     * 项目
     */
    private String project;
    /**
     * 开始值
     */
    private Double start;
    /**
     * 结束值
     */
    private Double end;
    /**
     * 增减额
     */
    private Double change;
    /**
     * 增减率
     */
    private String changeScale;

//    /**
//     * 利润类型
//     */
//    private ProfitType profitType;

//    public ProfitType getProfitType() {
//        return profitType;
//    }
//
//    public void setProfitType(ProfitType profitType) {
//        this.profitType = profitType;
//    }

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getStart() {
        return start;
    }

    public void setStart(Double start) {
        this.start = start;
    }

    public Double getEnd() {
        return end;
    }

    public void setEnd(Double end) {
        this.end = end;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public String getChangeScale() {
        return changeScale;
    }

    public void setChangeScale(String changeScale) {
        this.changeScale = changeScale;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
