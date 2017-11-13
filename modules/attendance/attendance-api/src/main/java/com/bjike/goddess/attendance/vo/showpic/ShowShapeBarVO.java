package com.bjike.goddess.attendance.vo.showpic;


import java.io.Serializable;
import java.util.List;

/**
 * 考勤首页条形的情况
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 考勤首页条形的情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShowShapeBarVO implements Serializable{

    /**
     * title
     */
    private ShowShapeTitleVO showShapeTitleVO;
    /**
     * color
     */
    private List<String> colorList;
    /**
     * legend
     */
    private ShowShapeLegendVO rlegendVO;

    /**
     * tooltip
     */
    private ShowShapeBarToolTipVO toolTipVO;

    /**
     * grid
     */
    private ShowShapeBarGridVO barGridVO;

    /**
     * xAxis
     */
    private ShowShapeXaxisVO xaxisVO;
    /**
     * yAxis
     */
    private ShowShapeYaxisVO yaxisVO;

    /**
     * series
     */
    private List<ShowShapeBarSeriesVO> seriesVOs;

    public ShowShapeTitleVO getShowShapeTitleVO() {
        return showShapeTitleVO;
    }

    public void setShowShapeTitleVO(ShowShapeTitleVO showShapeTitleVO) {
        this.showShapeTitleVO = showShapeTitleVO;
    }

    public List<String> getColorList() {
        return colorList;
    }

    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }

    public ShowShapeLegendVO getRlegendVO() {
        return rlegendVO;
    }

    public void setRlegendVO(ShowShapeLegendVO rlegendVO) {
        this.rlegendVO = rlegendVO;
    }

    public ShowShapeBarToolTipVO getToolTipVO() {
        return toolTipVO;
    }

    public void setToolTipVO(ShowShapeBarToolTipVO toolTipVO) {
        this.toolTipVO = toolTipVO;
    }

    public ShowShapeBarGridVO getBarGridVO() {
        return barGridVO;
    }

    public void setBarGridVO(ShowShapeBarGridVO barGridVO) {
        this.barGridVO = barGridVO;
    }

    public ShowShapeXaxisVO getXaxisVO() {
        return xaxisVO;
    }

    public void setXaxisVO(ShowShapeXaxisVO xaxisVO) {
        this.xaxisVO = xaxisVO;
    }

    public ShowShapeYaxisVO getYaxisVO() {
        return yaxisVO;
    }

    public void setYaxisVO(ShowShapeYaxisVO yaxisVO) {
        this.yaxisVO = yaxisVO;
    }

    public List<ShowShapeBarSeriesVO> getSeriesVOs() {
        return seriesVOs;
    }

    public void setSeriesVOs(List<ShowShapeBarSeriesVO> seriesVOs) {
        this.seriesVOs = seriesVOs;
    }
}