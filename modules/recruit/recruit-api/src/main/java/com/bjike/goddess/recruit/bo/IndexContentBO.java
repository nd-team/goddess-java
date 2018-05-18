package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 考核指标内容业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-14 02:46 ]
 * @Description: [ 考核指标内容业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IndexContentBO extends BaseBO {

    /**
     * 对赌标签名
     */
    private String name;

    /**
     * 标签权重
     */
    private Integer weight;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}