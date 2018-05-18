package com.bjike.goddess.lendreimbursement.vo.lendreimshape;


import java.io.Serializable;

/**
 * 日/周/月每个人的报销的情况饼型图
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 日/周/月每个人的报销的情况饼型图 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimShapeVO implements Serializable{

    /**
     * title
     */
    private ReimShapeTitleVO titleVO;

    /**
     * tooltip
     */
    private ReimShapeToolTipVO toolTipVO;

    /**
     * legend
     */
    private ReimShapeLegendVO rlegendVO;

    /**
     * series
     */
    private ReimShapeSeriesVO seriesVO;

    public ReimShapeTitleVO getTitleVO() {
        return titleVO;
    }

    public void setTitleVO(ReimShapeTitleVO titleVO) {
        this.titleVO = titleVO;
    }

    public ReimShapeToolTipVO getToolTipVO() {
        return toolTipVO;
    }

    public void setToolTipVO(ReimShapeToolTipVO toolTipVO) {
        this.toolTipVO = toolTipVO;
    }

    public ReimShapeLegendVO getRlegendVO() {
        return rlegendVO;
    }

    public void setRlegendVO(ReimShapeLegendVO rlegendVO) {
        this.rlegendVO = rlegendVO;
    }

    public ReimShapeSeriesVO getSeriesVO() {
        return seriesVO;
    }

    public void setSeriesVO(ReimShapeSeriesVO seriesVO) {
        this.seriesVO = seriesVO;
    }
}