package com.bjike.goddess.bonus.bo;

/**
 * 奖励处罚明细传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-10 10:37]
 * @Description: [ 奖励处罚明细传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DisciplineRecordDetailBO {

    /**
     * 发生时间
     */
    private String occurrence;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String group;

    /**
     * 姓名
     */
    private String username;

    /**
     * 工号
     */
    private String serialNumber;

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

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
}
