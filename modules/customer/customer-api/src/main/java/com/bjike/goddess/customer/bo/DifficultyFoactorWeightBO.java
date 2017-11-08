package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 难易度因素层权重业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:20 ]
 * @Description: [ 难易度因素层权重业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DifficultyFoactorWeightBO extends BaseBO {

    /**
     * 困难度类型名称
     */
    private String difficName;

    /**
     * 困难度类型权重
     */
    private Double difficWeight;


    public String getDifficName() {
        return difficName;
    }

    public void setDifficName(String difficName) {
        this.difficName = difficName;
    }

    public Double getDifficWeight() {
        return difficWeight;
    }

    public void setDifficWeight(Double difficWeight) {
        this.difficWeight = difficWeight;
    }
}