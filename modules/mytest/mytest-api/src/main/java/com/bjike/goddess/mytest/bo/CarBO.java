package com.bjike.goddess.mytest.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 测试用类 汽车业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-16 11:13 ]
 * @Description: [ 测试用类 汽车业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CarBO extends BaseBO {

    /**
     * 速度
     */
    private String speed;

    /**
     * 颜色
     */
    private String color;

    /**
     * 品牌
     */
    private String brand;


    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "CarBO{" +
                "speed='" + speed + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}