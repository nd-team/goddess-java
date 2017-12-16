package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 周期
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 03:05 ]
 * @Description: [ 周期 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CycleTO extends BaseTO {

    /**
     * 业务方向科目id
     */
    private String subjectDataId;

    /**
     * 周期
     */
    private String cycle;


    public String getSubjectDataId() {
        return subjectDataId;
    }

    public void setSubjectDataId(String subjectDataId) {
        this.subjectDataId = subjectDataId;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
}