package com.bjike.goddess.lendreimbursement.vo.lendreimshape;


import java.io.Serializable;
import java.util.List;

/**
 * 日/周/月每个人的报销条形的情况
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 日/周/月每个人的报销条形的情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimShapeBarVO implements Serializable{

    /**
     * title
     */
    private ReimShapeTitleVO reimShapeTitleVO;
    /**
     * color
     */
    private List<String> colorList;

    /**
     * tooltip
     */
    private ReimShapeBarToolTipVO toolTipVO;

    /**
     * grid
     */
    private ReimShapeBarGridVO barGridVO;

    /**
     * xAxis
     */
    private ReimShapeXaxisVO xaxisVO;
    /**
     * yAxis
     */
    private ReimShapeYaxisVO yaxisVO;

    /**
     * series
     */
    private ReimShapeBarSeriesVO seriesVO;

    public ReimShapeTitleVO getReimShapeTitleVO() {
        return reimShapeTitleVO;
    }

    public void setReimShapeTitleVO(ReimShapeTitleVO reimShapeTitleVO) {
        this.reimShapeTitleVO = reimShapeTitleVO;
    }

    public List<String> getColorList() {
        return colorList;
    }

    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }

    public ReimShapeBarToolTipVO getToolTipVO() {
        return toolTipVO;
    }

    public void setToolTipVO(ReimShapeBarToolTipVO toolTipVO) {
        this.toolTipVO = toolTipVO;
    }

    public ReimShapeBarGridVO getBarGridVO() {
        return barGridVO;
    }

    public void setBarGridVO(ReimShapeBarGridVO barGridVO) {
        this.barGridVO = barGridVO;
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

    public ReimShapeBarSeriesVO getSeriesVO() {
        return seriesVO;
    }

    public void setSeriesVO(ReimShapeBarSeriesVO seriesVO) {
        this.seriesVO = seriesVO;
    }
}