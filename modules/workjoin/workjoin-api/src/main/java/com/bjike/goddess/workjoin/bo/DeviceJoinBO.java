package com.bjike.goddess.workjoin.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 设备交接业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:57 ]
 * @Description: [ 设备交接业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DeviceJoinBO extends BaseBO {
    /**
     * 工作交接编号
     */
    private String workJoinNum;

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 关于设备使用的相关资料存储路径
     */
    private String devicePath;

    public String getWorkJoinNum() {
        return workJoinNum;
    }

    public void setWorkJoinNum(String workJoinNum) {
        this.workJoinNum = workJoinNum;
    }

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