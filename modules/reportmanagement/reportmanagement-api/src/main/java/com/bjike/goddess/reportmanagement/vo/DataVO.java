package com.bjike.goddess.reportmanagement.vo;

import com.bjike.goddess.reportmanagement.enums.DataType;

/**
 * 补充资料表表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 02:29 ]
 * @Description: [ 补充资料表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DataVO {

    /**
     * id
     */
    private String id;
    /**
     * 补充资料
     */
    private String data;

    /**
     * 补充资料类型
     */
    private DataType dataType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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