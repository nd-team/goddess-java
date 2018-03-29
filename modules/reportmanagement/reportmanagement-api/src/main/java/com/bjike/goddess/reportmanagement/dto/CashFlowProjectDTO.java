package com.bjike.goddess.reportmanagement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.entity.EDIT;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 现金流量项目表数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 03:06 ]
 * @Description: [ 现金流量项目表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashFlowProjectDTO extends BaseDTO {

    public interface LIST {}

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 开始时间
     */
    @NotBlank(message = "开始时间不能为空", groups = {EDIT.class, LIST.class})
    private String startTime;

    /**
     * 结束时间
     */
    @NotBlank(message = "结束时间不能为空", groups = {EDIT.class, LIST.class})
    private String endTime;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空", groups = {EDIT.class})
    private Double money;

    /*
    * 是否获取最新数据
    */
    private boolean lastest;

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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public boolean isLastest() {
        return lastest;
    }

    public void setLastest(boolean lastest) {
        this.lastest = lastest;
    }
}