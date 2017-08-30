package com.bjike.goddess.festival.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 注意事项数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:19 ]
 * @Description: [ 注意事项数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NoticeThingDTO extends BaseDTO {

    public interface TESTFindDetail{}
    /**
     * 节假日方案id
     */
    @NotBlank(groups = {NoticeThingDTO.TESTFindDetail.class},message = "节假日方案id不能为空")
    private String holidayProgrammeId;

    public String getHolidayProgrammeId() {
        return holidayProgrammeId;
    }

    public void setHolidayProgrammeId(String holidayProgrammeId) {
        this.holidayProgrammeId = holidayProgrammeId;
    }
}