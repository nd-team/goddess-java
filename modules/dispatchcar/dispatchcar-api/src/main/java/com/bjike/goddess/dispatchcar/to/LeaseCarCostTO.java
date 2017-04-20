package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 租车费用基本信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-13 10:55 ]
 * @Description: [ 租车费用基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LeaseCarCostTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目组
     */
    @NotBlank(message = "项目组不能为空", groups = {ADD.class, EDIT.class})
    private String group;

    /**
     * 租车费
     */
    @NotNull(message = "租车费不能为空", groups = {ADD.class, EDIT.class})
    private Double cost;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}