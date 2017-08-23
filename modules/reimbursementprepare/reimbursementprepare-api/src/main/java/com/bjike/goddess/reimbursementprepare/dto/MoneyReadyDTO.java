package com.bjike.goddess.reimbursementprepare.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 资金准备数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-03 03:07 ]
 * @Description: [ 资金准备数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyReadyDTO extends BaseDTO {
    public interface COUNT{
    }
    /**
     * 开始时间
     */
    @NotBlank(groups = {MoneyReadyDTO.COUNT.class}, message = "开始时间不能为空")
    private String startTime;
    /**
     * 结束时间
     */
    @NotBlank(groups = {MoneyReadyDTO.COUNT.class}, message = "结束时间不能为空")
    private String endTime;

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
}