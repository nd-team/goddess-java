package com.bjike.goddess.democraticmeet.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 会议纪要数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-03 11:22 ]
 * @Description: [ 会议纪要数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummaryDTO extends BaseDTO {

    /**
     * 会议记录人
     */
    private String recorder;

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }
}