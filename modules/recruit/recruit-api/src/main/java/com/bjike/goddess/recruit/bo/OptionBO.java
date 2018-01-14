package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.enterprise.inject.New;

/**
 * 转正图形展示传数对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [转正图形展示传数对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OptionBO extends BaseBO{

    public OptionBO() {

    }

    public OptionBO(SeriesBO[] series) {
        this.series = series;
    }
    /**
     * 标题
     */
    private TitleBO title = new TitleBO();

    /**
     * 柱状图文字描述
     */
    private LegendBO legend = new LegendBO();

    /**
     * 横坐标
     */
    private XAxisBO xAxis = new XAxisBO();

    /**
     * 纵坐标
     */
    private YAxisBO yAxis = new YAxisBO();

    /**
     * 柱状图数据
     */
    private SeriesBO[] series = {new SeriesBO(),new SeriesBO()};

    /**
     * 柱状图数据
     */
    private TooltipBO tooltip = new TooltipBO();

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

    public TooltipBO getTooltip() {
        return tooltip;
    }

    public void setTooltip(TooltipBO tooltip) {
        this.tooltip = tooltip;
    }
}
