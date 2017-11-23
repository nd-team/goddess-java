package com.bjike.goddess.lendreimbursement.vo.lendreimshape;


import java.io.Serializable;

/**
 * 日/周/月每个人的报销的情况
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 日/周/月每个人的报销的情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LendMixReimShapeVO implements Serializable{

    /**
     * 柱型图
     */
    private ReimShapeMixVO reimShapeMixVO;
    /**
     * 饼状图
     */
    private ReimShapeVO reimShapeVO;

    public ReimShapeMixVO getReimShapeMixVO() {
        return reimShapeMixVO;
    }

    public void setReimShapeMixVO(ReimShapeMixVO reimShapeMixVO) {
        this.reimShapeMixVO = reimShapeMixVO;
    }

    public ReimShapeVO getReimShapeVO() {
        return reimShapeVO;
    }

    public void setReimShapeVO(ReimShapeVO reimShapeVO) {
        this.reimShapeVO = reimShapeVO;
    }
}