package com.bjike.goddess.shareholdersmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 变更股权类型业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:42 ]
 * @Description: [ 变更股权类型业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ChangeEquityTypeBO extends BaseBO {

    /**
     * 变更前股权类型
     */
    private String changeBeforeType;

    /**
     * 变更后股权类型
     */
    private String changeAfterType;

    /**
     * 变更时间
     */
    private String changeDate;

    /**
     * 备注
     */
    private String remark;


    public String getChangeBeforeType() {
        return changeBeforeType;
    }

    public void setChangeBeforeType(String changeBeforeType) {
        this.changeBeforeType = changeBeforeType;
    }

    public String getChangeAfterType() {
        return changeAfterType;
    }

    public void setChangeAfterType(String changeAfterType) {
        this.changeAfterType = changeAfterType;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}