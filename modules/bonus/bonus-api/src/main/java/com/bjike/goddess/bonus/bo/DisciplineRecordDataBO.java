package com.bjike.goddess.bonus.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 奖罚记录业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-10 11:54 ]
 * @Description: [ 奖罚记录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DisciplineRecordDataBO extends BaseBO {

    /**
     * 季度
     */
    private String quarter;

    /**
     * 分数
     */
    private Double ballot;

    /**
     * 奖励或处罚
     */
    private Boolean status;

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public Double getBallot() {
        return ballot;
    }

    public void setBallot(Double ballot) {
        this.ballot = ballot;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}