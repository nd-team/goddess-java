package com.bjike.goddess.attendance.dto;

import com.bjike.goddess.attendance.enums.CountType;
import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 请假管理数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VacateDTO extends BaseDTO {
    public interface COUNT{}
    /**
     * 汇总类型
     */
    @NotNull(groups = VacateDTO.COUNT.class,message = "汇总类型不能为空")
    private CountType countType;
    /**
     * 部门数组
     */
    private String[] departs;
    /**
     * 开始时间
     */
    @NotBlank(groups = VacateDTO.COUNT.class,message = "开始时间不能为空")
    private String startTime;
    /**
     * 结束时间
     */
    @NotBlank(groups = VacateDTO.COUNT.class,message = "结束时间不能为空")
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

    public CountType getCountType() {
        return countType;
    }

    public void setCountType(CountType countType) {
        this.countType = countType;
    }

    public String[] getDeparts() {
        return departs;
    }

    public void setDeparts(String[] departs) {
        this.departs = departs;
    }
}