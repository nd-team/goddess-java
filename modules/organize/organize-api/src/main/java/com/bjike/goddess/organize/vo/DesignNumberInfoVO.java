package com.bjike.goddess.organize.vo;

/**
 * 编号设计信息表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-31 09:49 ]
 * @Description: [ 编号设计信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DesignNumberInfoVO {

    /**
     * id
     */
    private String id;
    /**
     * 操作对象
     */
    private String serialNumber;

    /**
     * 编号信息类型
     */
    private String type;

    /**
     * 举例名称
     */
    private String illustrate;

    /**
     * 举例编号
     */
    private String illustrateNumber;

    /**
     * 编号信息生成说明
     */
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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