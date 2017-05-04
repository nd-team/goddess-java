package com.bjike.goddess.workjoin.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 设备交接
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:57 ]
 * @Description: [ 设备交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "workjoin_devicejoin")
public class DeviceJoin extends BaseEntity {

    /**
     * 设备编号
     */
    @Column(name = "deviceNum", columnDefinition = "VARCHAR(255)   COMMENT '设备编号'")
    private String deviceNum;

    /**
     * 设备名称
     */
    @Column(name = "deviceName", columnDefinition = "VARCHAR(255)   COMMENT '设备名称'")
    private String deviceName;

    /**
     * 关于设备使用的相关资料存储路径
     */
    @Column(name = "devicePath", columnDefinition = "VARCHAR(255)   COMMENT '关于设备使用的相关资料存储路径'")
    private String devicePath;


    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDevicePath() {
        return devicePath;
    }

    public void setDevicePath(String devicePath) {
        this.devicePath = devicePath;
    }
}