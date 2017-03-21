package com.bjike.goddess.headcount.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 部门信息业务数据传输
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-13 09:34 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupInfoTO extends BaseTO{
    /**
     * 部门名称
     */
    @NotBlank(message = "部门名不能为空",groups = ADD.class)
    private String name;

    /**
     * 计划成本数
     */
    private Double plancostnum;

    /**
     * 人工成本计划id
     */
    private String planCosts_id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPlancostnum() {
        return plancostnum;
    }

    public void setPlancostnum(Double plancostnum) {
        this.plancostnum = plancostnum;
    }

    public String getPlanCosts_id() {
        return planCosts_id;
    }

    public void setPlanCosts_id(String planCosts_id) {
        this.planCosts_id = planCosts_id;
    }
}
