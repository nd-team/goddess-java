package com.bjike.goddess.task.vo;

import com.bjike.goddess.task.enums.DateType;
import com.bjike.goddess.task.enums.TimeType;

import java.util.List;

/**
 * 自定义汇总
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CustomizeVO {
    /**
     * id
     */
    private String id;
    /**
     * 汇总表名称
     */
    private String name;
    /**
     * 项目id
     */
    private String projectId;

    /**
     * 项目
     */
    private String project;

    /**
     * 汇总任务数组
     */
    private String[] tables;
    /**
     * 汇总任务
     */
    private String table;
    /**
     * 汇总任务id
     */
    private String tablesId;
    /**
     * 创建人
     */
    private String user;
    /**
     * 是否抄送本部门
     */
    private Boolean sendDepart;

    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * 是否需要固定表头
     */
    private Boolean needFixed;
    /**
     * 汇总对象
     */
    private String collectObjec;
    /**
     * 汇总频率
     */
    private DateType collectType;

    /**
     * 提醒频率
     */
    private TimeType remindType;
    /**
     * 提醒间隔值
     */
    private Integer dateVal;

    /**
     * 提醒时间
     */
    private String sendTime;

    /**
     * 上次发送时间
     */
    private String lastTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 子表
     */
    private List<CustomizeSonVO> sons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getNeedFixed() {
        return needFixed;
    }

    public void setNeedFixed(Boolean needFixed) {
        this.needFixed = needFixed;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String[] getTables() {
        return tables;
    }

    public void setTables(String[] tables) {
        this.tables = tables;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTablesId() {
        return tablesId;
    }

    public void setTablesId(String tablesId) {
        this.tablesId = tablesId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCollectObjec() {
        return collectObjec;
    }

    public void setCollectObjec(String collectObjec) {
        this.collectObjec = collectObjec;
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

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public List<CustomizeSonVO> getSons() {
        return sons;
    }

    public void setSons(List<CustomizeSonVO> sons) {
        this.sons = sons;
    }
}
