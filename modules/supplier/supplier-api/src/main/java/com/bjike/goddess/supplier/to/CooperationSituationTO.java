package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 合作情况
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:05:37.654 ]
 * @Description: [ 合作情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CooperationSituationTO extends BaseTO {

    /**
     * 合作情况id数组
     */
    private String[] cooperation_ids;

    /**
     * 公司名称
     */
    private String[] name;

    /**
     * 产品/服务内容
     */
    private String[] product;

    /**
     * 合作时间
     */
    private String[] cooperationTime;

    /**
     * 合作期限
     */
    private String[] cooperationTerm;


    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getProduct() {
        return product;
    }

    public void setProduct(String[] product) {
        this.product = product;
    }

    public String[] getCooperationTime() {
        return cooperationTime;
    }

    public void setCooperationTime(String[] cooperationTime) {
        this.cooperationTime = cooperationTime;
    }

    public String[] getCooperationTerm() {
        return cooperationTerm;
    }

    public void setCooperationTerm(String[] cooperationTerm) {
        this.cooperationTerm = cooperationTerm;
    }

    public String[] getCooperation_ids() {
        return cooperation_ids;
    }

    public void setCooperation_ids(String[] cooperation_ids) {
        this.cooperation_ids = cooperation_ids;
    }
}