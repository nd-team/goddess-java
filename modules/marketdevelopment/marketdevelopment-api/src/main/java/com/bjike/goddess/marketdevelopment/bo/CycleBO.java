package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 周期业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 03:05 ]
 * @Description: [ 周期业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CycleBO extends BaseBO {

    /**
     * 业务方向科目id
     */
    private String subjectDataId;

    /**
     * 周期
     */
    private String cycle;

    private List<DateDataBO> dateDataVOs;

    /**
     * 合计
     */
    private String total;


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

    public List<DateDataBO> getDateDataVOs() {
        return dateDataVOs;
    }

    public void setDateDataVOs(List<DateDataBO> dateDataVOs) {
        this.dateDataVOs = dateDataVOs;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}