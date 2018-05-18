package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.task.enums.DateType;
import com.bjike.goddess.task.enums.SortType;
import com.bjike.goddess.task.enums.TimeType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 汇总方案
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-18 04:33 ]
 * @Description: [ 汇总方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectSchemeTO extends BaseTO {
    public interface NOTICE{}
    public interface FIELD{}

    /**
     * 汇总方案
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "汇总方案不能为空")
    private String name;

    /**
     * 选中汇总表
     */
    @NotNull(groups = {ADD.class, EDIT.class,CollectSchemeTO.FIELD.class},message = "选中汇总表不能为空")
    private String[] tabless;

    /**
     * 汇总对象
     */
    @NotNull(groups = {ADD.class, EDIT.class,CollectSchemeTO.NOTICE.class},message = "汇总对象不能为空")
    private String[] collectObjects;

    /**
     * 汇总频率
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "汇总频率不能为空")
    private DateType collectType;

    /**
     * 提醒频率
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "提醒频率不能为空")
    private TimeType remindType;

    /**
     * 提醒间隔值
     */
//    @NotNull(groups = {ADD.class, EDIT.class},message = "提醒间隔值不能为空")
    private Integer remindVal;

    /**
     * 发送时间
     */
//    @NotBlank(groups = {ADD.class, EDIT.class},message = "发送时间不能为空")
    private String sendTime;

    /**
     * 是否启用
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "是否启用不能为空")
    private Boolean enable;

    /**
     * 是否抄送本部门
     */
    @NotNull(groups = {ADD.class, EDIT.class,CollectSchemeTO.NOTICE.class},message = "是否抄送本部门不能为空")
    private Boolean sendDepart;
    /**
     * 汇总表头排序
     */
//    @NotNull(groups = {ADD.class, EDIT.class},message = "汇总表头排序不能为空")
    private SortType sortType;
    /**
     * 字段
     */
    private List<String> fileds;

    public List<String> getFileds() {
        return fileds;
    }

    public void setFileds(List<String> fileds) {
        this.fileds = fileds;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTabless() {
        return tabless;
    }

    public void setTabless(String[] tabless) {
        this.tabless = tabless;
    }

    public String[] getCollectObjects() {
        return collectObjects;
    }

    public void setCollectObjects(String[] collectObjects) {
        this.collectObjects = collectObjects;
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