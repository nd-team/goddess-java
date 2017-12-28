//package com.bjike.goddess.marketdevelopment.entity;
//
//import com.bjike.goddess.common.api.entity.BaseEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//
///**
// * 市场测算
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 07:19 ]
// * @Description: [ 市场测算 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@Entity
//@Table(name = "marketdevelopment_market_measure")
//public class MarketMeasure extends BaseEntity {
//
//    /**
//     * 业务类型
//     */
//    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
//    private String type;
//
//    /**
//     * 业务方向科目
//     */
//    @Column(name = "course", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
//    private String course;
//
//    /**
//     * 资金投入
//     */
//    @Column(name = "capital", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '资金投入'")
//    private Double capital;
//
//    /**
//     * 人员成本(元)
//     */
//    @Column(name = "staffCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '人员成本(元)'")
//    private Double staffCost;
//
//    /**
//     * 设备成本(元)
//     */
//    @Column(name = "equipmentCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '设备成本(元)'")
//    private Double equipmentCost;
//
//    /**
//     * 车辆成本(元)
//     */
//    @Column(name = "carCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '车辆成本(元)'")
//    private Double carCost;
//
//    /**
//     * 人员投入
//     */
//    @Column(name = "staff", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '人员投入'")
//    private Double staff;
//
//    /**
//     * 设备投入
//     */
//    @Column(name = "equipment", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '设备投入'")
//    private Double equipment;
//
//    /**
//     * 车辆投入
//     */
//    @Column(name = "car", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '车辆投入'")
//    private Double car;
//
//    /**
//     * 备注
//     */
//    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
//    private String remark;
//
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getCourse() {
//        return course;
//    }
//
//    public void setCourse(String course) {
//        this.course = course;
//    }
//
//    public Double getCapital() {
//        return capital;
//    }
//
//    public void setCapital(Double capital) {
//        this.capital = capital;
//    }
//
//    public Double getStaffCost() {
//        return staffCost;
//    }
//
//    public void setStaffCost(Double staffCost) {
//        this.staffCost = staffCost;
//    }
//
//    public Double getEquipmentCost() {
//        return equipmentCost;
//    }
//
//    public void setEquipmentCost(Double equipmentCost) {
//        this.equipmentCost = equipmentCost;
//    }
//
//    public Double getCarCost() {
//        return carCost;
//    }
//
//    public void setCarCost(Double carCost) {
//        this.carCost = carCost;
//    }
//
//    public Double getStaff() {
//        return staff;
//    }
//
//    public void setStaff(Double staff) {
//        this.staff = staff;
//    }
//
//    public Double getEquipment() {
//        return equipment;
//    }
//
//    public void setEquipment(Double equipment) {
//        this.equipment = equipment;
//    }
//
//    public Double getCar() {
//        return car;
//    }
//
//    public void setCar(Double car) {
//        this.car = car;
//    }
//
//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//}