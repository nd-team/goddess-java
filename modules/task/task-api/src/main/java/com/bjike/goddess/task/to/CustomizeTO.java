package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.task.dto.ValDTO;
import com.bjike.goddess.task.enums.CollectSuitation;
import com.bjike.goddess.task.enums.DateType;
import com.bjike.goddess.task.enums.TimeType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 定制化
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CustomizeTO extends BaseTO {
    public interface NOTICE {
    }

    public interface VALUE{}
    public interface FIELD{}

    /**
     * 汇总名
     */
    @NotBlank(message = "汇总名不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 项目id
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目id不能为空")
    private String projectId;
    /**
     * 任务id
     */
    @NotNull(groups = {ADD.class, EDIT.class,CustomizeTO.VALUE.class,CustomizeTO.FIELD.class}, message = "任务id不能为空")
    private String[] tablesId;
    /**
     * 是否需要固定表头
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否需要固定表头不能为空")
    private Boolean needFixed;

    /**
     * 汇总对象
     */
    @NotNull(groups = {CustomizeTO.NOTICE.class}, message = "汇总对象不能为空")
    private String[] collectObjecs;

    /**
     * 是否抄送本部门
     */
    @NotNull(groups = {CustomizeTO.NOTICE.class}, message = "是否抄送本部门不能为空")
    private Boolean sendDepart;

    /**
     * 是否启用
     */
    @NotNull(groups = {CustomizeTO.NOTICE.class}, message = "是否启用不能为空")
    private Boolean enable;

    /**
     * 汇总频率
     */
    @NotNull(groups = {CustomizeTO.NOTICE.class}, message = "汇总频率不能为空")
    private DateType collectType;

    /**
     * 提醒频率
     */
    @NotNull(groups = {CustomizeTO.NOTICE.class}, message = "提醒频率不能为空")
    private TimeType remindType;
    /**
     * 提醒间隔值
     */
    @NotNull(groups = {CustomizeTO.NOTICE.class}, message = "提醒间隔值不能为空")
    private Integer dateVal;

    /**
     * 提醒时间
     */
    @NotBlank(groups = {CustomizeTO.NOTICE.class}, message = "提醒时间不能为空")
    private String sendTime;

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
     * 字段
     */
    @NotBlank(groups = {CustomizeTO.VALUE.class}, message = "字段不能为空")
    private String field;

    /**
     * 条件
     */
    @NotNull(groups = {CustomizeTO.VALUE.class}, message = "条件不能为空")
    private CollectSuitation collectSuitation;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public CollectSuitation getCollectSuitation() {
        return collectSuitation;
    }

    public void setCollectSuitation(CollectSuitation collectSuitation) {
        this.collectSuitation = collectSuitation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCollectObjecs() {
        return collectObjecs;
    }

    public void setCollectObjecs(String[] collectObjecs) {
        this.collectObjecs = collectObjecs;
    }

    public Boolean getSendDepart() {
        return sendDepart;
    }

    public void setSendDepart(Boolean sendDepart) {
        this.sendDepart = sendDepart;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public DateType getCollectType() {
        return collectType;
    }

    public void setCollectType(DateType collectType) {
        this.collectType = collectType;
    }

    public TimeType getRemindType() {
        return remindType;
    }

    public void setRemindType(TimeType remindType) {
        this.remindType = remindType;
    }

    public Integer getDateVal() {
        return dateVal;
    }

    public void setDateVal(Integer dateVal) {
        this.dateVal = dateVal;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
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
