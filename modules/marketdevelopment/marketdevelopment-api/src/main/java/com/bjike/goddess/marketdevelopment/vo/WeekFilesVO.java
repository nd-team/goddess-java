package com.bjike.goddess.marketdevelopment.vo;

import java.util.List;

/**
 * 周计划的表头数据表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 05:46 ]
 * @Description: [ 周计划的表头数据表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeekFilesVO {

    /**
     * id
     */
    private String id;
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

    private List<WeekFilesVO> weekFilesVOs;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<WeekFilesVO> getWeekFilesVOs() {
        return weekFilesVOs;
    }

    public void setWeekFilesVOs(List<WeekFilesVO> weekFilesVOs) {
        this.weekFilesVOs = weekFilesVOs;
    }
}