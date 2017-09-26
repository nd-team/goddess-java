package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 人员标准工时汇总子表
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 18:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TimeSonBO extends BaseBO {
    /**
     * 项目组/部门
     */
    private String depart;
    /**
     * 无差异人数
     */
    private Long noDiffer;
    /**
     * 有差异人数
     */
    private Long haveDiffer;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public Long getNoDiffer() {
        return noDiffer;
    }

    public void setNoDiffer(Long noDiffer) {
        this.noDiffer = noDiffer;
    }

    public Long getHaveDiffer() {
        return haveDiffer;
    }

    public void setHaveDiffer(Long haveDiffer) {
        this.haveDiffer = haveDiffer;
    }
}
