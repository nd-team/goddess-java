package com.bjike.goddess.foreigntax.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [xiazhili]
 * @Date: [2017-10-16 15:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class IncomeCollectDTO extends BaseDTO {
    public interface collect{}
    /**
     * 开始时间
     */
    @NotBlank(message = "开始时间不能为空",groups = {IncomeCollectDTO.collect.class})
    private String startTime;
    /**
     * 结束时间
     */
    @NotBlank(message = "结束时间不能为空",groups = {IncomeCollectDTO.collect.class})
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
