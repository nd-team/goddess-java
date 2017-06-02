package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 编号设计信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-31 09:49 ]
 * @Description: [ 编号设计信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "organize_design_number_info")
public class DesignNumberInfo extends BaseEntity {

    /**
     * 操作对象
     */
    @Column(name = "serialNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '操作对象'")
    private String serialNumber;

    /**
     * 编号信息类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '编号信息类型'")
    private String type;

    /**
     * 举例名称
     */
    @Column(name = "illustrate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '举例名称'")
    private String illustrate;

    /**
     * 举例编号
     */
    @Column(name = "illustrateNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '举例编号'")
    private String illustrateNumber;

    /**
     * 编号信息生成说明
     */
    @Column(name = "description", columnDefinition = "VARCHAR(255)   COMMENT '编号信息生成说明'")
    private String description;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public String getIllustrateNumber() {
        return illustrateNumber;
    }

    public void setIllustrateNumber(String illustrateNumber) {
        this.illustrateNumber = illustrateNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}