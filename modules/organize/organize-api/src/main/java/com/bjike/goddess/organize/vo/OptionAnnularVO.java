package com.bjike.goddess.organize.vo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.organize.bo.LegendBO;
import com.bjike.goddess.organize.bo.SeriesAnnularBO;
import com.bjike.goddess.organize.bo.TooltipBO;

/**
 * 环形图
 *
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [转正图形展示传数对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OptionAnnularVO extends BaseBO {

    private TooltipBO tooltip;

    private LegendBO legend;

    private SeriesAnnularBO[] series;

    public TooltipBO getTooltip() {
        return tooltip;
    }

    public void setTooltip(TooltipBO tooltip) {
        this.tooltip = tooltip;
    }

    public LegendBO getLegend() {
        return legend;
    }

    public void setLegend(LegendBO legend) {
        this.legend = legend;
    }

    public SeriesAnnularBO[] getSeries() {
        return series;
    }

    public void setSeries(SeriesAnnularBO[] series) {
        this.series = series;
    }
}
