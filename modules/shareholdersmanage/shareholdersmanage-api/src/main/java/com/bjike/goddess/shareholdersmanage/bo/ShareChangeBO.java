package com.bjike.goddess.shareholdersmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 股东变更业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:37 ]
 * @Description: [ 股东变更业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareChangeBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 变更前股东姓名
     */
    private String changeBeforeName;

    /**
     * 变更后股东姓名
     */
    private String changeAfterName;

    /**
     * 变更时间
     */
    private String changeDate;

    /**
     * 备注
     */
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getChangeBeforeName() {
        return changeBeforeName;
    }

    public void setChangeBeforeName(String changeBeforeName) {
        this.changeBeforeName = changeBeforeName;
    }

    public String getChangeAfterName() {
        return changeAfterName;
    }

    public void setChangeAfterName(String changeAfterName) {
        this.changeAfterName = changeAfterName;
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