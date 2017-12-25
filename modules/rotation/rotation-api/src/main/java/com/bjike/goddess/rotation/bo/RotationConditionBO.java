package com.bjike.goddess.rotation.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 岗位轮换条件业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:41 ]
 * @Description: [ 岗位轮换条件业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RotationConditionBO extends BaseBO {

    /**
     * 轮换方式
     */
    private String way;

    /**
     * 条件
     */
    private String condition;


    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}