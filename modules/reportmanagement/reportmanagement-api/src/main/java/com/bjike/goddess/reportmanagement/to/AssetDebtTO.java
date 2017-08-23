package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 资产负债总表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:37 ]
 * @Description: [ 资产负债总表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssetDebtTO extends BaseTO {

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 对应的行次
     */
    private Integer num;


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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}