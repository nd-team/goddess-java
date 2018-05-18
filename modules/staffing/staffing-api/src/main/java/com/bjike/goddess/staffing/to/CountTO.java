package com.bjike.goddess.staffing.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 操作汇总
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-09 02:00 ]
 * @Description: [ 操作汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CountTO extends BaseTO {
    public CountTO(String navigation) {
        this.navigation = navigation;
    }

    /**
     * 导航名
     */
    @NotBlank(groups = ADD.class,message = "导航名不能为空")
    private String navigation;

    /**
     * 功能名
     */
    @NotBlank(groups = ADD.class,message = "功能名不能为空")
    private String function;


    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

}