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
public class ReimShapeAllVO implements Serializable{

    /**
     * 报销情况指标统计(累计记录百分比)
     */
    private ReimShapeVO reimConIndexPercent;

    /**
     * 报销金额指标统计(累计金额百分比)
     */
    private ReimShapeVO reimMoneyIndexPercent;

    /**
     * 报销金额统计(累计金额条形显示)
     */
    private ReimShapeBarVO reimShapeBarVO;

    public ReimShapeVO getReimConIndexPercent() {
        return reimConIndexPercent;
    }

    public void setReimConIndexPercent(ReimShapeVO reimConIndexPercent) {
        this.reimConIndexPercent = reimConIndexPercent;
    }

    public ReimShapeVO getReimMoneyIndexPercent() {
        return reimMoneyIndexPercent;
    }

    public void setReimMoneyIndexPercent(ReimShapeVO reimMoneyIndexPercent) {
        this.reimMoneyIndexPercent = reimMoneyIndexPercent;
    }

    public ReimShapeBarVO getReimShapeBarVO() {
        return reimShapeBarVO;
    }

    public void setReimShapeBarVO(ReimShapeBarVO reimShapeBarVO) {
        this.reimShapeBarVO = reimShapeBarVO;
    }
}