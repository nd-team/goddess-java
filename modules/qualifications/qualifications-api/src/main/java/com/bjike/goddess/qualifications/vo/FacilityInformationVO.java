package com.bjike.goddess.qualifications.vo;

/**
 * 设备信息表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:59 ]
 * @Description: [ 设备信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FacilityInformationVO {

    /**
     * id
     */
    private String id;
    /**
     * 设备名称
     */
    private String name;

    /**
     * 清单
     */
    private String fictitious;

    /**
     * 真实/虚拟
     */
    private Boolean real;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFictitious() {
        return fictitious;
    }

    public void setFictitious(String fictitious) {
        this.fictitious = fictitious;
    }

    public Boolean getReal() {
        return real;
    }

    public void setReal(Boolean real) {
        this.real = real;
    }
}