package com.bjike.goddess.attendance.dto.overtime;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 加班数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverWorkDTO extends BaseDTO {

    /**
     * 加班人员
     */
    private String overWorker;

    public String getOverWorker() {
        return overWorker;
    }

    public void setOverWorker(String overWorker) {
        this.overWorker = overWorker;
    }
}