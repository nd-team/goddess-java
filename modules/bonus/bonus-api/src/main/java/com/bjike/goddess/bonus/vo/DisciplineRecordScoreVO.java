package com.bjike.goddess.bonus.vo;

/**
 * 奖励处罚分数汇总表现层对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-10 10:47]
 * @Description: [ 奖励处罚分数汇总表现层对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DisciplineRecordScoreVO {

    /**
     * 开始时间
     */
    private String start;

    /**
     * 结束时间
     */
    private String end;

    /**
     * 项目组
     */
    private String department;

    /**
     * 地区
     */
    private String area;

    /**
     * 奖罚指标
     */
    private String target;

    /**
     * 奖励总次数
     */
    private Integer reward;

    /**
     * 奖励总分
     */
    private Double rewardTotal;

    /**
     * 处罚次数
     */
    private Integer push;

    /**
     * 处罚总分
     */
    private Double pushTotal;

    /**
     * 最后得分
     */
    private Double total;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Double getRewardTotal() {
        return rewardTotal;
    }

    public void setRewardTotal(Double rewardTotal) {
        this.rewardTotal = rewardTotal;
    }

    public Integer getPush() {
        return push;
    }

    public void setPush(Integer push) {
        this.push = push;
    }

    public Double getPushTotal() {
        return pushTotal;
    }

    public void setPushTotal(Double pushTotal) {
        this.pushTotal = pushTotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
