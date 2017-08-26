package com.bjike.goddess.outcarfare.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * 等待付款数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 03:11 ]
 * @Description: [ 等待付款数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WaitPayDTO extends BaseDTO {
    public interface DRIVER{}
    public interface Arrival{}
    public interface CARUSER{}
    /**
     * 司机数组
     */
    @NotNull(groups = WaitPayDTO.DRIVER.class,message = "司机数组不能为空")
    private String[] drivers;
    /**
     * 地区数组
     */
    @NotNull(groups = WaitPayDTO.Arrival.class,message = "地区数组不能为空")
    private String[] arrivals;
    /**
     * 用车人数组
     */
    @NotNull(groups = WaitPayDTO.CARUSER.class,message = "用车人数组不能为空")
    private String[] carUsers;

    public String[] getDrivers() {
        return drivers;
    }

    public void setDrivers(String[] drivers) {
        this.drivers = drivers;
    }

    public String[] getArrivals() {
        return arrivals;
    }

    public void setArrivals(String[] arrivals) {
        this.arrivals = arrivals;
    }

    public String[] getCarUsers() {
        return carUsers;
    }

    public void setCarUsers(String[] carUsers) {
        this.carUsers = carUsers;
    }
}