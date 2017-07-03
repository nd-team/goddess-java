package com.bjike.goddess.supplier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 获奖情况业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:53:15.050 ]
 * @Description: [ 获奖情况业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RewardSituationBO extends BaseBO {

    /**
     * 供应商基本信息id
     */
    private String informationId;

    /**
     * 获奖名称
     */
    private String name;

    /**
     * 获得时间
     */
    private String acquisition;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public String getAcquisition() {
        return acquisition;
    }

    public void setAcquisition(String acquisition) {
        this.acquisition = acquisition;
    }
}