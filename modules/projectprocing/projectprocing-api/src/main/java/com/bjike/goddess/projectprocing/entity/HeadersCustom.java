package com.bjike.goddess.projectprocing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectprocing.enums.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 表头定制
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 表头定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectprocing_headerscustom")
public class HeadersCustom extends BaseEntity {

    /**
     * 外包单位
     */
    @Column(name = "outUnit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '外包单位'")
    private String outUnit;

    /**
     * 进度管理id
     */
    @Column(name = "prossManageId", columnDefinition = "VARCHAR(255)   COMMENT '进度管理id'")
    private String prossManageId;

    /**
     * 父级id
     */
    @Column(name = "fatherId", columnDefinition = "VARCHAR(255)   COMMENT '父级id'")
    private String fatherId;

    /**
     * 表头
     */
    @Column(name = "header",nullable = false,  columnDefinition = "VARCHAR(255)   COMMENT '表头'")
    private String header;

    /**
     * 类型
     */
    @Column(name = "types",nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private Types types;

    /**
     * 是否为必填
     */
    @Column(name = "is_requiredFill",nullable = false,columnDefinition = "TINYINT(1)  COMMENT '是否为必填'")
    private Boolean requiredFill;

    /**
     * 字段内容
     */
    @Column(name = "content", columnDefinition = "VARCHAR(255)   COMMENT '字段内容'")
    private String content;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;




    public String getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getProssManageId() {
        return prossManageId;
    }

    public void setProssManageId(String prossManageId) {
        this.prossManageId = prossManageId;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public Types getTypes() {
        return types;
    }

    public void setTypes(Types types) {
        this.types = types;
    }

    public Boolean getRequiredFill() {
        return requiredFill;
    }

    public void setRequiredFill(Boolean requiredFill) {
        this.requiredFill = requiredFill;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}