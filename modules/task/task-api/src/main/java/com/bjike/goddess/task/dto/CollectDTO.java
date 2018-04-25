package com.bjike.goddess.task.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.task.enums.CollectSuitation;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
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

    /**
     * 新增汇总类型
     * 2018-01-05
     *      目前只有2中汇总类型
     *          1.明细汇总
     *              --今日完成情况 1
     *              --明日完成情况 2
     *          2.数量汇总
     *              --日周月      1
     *              --自定义      2
     */
    private String type;

    /**
     * 新增汇总说明
     * 2018-01-05
     *      目前只有2中汇总类型
     *          1.明细汇总
     *              --今日完成情况 1
     *              --明日完成情况 2
     *          2.数量汇总
     *              --日周月      1
     *              --自定义      2
     */
    private String typeExplain;

    /**
     * 新增 可以多选项目 区分以前单选
     *
     */
    private List<String> projectIds;

    /**
     * 新增 任务ID
     * projectIds[0]：xxx
     * --tabs[0].tables[0]：xxx
     * --tabs[0].tables[1]：xxx
     */
    private List<TableDTO> tabs;

    public List<String> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<String> projectIds) {
        this.projectIds = projectIds;
    }

    public List<TableDTO> getTabs() {
        return tabs;
    }

    public void setTabs(List<TableDTO> tabs) {
        this.tabs = tabs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeExplain() {
        return typeExplain;
    }

    public void setTypeExplain(String typeExplain) {
        this.typeExplain = typeExplain;
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

    @Override
    public String toString() {
        return "CollectDTO{" +
                "projectId='" + projectId + '\'' +
                ", tablesId=" + Arrays.toString(tablesId) +
                ", needFixed=" + needFixed +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", fields=" + fields +
                ", collectSuitations=" + collectSuitations +
                ", vals=" + vals +
                ", tableFields=" + tableFields +
                ", type='" + type + '\'' +
                ", typeExplain='" + typeExplain + '\'' +
                ", projectIds=" + projectIds +
                ", tabs=" + tabs +
                '}';
    }
}
