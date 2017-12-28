package com.bjike.goddess.lendreimbursement.vo.lendreimshape;


import java.io.Serializable;
import java.util.List;

/**
 * 年/周/月公司的柱状图和饼型图
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 年/周/月公司的柱状图和饼型图 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimCompanyMixShapeVO implements Serializable {

    /**
     * 公司的柱状图
     */
    private ReimCompanyShapeBarVO companyShapeBarVO;


    /**
     * 公司的饼型图
     */
    private ReimShapeVO reimShapeVO;


    public ReimCompanyShapeBarVO getCompanyShapeBarVO() {
        return companyShapeBarVO;
    }

    public void setCompanyShapeBarVO(ReimCompanyShapeBarVO companyShapeBarVO) {
        this.companyShapeBarVO = companyShapeBarVO;
    }

    public ReimShapeVO getReimShapeVO() {
        return reimShapeVO;
    }

    public void setReimShapeVO(ReimShapeVO reimShapeVO) {
        this.reimShapeVO = reimShapeVO;
    }
}