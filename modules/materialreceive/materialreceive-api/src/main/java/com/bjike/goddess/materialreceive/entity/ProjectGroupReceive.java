package com.bjike.goddess.materialreceive.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 项目组领用归还
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:52 ]
 * @Description: [ 项目组领用归还 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialreceive_projectgroupreceive")
public class ProjectGroupReceive extends BaseEntity {

    /**
     * 领用时间
     */
    @Column(name = "receiveTime", nullable = false, columnDefinition = "DATETIME COMMENT '领用时间'")
    private LocalDateTime receiveTime;

    /**
     * 物品名称
     */
    @Column(name = "materialName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '物品名称'")
    private String materialName;

    /**
     * 物资领用编号
     */
    @Column(name = "materialNo", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '物资领用编号'")
    private String materialNo;

    /**
     * 数量
     */
    @Column(name = "quantity", nullable = false, columnDefinition = "INT(11) COMMENT '数量'")
    private Integer quantity;

    /**
     * 领用人
     */
    @Column(name = "recipient", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '领用人'")
    private String recipient;

    /**
     * 发放人
     */
    @Column(name = "issuer", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '发放人'")
    private String issuer;

    /**
     * 归还时间
     */
    @Column(name = "returnTime", columnDefinition = "DATETIME COMMENT '归还时间'")
    private LocalDateTime returnTime;

    /**
     * 归还人
     */
    @Column(name = "returnPerson", columnDefinition = "VARCHAR(255) COMMENT '归还人'")
    private String returnPerson;


    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnPerson() {
        return returnPerson;
    }

    public void setReturnPerson(String returnPerson) {
        this.returnPerson = returnPerson;
    }
}