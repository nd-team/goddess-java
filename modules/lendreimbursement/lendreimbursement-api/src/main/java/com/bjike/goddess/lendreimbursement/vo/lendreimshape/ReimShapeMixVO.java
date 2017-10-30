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
public class ReimShapeMixVO implements Serializable{

    /**
     * title
     */
    private ReimShapeTitleVO titleVO;

    /**
     * tooltip
     */
    private ReimShapeMixToolTipVO toolTipVO;

    /**
     * legend
     */
    private ReimShapeLegendVO rlegendVO;

    /**
     * grid
     */
    private ReimShapeBarGridVO gridVO;

    /**
     * AxisPointer
     */
    private ReimShapeXaxisVO xaxisVO;

    /**
     * AyisPointer
     */
    private ReimShapeYaxisVO yaxisVO;

    /**
     * series
     */
    private List<ReimShapeMixSeriesVO> seriesVO;

    public ReimShapeTitleVO getTitleVO() {
        return titleVO;
    }

    public void setTitleVO(ReimShapeTitleVO titleVO) {
        this.titleVO = titleVO;
    }

    public ReimShapeMixToolTipVO getToolTipVO() {
        return toolTipVO;
    }

    public void setToolTipVO(ReimShapeMixToolTipVO toolTipVO) {
        this.toolTipVO = toolTipVO;
    }

    public ReimShapeLegendVO getRlegendVO() {
        return rlegendVO;
    }

    public void setRlegendVO(ReimShapeLegendVO rlegendVO) {
        this.rlegendVO = rlegendVO;
    }

    public ReimShapeBarGridVO getGridVO() {
        return gridVO;
    }

    public void setGridVO(ReimShapeBarGridVO gridVO) {
        this.gridVO = gridVO;
    }

    public ReimShapeXaxisVO getXaxisVO() {
        return xaxisVO;
    }

    public void setXaxisVO(ReimShapeXaxisVO xaxisVO) {
        this.xaxisVO = xaxisVO;
    }

    public ReimShapeYaxisVO getYaxisVO() {
        return yaxisVO;
    }

    public void setYaxisVO(ReimShapeYaxisVO yaxisVO) {
        this.yaxisVO = yaxisVO;
    }

    public List<ReimShapeMixSeriesVO> getSeriesVO() {
        return seriesVO;
    }

    public void setSeriesVO(List<ReimShapeMixSeriesVO> seriesVO) {
        this.seriesVO = seriesVO;
    }
}