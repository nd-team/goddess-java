package com.bjike.goddess.attendance.dto.overtime;

import com.bjike.goddess.attendance.enums.AuditStatus;
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
public class PhoneMyOverWorkDTO extends BaseDTO {

    public interface TESTLIST{}

    /**
     * 加班人员
     */
    @NotBlank(groups = {PhoneMyOverWorkDTO.TESTLIST.class} , message = "加班人员不能为空")
    private String overWorker;

    public String getOverWorker() {
        return overWorker;
    }

    public void setOverWorker(String overWorker) {
        this.overWorker = overWorker;
    }
}