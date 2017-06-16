package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.supplier.enums.TimeType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 供应商汇总
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-14 11:48 ]
 * @Description: [ 供应商汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectSendTO extends BaseTO {

    /**
     * 地区
     */
    private String[] areas;


    /**
     * 间隔
     */
    @NotNull(message = "间隔不能为空", groups = {ADD.class, EDIT.class})
    private Integer interval;

    /**
     * 间隔时间类型
     */
    @NotNull(message = "间隔时间类型不能为空", groups = {ADD.class, EDIT.class})
    private TimeType timeInterval;

    /**
     * 汇总间隔
     */
    @NotNull(message = "汇总间隔不能为空", groups = {ADD.class, EDIT.class})
    private Integer collect;

    /**
     * 汇总间隔时间类型
     */
    @NotNull(message = "汇总间隔时间类型不能为空", groups = {ADD.class, EDIT.class})
    private TimeType collectInterval;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空", groups = {ADD.class, EDIT.class})
    private String description;

    /**
     * 发送邮箱
     */
    private String[] emails;

    /**
     * 是否全部发送
     */
    @NotNull(message = "是否全部发送不能为空", groups = {ADD.class, EDIT.class})
    private Boolean all;


    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public TimeType getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(TimeType timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public TimeType getCollectInterval() {
        return collectInterval;
    }

    public void setCollectInterval(TimeType collectInterval) {
        this.collectInterval = collectInterval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    public Boolean getAll() {
        return all;
    }

    public void setAll(Boolean all) {
        this.all = all;
    }

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }
}