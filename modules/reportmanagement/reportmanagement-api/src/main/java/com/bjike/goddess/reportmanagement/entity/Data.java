package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.reportmanagement.enums.DataType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 补充资料表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 02:29 ]
 * @Description: [ 补充资料表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_data")
public class Data extends BaseEntity {

    /**
     * 补充资料
     */
    @Column(name = "data", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补充资料'")
    private String data;

    /**
     * 补充资料类型
     */
    @Column(name = "dataType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补充资料类型'")
    private DataType dataType;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}