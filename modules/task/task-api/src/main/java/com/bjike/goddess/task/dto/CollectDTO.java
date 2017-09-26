package com.bjike.goddess.task.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.task.enums.CollectType;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-22 11:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectDTO extends BaseDTO {
    /**
     * 项目
     */
    private String projectId;
    /**
     * 多个表(为空时查询所有表)
     */
    private String[] tablesId;
    /**
     * 是否需要固定表头
     */
    private boolean needFixed = true;

    /**
     * 自定义汇总字段
     */
    private String[] fields;
    /**
     * 自定义汇总类型
     */
    private CollectType type;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String[] getTablesId() {
        return tablesId;
    }

    public void setTablesId(String[] tablesId) {
        this.tablesId = tablesId;
    }

    public boolean isNeedFixed() {
        return needFixed;
    }

    public void setNeedFixed(boolean needFixed) {
        this.needFixed = needFixed;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public CollectType getType() {
        return type;
    }

    public void setType(CollectType type) {
        this.type = type;
    }
}
