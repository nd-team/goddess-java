package com.bjike.goddess.attendance.dto.overtime;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 补班设置数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-12 04:42 ]
 * @Description: [ 补班设置数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExtralOverWorkDayDTO extends BaseDTO {

    public interface TestAdd{}

    /**
     * 补班开始时间
     */
    @NotBlank(groups = {ExtralOverWorkDayDTO.TestAdd.class} , message = "补班开始时间不能为空")
    private String startTime;
    /**
     * 补班结束时间
     */
    @NotBlank(groups = {ExtralOverWorkDayDTO.TestAdd.class} , message = "补班结束时间不能为空")
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