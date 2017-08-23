package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.dispatchcar.enums.Acctype;

import javax.validation.constraints.NotNull;

/**
 * 财务地区汇总
 *
 * @Author: [Jason]
 * @Date: [17-4-18 上午9:09]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaCollectTO extends BaseTO {

    public interface SelectCollect {
    }

    /**
     * 年份
     */
    @NotNull(message = "年份不能为空!", groups = {AreaCollectTO.SelectCollect.class})
    private Integer year;

    /**
     * 月份
     */
    @NotNull(message = "月份不能为空!", groups = {AreaCollectTO.SelectCollect.class})
    private Integer month;

    /**
     * 地区
     */
    private String area;

    /**
     * 科目
     */
    private Acctype acctype;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Acctype getAcctype() {
        return acctype;
    }

    public void setAcctype(Acctype acctype) {
        this.acctype = acctype;
    }

}
