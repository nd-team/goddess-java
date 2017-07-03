package com.bjike.goddess.supplier.vo;

/**
 * 合作情况表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:05:37.655 ]
 * @Description: [ 合作情况表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CooperationSituationVO {

    /**
     * id
     */
    private String id;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public String getName() {
        return name;
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