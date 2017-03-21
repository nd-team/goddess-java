package com.bjike.goddess.headcount.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 业务成本计划组合业务传输对象
 *
 * @Author: [yewenbo]
 * @Date: [2017-03-16 17:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PlanCostsInteBO  extends BaseBO{
    /**
     * 总计划人工成本
     */
    private Double  total;

    /**
     * 公司整体平均工资水平
     */
    private Double  avg;

    /**
     * 公司总人数配置
     */
    private Integer  number;

    /**
     * 职能体系占比
     */
    private Double  funPercent;

    /**
     * 实施体系占比
     */
    private Double  implePercent;

    /**
     * 部门信息
     */
    private List<GroupInfoBO> setGroup;

    /**
     * 薪资区间信息
     */
    private List<IntervalInfoBO> setInterval;


    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getFunPercent() {
        return funPercent;
    }

    public void setFunPercent(Double funPercent) {
        this.funPercent = funPercent;
    }

    public Double getImplePercent() {
        return implePercent;
    }

    public void setImplePercent(Double implePercent) {
        this.implePercent = implePercent;
    }

    public List<GroupInfoBO> getSetGroup() {
        return setGroup;
    }

    public void setSetGroup(List<GroupInfoBO> setGroup) {
        this.setGroup = setGroup;
    }

    public List<IntervalInfoBO> getSetInterval() {
        return setInterval;
    }

    public void setSetInterval(List<IntervalInfoBO> setInterval) {
        this.setInterval = setInterval;
    }
}
