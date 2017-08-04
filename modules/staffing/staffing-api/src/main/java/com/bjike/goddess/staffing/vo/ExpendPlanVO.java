package com.bjike.goddess.staffing.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 人工成本计划表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 11:47 ]
 * @Description: [ 人工成本计划表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExpendPlanVO {

    /**
     * id
     */
    private String id;
    /**
     * 时间
     */
    private String time;

    /**
     * 计划人工成本
     */
    private Double planExpend;

    /**
     * 公司整体平均工资水平
     */
    private Double avg;

    /**
     * 公司总人数配置
     */
    private Integer total;

    /**
     * 总经办成本占比
     */
    private String bossProportion;

    /**
     * 人工成本计划详细信息
     */
    private List<DetailVO> details;

    /**
     * 人工成本计划子表信息
     */
    private List<SonVO> planSons;

    public List<DetailVO> getDetails() {
        return details;
    }

    public void setDetails(List<DetailVO> details) {
        this.details = details;
    }

    public List<SonVO> getPlanSons() {
        return planSons;
    }

    public void setPlanSons(List<SonVO> planSons) {
        this.planSons = planSons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPlanExpend() {
        return planExpend;
    }

    public void setPlanExpend(Double planExpend) {
        this.planExpend = planExpend;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getBossProportion() {
        return bossProportion;
    }

    public void setBossProportion(String bossProportion) {
        this.bossProportion = bossProportion;
    }
}