package com.bjike.goddess.projectprocing.vo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.projectprocing.bo.*;

/**
 * 项目结算验收签字展示传数对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [项目结算验收签字展示传数对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OptionAmountVO {
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
    private SeriesAmountBO[] series;
    /**
     * 悬停提示属性
     */
    private TooltipBO tooltip;

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

    public SeriesAmountBO[] getSeries() {
        return series;
    }

    public void setSeries(SeriesAmountBO[] series) {
        this.series = series;
    }

    public TooltipBO getTooltip() {
        return tooltip;
    }

    public void setTooltip(TooltipBO tooltip) {
        this.tooltip = tooltip;
    }
}
