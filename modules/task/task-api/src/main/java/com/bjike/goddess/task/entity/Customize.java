package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.task.enums.DateType;
import com.bjike.goddess.task.enums.TimeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * 自定义汇总
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-25 15:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@javax.persistence.Table(name = "task_customize")
public class Customize extends BaseEntity {
    /**
     * 汇总表名称
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '汇总表名称' ", nullable = false)
    private String name;

    /**
     * 汇总项目id
     */
    @Column(columnDefinition = "TEXT COMMENT '项目id,多个' ", nullable = false)
    private String projectId;

    /**
     * 汇总任务id
     */
    @Column(columnDefinition = "TEXT COMMENT '表id,多个' ", nullable = false)
    private String tablesId;

    /**
     * 创建人
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '创建人(非id)' ", nullable = false)
    private String user;

    /**
     * 汇总对象
     */
    @Column(columnDefinition = "TEXT COMMENT '汇总对象' ")
    private String collectObjec;

    /**
     * 是否抄送本部门
     */
    @Column(name = "sendDepart", columnDefinition = "TINYINT(1)  COMMENT '是否抄送本部门'")
    private Boolean sendDepart;

    /**
     * 是否启用
     */
    @Column(name = "is_enable", columnDefinition = "TINYINT(1)  COMMENT '是否使用'")
    private Boolean enable;

    /**
     * 是否需要固定表头
     */
    @Column(name = "is_needFixed", columnDefinition = "TINYINT(1)  COMMENT '是否需要固定表头'", nullable = false)
    private Boolean needFixed;

    /**
     * 汇总频率
     */
    @Column(columnDefinition = "TINYINT(2) COMMENT '汇总频率' ")
    private DateType collectType;

    /**
     * 提醒频率
     */
    @Column(columnDefinition = "TINYINT(2) COMMENT '提醒频率' ")
    private TimeType remindType;
    /**
     * 提醒间隔值
     */
    @Column(columnDefinition = "INT(11) COMMENT '提醒间隔值'")
    private Integer dateVal;

    /**
     * 提醒时间
     */
    @Column(columnDefinition = "DATETIME COMMENT '提醒时间'")
    private LocalDateTime sendTime;

    /**
     * 上次发送时间
     */
    @Column(columnDefinition = "DATETIME COMMENT '上次发送时间'")
    private LocalDateTime lastTime;


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
     *
     * @return
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '新增汇总类型' ", nullable = false)
    private String type;

    /**
     * 新增汇总类型说明
     * 2018-01-05
     * @return
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '新增汇总类型说明' ", nullable = false)
    private String typeExplain;



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

    public Boolean getNeedFixed() {
        return needFixed;
    }

    public void setNeedFixed(Boolean needFixed) {
        this.needFixed = needFixed;
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

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }
}
