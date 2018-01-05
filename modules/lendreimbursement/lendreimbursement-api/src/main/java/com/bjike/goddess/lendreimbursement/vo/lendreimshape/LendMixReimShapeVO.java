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

    /**
     * 公司柱型图
     */
    private ReimCompanyShapeBarVO companyShapeMixVO;

    public ReimShapeMixVO getReimShapeMixVO() {
        return reimShapeMixVO;
    }

    public void setReimShapeMixVO(ReimShapeMixVO reimShapeMixVO) {
        this.reimShapeMixVO = reimShapeMixVO;
    }

    public ReimCompanyShapeBarVO getCompanyShapeMixVO() {
        return companyShapeMixVO;
    }

    public void setCompanyShapeMixVO(ReimCompanyShapeBarVO companyShapeMixVO) {
        this.companyShapeMixVO = companyShapeMixVO;
    }


    public ReimShapeVO getReimShapeVO() {
        return reimShapeVO;
    }

    public void setReimShapeVO(ReimShapeVO reimShapeVO) {
        this.reimShapeVO = reimShapeVO;
    }
}