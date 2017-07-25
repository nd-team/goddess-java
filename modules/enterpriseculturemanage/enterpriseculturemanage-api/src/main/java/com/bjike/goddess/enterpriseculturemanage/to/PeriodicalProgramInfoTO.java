package com.bjike.goddess.enterpriseculturemanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.enterpriseculturemanage.enums.AuditResult;
import com.bjike.goddess.enterpriseculturemanage.enums.PublicizeWay;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;

/**
 * 刊物方案信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-01 09:07 ]
 * @Description: [ 刊物方案信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PeriodicalProgramInfoTO extends BaseTO {

    /**
     * 方案名称
     */
    @NotBlank(message = "方案名称不能为空", groups = {ADD.class, EDIT.class})
    private String programName;

    /**
     * 刊物理念
     */
    @NotBlank(message = "刊物理念不能为空", groups = {ADD.class, EDIT.class})
    private String ida;

    /**
     * 发刊形式
     */
    @NotBlank(message = "刊物理念不能为空", groups = {ADD.class, EDIT.class})
    private PublicizeWay way;

    /**
     * 发刊规格
     */
    @NotBlank(message = "刊物理念不能为空", groups = {ADD.class, EDIT.class})
    private String standard;

    /**
     * 执笔人
     */
    @NotBlank(message = "刊物理念不能为空", groups = {ADD.class, EDIT.class})
    private String executer;

    /**
     * 刊物名称
     */
    @NotBlank(message = "刊物理念不能为空", groups = {ADD.class, EDIT.class})
    private String periodicalName;

    /**
     * 发刊时间
     */
    @NotBlank(message = "发刊时间不能为空", groups = {ADD.class, EDIT.class})
    private String time;

    /**
     * 发刊费用
     */
    @NotNull(message = "发刊费用不能为空", groups = {ADD.class, EDIT.class})
    private Double cost;

    /**
     * 发刊周期
     */
    @NotBlank(message = "发刊周期不能为空", groups = {ADD.class, EDIT.class})
    private String cycle;

    /**
     * 企业文化信息id
     */
    private String infoId;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getIda() {
        return ida;
    }

    public void setIda(String ida) {
        this.ida = ida;
    }

    public PublicizeWay getWay() {
        return way;
    }

    public void setWay(PublicizeWay way) {
        this.way = way;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getExecuter() {
        return executer;
    }

    public void setExecuter(String executer) {
        this.executer = executer;
    }

    public String getPeriodicalName() {
        return periodicalName;
    }

    public void setPeriodicalName(String periodicalName) {
        this.periodicalName = periodicalName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }
}