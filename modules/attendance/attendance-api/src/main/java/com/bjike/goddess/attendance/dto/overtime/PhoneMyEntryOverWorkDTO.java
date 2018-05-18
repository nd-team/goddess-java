package com.bjike.goddess.attendance.dto.overtime;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 加班数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneMyEntryOverWorkDTO extends BaseDTO {

    public interface TESTLIST{}

    /**
     * 录入人姓名
     */
    @NotBlank(groups = {PhoneMyEntryOverWorkDTO.TESTLIST.class} , message = "录入人姓名不能为空")
    private String entryer;


    public String getEntryer() {
        return entryer;
    }

    public void setEntryer(String entryer) {
        this.entryer = entryer;
    }
}