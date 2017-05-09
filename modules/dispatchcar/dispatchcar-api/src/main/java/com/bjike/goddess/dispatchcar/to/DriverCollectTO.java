package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.dispatchcar.enums.Acctype;

import javax.validation.constraints.NotNull;

/**
 * 财务司机汇总
 *
 * @Author: [Jason]
 * @Date: [17-4-18 上午9:09]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DriverCollectTO extends BaseTO {

    public interface SelectCollect {
    }

    /**
     * 年份
     */
    @NotNull(message = "年份不能为空!", groups = {DriverCollectTO.SelectCollect.class})
    private Integer year;

    /**
     * 月份
     */
    @NotNull(message = "月份不能为空!", groups = {DriverCollectTO.SelectCollect.class})
    private Integer month;

    /**
     * 司机
     */
    private String driver;

    /**
     * 用车人
     */
    private String carUser;

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

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getCarUser() {
        return carUser;
    }

    public void setCarUser(String carUser) {
        this.carUser = carUser;
    }
}
