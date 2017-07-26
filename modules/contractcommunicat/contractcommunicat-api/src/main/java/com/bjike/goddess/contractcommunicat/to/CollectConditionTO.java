package com.bjike.goddess.contractcommunicat.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;

/**
 * 项目承包洽谈
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.913 ]
 * @Description: [ 项目承包洽谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectConditionTO extends BaseTO {

    /**
     * 内部项目名称
     */
    private String contractInProject;

    /**
     * 汇总间隔
     */

    private QuartzCycleType quartzCycleType;

    /**
     * 汇总间隔数组
     */

    private String interval;
    /**
     * 创建开始范围时间
     */
    private String startTime;
    /**
     * 创建结束范围时间
     */
    private String endTime;

    public String getContractInProject() {
        return contractInProject;
    }

    public void setContractInProject(String contractInProject) {
        this.contractInProject = contractInProject;
    }

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

    public QuartzCycleType getQuartzCycleType() {
        return quartzCycleType;
    }

    public void setQuartzCycleType(QuartzCycleType quartzCycleType) {
        this.quartzCycleType = quartzCycleType;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}