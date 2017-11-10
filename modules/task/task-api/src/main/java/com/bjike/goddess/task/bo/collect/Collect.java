package com.bjike.goddess.task.bo.collect;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-22 11:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Collect implements Serializable {

    private String project;//项目名

    @ExcelHeader(name = "内部项目名称")
    private String innerProject; //内部项目名称
    @ExcelHeader(name = "外部项目名称")
    private String outProject; //外部项目名称
    @ExcelHeader(name = "是否完工")
    private String isFinish;//是否完工
    @ExcelHeader(name = "人工数")
    private int workerCount;//人工数
    @ExcelHeader(name = "合同规模数")
    private double scaleCount;//合同规模数（从商务合同管理获取）
    @ExcelHeader(name = "实际完成规模数")
    private double finishCount;
    @ExcelHeader(name = "出车数量")
    private int carCount;//出车数量（从出车记录管理获取）

    private String department;//如果设置有值,则为部门汇总

    private List<Custom> customs = new ArrayList<>(); //自定义汇总
    /**
     * 部门汇总(今天)
     */
    private List<TaskCollect> todayCollects;

    /**
     * 部门汇总(明天)
     */
    private List<TomorrowCollect> tomorrowCollects;

    public double getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(double finishCount) {
        this.finishCount = finishCount;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getOutProject() {
        return outProject;
    }

    public void setOutProject(String outProject) {
        this.outProject = outProject;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(int workerCount) {
        this.workerCount = workerCount;
    }

    public double getScaleCount() {
        return scaleCount;
    }

    public void setScaleCount(double scaleCount) {
        this.scaleCount = scaleCount;
    }

    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public List<Custom> getCustoms() {
        return customs;
    }

    public void setCustoms(List<Custom> customs) {
        this.customs = customs;
    }

    public List<TaskCollect> getTodayCollects() {
        return todayCollects;
    }

    public void setTodayCollects(List<TaskCollect> todayCollects) {
        this.todayCollects = todayCollects;
    }

    public List<TomorrowCollect> getTomorrowCollects() {
        return tomorrowCollects;
    }

    public void setTomorrowCollects(List<TomorrowCollect> tomorrowCollects) {
        this.tomorrowCollects = tomorrowCollects;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
