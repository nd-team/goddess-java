package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.organize.enums.IntervalType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by ike on 17-9-7.
 */
public class EmailTO extends BaseTO {
    /**
     * 项目组/部门
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "项目组/部门不能为空")
    private String[] departs;
    /**
     * 是否发送至本项目组所有人
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否发送至本项目组所有人不能为空")
    private Boolean sendAll;
    /**
     * 间隔类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "间隔类型不能为空")
    private IntervalType it;
    /**
     * 间隔时长
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "间隔时长不能为空")
    private Integer itTime;

    /**
     * 发送时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "发送时间不能为空")
    private String setTime;
    /**
     * 发送对象
     */
    private String[] sendObjects;

    public String[] getDeparts() {
        return departs;
    }

    public void setDeparts(String[] departs) {
        this.departs = departs;
    }

    public Boolean getSendAll() {
        return sendAll;
    }

    public void setSendAll(Boolean sendAll) {
        this.sendAll = sendAll;
    }

    public IntervalType getIt() {
        return it;
    }

    public void setIt(IntervalType it) {
        this.it = it;
    }

    public Integer getItTime() {
        return itTime;
    }

    public void setItTime(Integer itTime) {
        this.itTime = itTime;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String[] getSendObjects() {
        return sendObjects;
    }

    public void setSendObjects(String[] sendObjects) {
        this.sendObjects = sendObjects;
    }
}
