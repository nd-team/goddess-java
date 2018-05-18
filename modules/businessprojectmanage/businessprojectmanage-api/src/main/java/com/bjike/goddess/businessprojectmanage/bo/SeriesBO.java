package com.bjike.goddess.businessprojectmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 转正图形展示数据传输对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [转正图形展示数据传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SeriesBO extends BaseBO{
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * 数据
     */
    private String[] data;

    /**
     * 间隔
     */
    private String barGap;

    public SeriesBO(String name, String type, String[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }


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

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
