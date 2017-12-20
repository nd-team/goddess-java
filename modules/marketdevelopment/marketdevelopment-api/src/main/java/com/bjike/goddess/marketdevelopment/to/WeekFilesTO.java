package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 周计划的表头数据
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 05:46 ]
 * @Description: [ 周计划的表头数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeekFilesTO extends BaseTO {

    /**
     * 周计划的周期id
     */
    private String weekCycleId;

    /**
     * 表头下标
     */
    private Integer tableIndex;

    /**
     * 表头
     */
    private String tableName;

    /**
     * 内容
     */
    private String context;

    /**
     * 父级id
     */
    private String fatherId;


    public String getWeekCycleId() {
        return weekCycleId;
    }

    public void setWeekCycleId(String weekCycleId) {
        this.weekCycleId = weekCycleId;
    }

    public Integer getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(Integer tableIndex) {
        this.tableIndex = tableIndex;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }


}