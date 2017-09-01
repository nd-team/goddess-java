package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 股权赠与
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:08 ]
 * @Description: [ 股权赠与 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EquityGiftTO extends BaseTO {

    /**
     * 地区
     */
    private String area;

    /**
     * 受赠人
     */
    private String donee;

    /**
     * 赠与人
     */
    private String donor;

    /**
     * 股权类型
     */
    private String equityType;

    /**
     * 赠与日期
     */
    private String heirDate;

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

    public String getDonee() {
        return donee;
    }

    public void setDonee(String donee) {
        this.donee = donee;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public String getHeirDate() {
        return heirDate;
    }

    public void setHeirDate(String heirDate) {
        this.heirDate = heirDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}