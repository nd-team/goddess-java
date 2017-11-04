package com.bjike.goddess.contractware.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 转正图形展示传数对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [转正图形展示传数对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OptionBO extends BaseBO{
    /**
     * 标题
     */
    private TitleBO title;

    /**
     * 柱状图文字描述
     */
    private LegendBO legend;

    private ToolTipBO tooltip;

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

    public ToolTipBO getTooltip() {
        return tooltip;
    }

    public void setTooltip(ToolTipBO tooltip) {
        this.tooltip = tooltip;
    }
}
