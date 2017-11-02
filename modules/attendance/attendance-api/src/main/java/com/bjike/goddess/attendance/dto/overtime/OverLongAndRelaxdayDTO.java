package com.bjike.goddess.attendance.dto.overtime;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 加班数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverLongAndRelaxdayDTO extends BaseDTO {

    public interface TestAdd{}

    /**
     * 加班开始时间
     */
    @NotBlank(groups = {OverLongAndRelaxdayDTO.TestAdd.class} , message = "加班开始时间不能为空")
    private String startTime;
    /**
     * 加班结束时间
     */
    @NotBlank(groups = {OverLongAndRelaxdayDTO.TestAdd.class} , message = "加班结束时间不能为空")
    private String endTime;

    /**
     * 是否午休
     */
    @NotNull(groups = {OverLongAndRelaxdayDTO.TestAdd.class} , message = "是否午休不能为空")
    private Boolean noonBreakOr;

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

    public Boolean getNoonBreakOr() {
        return noonBreakOr;
    }

    public void setNoonBreakOr(Boolean noonBreakOr) {
        this.noonBreakOr = noonBreakOr;
    }
}