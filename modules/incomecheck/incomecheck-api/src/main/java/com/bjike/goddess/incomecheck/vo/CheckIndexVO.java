package com.bjike.goddess.incomecheck.vo;

/**
 * 指标设置表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:49 ]
 * @Description: [ 指标设置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CheckIndexVO {

    /**
     * id
     */
    private String id;
    /**
     * 收入比例差异指标
     */
    private Double incomeRate;

    /**
     * 收入比例差异指标
     */
    private Double completeRate;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(Double incomeRate) {
        this.incomeRate = incomeRate;
    }

    public Double getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(Double completeRate) {
        this.completeRate = completeRate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}