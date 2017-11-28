package com.bjike.goddess.task.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.task.enums.CollectSuitation;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-22 11:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectDTO extends BaseDTO {
    public interface COUNT {
    }

    /**
     * 项目id
     */
    @NotBlank(groups = CollectDTO.COUNT.class, message = "项目id不能为空")
    private String projectId;
    /**
     * 任务id
     */
    @NotNull(groups = CollectDTO.COUNT.class, message = "任务id不能为空")
    private String[] tablesId;
    /**
     * 是否需要固定表头
     */
    @NotNull(groups = CollectDTO.COUNT.class, message = "是否需要固定表头不能为空")
    private Boolean needFixed;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 汇总字段
     */
    private List<String> fields;
    /**
     * 汇总条件
     */
    private List<CollectSuitation> collectSuitations;
    /**
     * 类型值
     */
    private List<ValDTO> vals;
    /**
     * 汇总表头字段
     */
    private List<String> tableFields;

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

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<CollectSuitation> getCollectSuitations() {
        return collectSuitations;
    }

    public void setCollectSuitations(List<CollectSuitation> collectSuitations) {
        this.collectSuitations = collectSuitations;
    }

    public List<ValDTO> getVals() {
        return vals;
    }

    public void setVals(List<ValDTO> vals) {
        this.vals = vals;
    }

    public List<String> getTableFields() {
        return tableFields;
    }

    public void setTableFields(List<String> tableFields) {
        this.tableFields = tableFields;
    }

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

    public Boolean getNeedFixed() {
        return needFixed;
    }

    public void setNeedFixed(Boolean needFixed) {
        this.needFixed = needFixed;
    }
}
