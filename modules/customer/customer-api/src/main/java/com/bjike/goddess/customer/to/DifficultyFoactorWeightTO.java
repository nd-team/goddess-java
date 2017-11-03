package com.bjike.goddess.customer.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 难易度因素层权重
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:20 ]
 * @Description: [ 难易度因素层权重 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DifficultyFoactorWeightTO extends BaseTO {

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