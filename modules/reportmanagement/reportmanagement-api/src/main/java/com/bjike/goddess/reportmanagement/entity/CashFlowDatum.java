package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 补充资料
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-22 11:54 ]
 * @Description: [ 补充资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_cashflowdatum")
public class CashFlowDatum extends BaseEntity {

    /**
     * 序号
     */
    @Column(name = "", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '序号'")
    private Integer seqNum;

    /**
     * 项目id
     */
    @Column(name = "dataId", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '项目id'")
    private String dataId;

    /**
     * 补充资料
     */
    @Column(name = "data", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '补充资料'")
    private String data;

    /**
     * 行次
     */
    @Column(name = "", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '行次'")
    private Integer num;

    /**
     * 金额
     */
    @Column(name = "money", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double money;

    /**
     * 开始时间
     */
    @Column(name = "startTime", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '开始时间'")
    private String startTime;

    /**
     * 结束时间
     */
    @Column(name = "endTime", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '结束时间'")
    private String endTime;


    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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