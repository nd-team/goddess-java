package com.bjike.goddess.dimission.vo;

/**
 * @Author: [dengjunren]
 * @Date: [2017-04-17 17:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DimissionReasonVO {

    /**
     * 离职类型
     */
    private String type;

    /**
     * 原因明细
     */
    private String detail;

    /**
     * 人员数量
     */
    private Integer number;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
