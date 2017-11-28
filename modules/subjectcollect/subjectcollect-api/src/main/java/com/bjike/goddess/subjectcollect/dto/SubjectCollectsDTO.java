package com.bjike.goddess.subjectcollect.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
/**
* 科目汇总数据传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-26 02:42 ]
* @Description:	[ 科目汇总数据传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SubjectCollectsDTO extends BaseDTO {
    /**
     * 查询开始时间
     */
    private String startTime;

    /**
     * 查询结束时间
     */
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