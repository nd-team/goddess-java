package com.bjike.goddess.supplier.vo;

/**
 * 获奖情况表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:53:15.055 ]
 * @Description: [ 获奖情况表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RewardSituationVO {

    /**
     * id
     */
    private String id;
    /**
     * 供应商基本信息
     */
    private String information_id;

    /**
     * 获奖名称
     */
    private String name;

    /**
     * 获得时间
     */
    private String acquisition;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInformation_id() {
        return information_id;
    }

    public void setInformation_id(String information_id) {
        this.information_id = information_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcquisition() {
        return acquisition;
    }

    public void setAcquisition(String acquisition) {
        this.acquisition = acquisition;
    }
}