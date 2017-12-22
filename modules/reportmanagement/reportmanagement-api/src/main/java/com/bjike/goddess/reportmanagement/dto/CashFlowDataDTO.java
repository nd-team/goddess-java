package com.bjike.goddess.reportmanagement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.entity.EDIT;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 现金流量资料表数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 03:02 ]
 * @Description: [ 现金流量资料表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashFlowDataDTO extends BaseDTO {

    /**
     * 补充资料Id
     */
    private String dataId;


    /**
     * 开始时间
     */
    @NotBlank(message = "开始时间不能为空", groups = {EDIT.class})
    private String startTime;

    /**
     * 结束时间
     */
    @NotBlank(message = "结束时间不能为空", groups = {EDIT.class})
    private String endTime;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空", groups = {EDIT.class})
    private Double money;

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

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}