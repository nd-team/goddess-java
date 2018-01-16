package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 转正图形展示数据传输对象
 *
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [转正图形展示数据传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SeriesBO extends BaseBO {

    public SeriesBO() {

    }

    public SeriesBO(Integer[] data) {
        this.data = data;
    }

    public SeriesBO(Integer[] data, String name) {
        this.data = data;
        this.name = name;
    }

    /**
     * 名称
     */
    private String name = "邮件营销";
    /**
     * 类型
     */
    private String type = "line";
    /**
     * 数据
     */
    private Integer[] data = {12, 12, 234, 545, 123, 55};


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer[] getData() {
        return data;
    }

    public void setData(Integer[] data) {
        this.data = data;
    }
}
