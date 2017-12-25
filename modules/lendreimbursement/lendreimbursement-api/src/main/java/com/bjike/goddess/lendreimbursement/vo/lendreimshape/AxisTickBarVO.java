package com.bjike.goddess.lendreimbursement.vo.lendreimshape;


import java.io.Serializable;
import java.util.List;

/**
 * 日/周/月每个人的报销的情况
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 日/周/月每个人的报销的情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AxisTickBarVO implements Serializable {

    /**
     * type
     */
    private Boolean alignWidthLablel;

    public Boolean getAlignWidthLablel() {
        return alignWidthLablel;
    }

    public void setAlignWidthLablel(Boolean alignWidthLablel) {
        this.alignWidthLablel = alignWidthLablel;
    }

    public AxisTickBarVO(Boolean alignWidthLablel) {
        this.alignWidthLablel = alignWidthLablel;
    }

    public AxisTickBarVO() {
    }
}