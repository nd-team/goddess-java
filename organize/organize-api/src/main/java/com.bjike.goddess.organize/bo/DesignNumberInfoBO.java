package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.time.LocalDateTime;

/**
 * 编号设计信息传输对象
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:26]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
public class DesignNumberInfoBO extends BaseBO {

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 分类
     */
    private String classify;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;


    /**
     * 创建时间
     */
    private String createTime;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
