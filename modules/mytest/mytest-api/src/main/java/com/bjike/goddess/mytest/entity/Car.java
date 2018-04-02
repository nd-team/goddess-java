package com.bjike.goddess.mytest.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 测试用类 汽车
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-16 11:13 ]
 * @Description: [ 测试用类 汽车 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "mytest_car")
public class Car extends BaseEntity {

    /**
     * 速度
     */
    @Column(name = "speed", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '速度'")
    private String speed;

    /**
     * 颜色
     */
    @Column(name = "color", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '颜色'")
    private String color;

    /**
     * 品牌
     */
    @Column(name = "brand", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '品牌'")
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
        return "Car{" +
                "speed='" + speed + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}