package com.bjike.goddess.task.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.task.enums.DateType;
import com.bjike.goddess.task.enums.SortType;
import com.bjike.goddess.task.enums.TimeType;

import java.util.List;

/**
 * 汇总方案业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-18 04:33 ]
 * @Description: [ 汇总方案业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectSchemeBO extends BaseBO {

    /**
     * 汇总方案
     */
    private String name;

    /**
     * 选中汇总表
     */
    private String tables;

    /**
     * 选中汇总表数组
     */
    private String[] table;

    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 汇总对象
     */
    private String collectObject;

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
    private Integer remindVal;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 上次发送时间
     */
    private String lastTime;

    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * 是否抄送本部门
     */
    private Boolean sendDepart;

    private List<SchemeSonBO> sons;
    /**
     * 汇总表头排序
     */
    private SortType sortType;

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public List<SchemeSonBO> getSons() {
        return sons;
    }

    public void setSons(List<SchemeSonBO> sons) {
        this.sons = sons;
    }


    public String[] getTable() {
        return table;
    }

    public void setTable(String[] table) {
        this.table = table;
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

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCollectObject() {
        return collectObject;
    }

    public void setCollectObject(String collectObject) {
        this.collectObject = collectObject;
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

    public Integer getRemindVal() {
        return remindVal;
    }

    public void setRemindVal(Integer remindVal) {
        this.remindVal = remindVal;
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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getSendDepart() {
        return sendDepart;
    }

    public void setSendDepart(Boolean sendDepart) {
        this.sendDepart = sendDepart;
    }
}