package com.bjike.goddess.rotation.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 岗位轮换条件
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:41 ]
 * @Description: [ 岗位轮换条件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RotationConditionTO extends BaseTO {

    /**
     * 轮换方式
     */
    @NotBlank(message = "轮换方式不能为空", groups = {ADD.class, EDIT.class})
    private String way;

    /**
     * 条件
     */
    @NotBlank(message = "条件不能为空", groups = {ADD.class, EDIT.class})
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