package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-10-18 11:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ObjectBO extends BaseBO {
    /**
     * 实际时长
     */
    private Double actualTime;
    /**
     * 实际时长时间类型
     */
    private Integer actualType;
    /**
     * 完成状态
     */
    private Integer finishStatus;

    public Integer getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
    }

    public Double getActualTime() {
        return actualTime;
    }

    public void setActualTime(Double actualTime) {
        this.actualTime = actualTime;
    }

    public Integer getActualType() {
        return actualType;
    }

    public void setActualType(Integer actualType) {
        this.actualType = actualType;
    }
}
