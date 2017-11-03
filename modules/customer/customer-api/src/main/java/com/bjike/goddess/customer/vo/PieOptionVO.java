package com.bjike.goddess.customer.vo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.customer.bo.LegendBO;
import com.bjike.goddess.customer.bo.PieSeriesBO;
import com.bjike.goddess.customer.bo.TitleBO;
import com.bjike.goddess.customer.bo.TooltipBO;

/**
 * 客户信息饼状图图形展示传数对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [客户信息饼状图图形展示传数对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PieOptionVO{
    /**
     * 标题
     */
    private TitleBO title;

    /**
     * 柱状图文字描述
     */
    private LegendBO legend;

    /**
     * 柱状图数据
     */
    private PieSeriesBO series;

    /**
     * 柱状图数据
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

    public PieSeriesBO getSeries() {
        return series;
    }

    public void setSeries(PieSeriesBO series) {
        this.series = series;
    }

    public TooltipBO getTooltip() {
        return tooltip;
    }

    public void setTooltip(TooltipBO tooltip) {
        this.tooltip = tooltip;
    }
}
