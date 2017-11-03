package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 亲密度因素层设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:01 ]
 * @Description: [ 亲密度因素层设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_closenessfoactorset")
public class ClosenessFoactorSet extends BaseEntity {

    /**
     * 一般一般比
     */
    @Column(name = "generalGeneral", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '一般一般比'")
    private Double generalGeneral;

    /**
     * 一般亲密比
     */
    @Column(name = "generalClose", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '一般亲密比'")
    private Double generalClose;

    /**
     * 一般十分亲密比
     */
    @Column(name = "generalVeryClose", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '一般十分亲密比'")
    private Double generalVeryClose;

    /**
     * 亲密一般比
     */
    @Column(name = "closeGeneral", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '亲密一般比'")
    private Double closeGeneral;

    /**
     * 亲密亲密比
     */
    @Column(name = "closeClose", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '亲密亲密比'")
    private Double closeClose;

    /**
     * 亲密十分亲密比
     */
    @Column(name = "closeVeryClose", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '亲密十分亲密比'")
    private Double closeVeryClose;

    /**
     * 十分亲密一般比
     */
    @Column(name = "veryCloseGeneral", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '十分亲密一般比'")
    private Double veryCloseGeneral;

    /**
     * 十分亲密亲密比
     */
    @Column(name = "veryCloseClose", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '十分亲密亲密比'")
    private Double veryCloseClose;

    /**
     * 十分亲密十分亲密比
     */
    @Column(name = "veryCloseVeryClose", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '十分亲密十分亲密比'")
    private Double veryCloseVeryClose;


    public Double getGeneralGeneral() {
        return generalGeneral;
    }

    public void setGeneralGeneral(Double generalGeneral) {
        this.generalGeneral = generalGeneral;
    }

    public Double getGeneralClose() {
        return generalClose;
    }

    public void setGeneralClose(Double generalClose) {
        this.generalClose = generalClose;
    }

    public Double getGeneralVeryClose() {
        return generalVeryClose;
    }

    public void setGeneralVeryClose(Double generalVeryClose) {
        this.generalVeryClose = generalVeryClose;
    }

    public Double getCloseGeneral() {
        return closeGeneral;
    }

    public void setCloseGeneral(Double closeGeneral) {
        this.closeGeneral = closeGeneral;
    }

    public Double getCloseClose() {
        return closeClose;
    }

    public void setCloseClose(Double closeClose) {
        this.closeClose = closeClose;
    }

    public Double getCloseVeryClose() {
        return closeVeryClose;
    }

    public void setCloseVeryClose(Double closeVeryClose) {
        this.closeVeryClose = closeVeryClose;
    }

    public Double getVeryCloseGeneral() {
        return veryCloseGeneral;
    }

    public void setVeryCloseGeneral(Double veryCloseGeneral) {
        this.veryCloseGeneral = veryCloseGeneral;
    }

    public Double getVeryCloseClose() {
        return veryCloseClose;
    }

    public void setVeryCloseClose(Double veryCloseClose) {
        this.veryCloseClose = veryCloseClose;
    }

    public Double getVeryCloseVeryClose() {
        return veryCloseVeryClose;
    }

    public void setVeryCloseVeryClose(Double veryCloseVeryClose) {
        this.veryCloseVeryClose = veryCloseVeryClose;
    }
}