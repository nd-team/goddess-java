package com.bjike.goddess.businessprojectmanage.vo;

import com.bjike.goddess.businessprojectmanage.bo.*;

/**
 * 项目实施进度图形展示传数对象
 *
 * @Author: [caiwenxian]
 * @Date: [2017-12-14 15:32]
 * @Description: [项目实施进度图形展示传数对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BCCollectEchartVO {
    /**
     * 标题
     */
    private TitleBO title;

    /**
     * 柱状图文字描述
     */
    private LegendBO legend;

    /**
     * 横坐标
     */
    private XAxisBO xAxis;

    /**
     * 纵坐标
     */
    private YAxisBO yAxis;

    /**
     * 柱状图数据
     */
    private SeriesBO[] series;
    /**
     * 悬停提示
     */
    private TooltipBO tooltipBO;

    public TooltipBO getTooltipBO() {
        return tooltipBO;
    }

    public void setTooltipBO(TooltipBO tooltipBO) {
        this.tooltipBO = tooltipBO;
    }

    public TitleBO getTitle() {
        return title;
    }

    public void setTitle(TitleBO title) {
        this.title = title;
    }

    public LegendBO getLegend() {
        return legend;
    }

    public void setLegend(LegendBO legend) {
        this.legend = legend;
    }

    public XAxisBO getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxisBO xAxis) {
        this.xAxis = xAxis;
    }

    public YAxisBO getyAxis() {
        return yAxis;
    }

    public void setyAxis(YAxisBO yAxis) {
        this.yAxis = yAxis;
    }

    public SeriesBO[] getSeries() {
        return series;
    }

    public void setSeries(SeriesBO[] series) {
        this.series = series;
    }
}
