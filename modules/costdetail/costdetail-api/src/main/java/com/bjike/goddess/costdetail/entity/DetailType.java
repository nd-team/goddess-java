package com.bjike.goddess.costdetail.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 明细分类
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-07 09:43 ]
 * @Description: [ 明细分类 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "costdetail_detailtype")
public class DetailType extends BaseEntity {

    /**
     * 主科目
     */
    @Column(name = "parNode", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '父节点'")
    private String parNode;

    /**
     * 二级科目
     */
    @Column(name = "typeName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分类名'")
    private String typeName;

    /**
     * 描述
     */
    @Column(name = "descs", columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String descs;


    public String getParNode() {
        return parNode;
    }

    public void setParNode(String parNode) {
        this.parNode = parNode;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}