package com.bjike.goddess.supplier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 合作情况业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:05:37.653 ]
 * @Description: [ 合作情况业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CooperationSituationBO extends BaseBO {

    /**
     * 供应商基本信息
     */
    private String informationId;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 产品服务内容
     */
    private String product;

    /**
     * 合作时间
     */
    private String cooperationTime;

    /**
     * 合作期限
     */
    private String cooperationTerm;


    public String getName() {
        return name;
    }

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCooperationTime() {
        return cooperationTime;
    }

    public void setCooperationTime(String cooperationTime) {
        this.cooperationTime = cooperationTime;
    }

    public String getCooperationTerm() {
        return cooperationTerm;
    }

    public void setCooperationTerm(String cooperationTerm) {
        this.cooperationTerm = cooperationTerm;
    }
}