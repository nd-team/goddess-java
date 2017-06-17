package com.bjike.goddess.moneyside.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 权责分配
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:13 ]
 * @Description: [ 权责分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccrualAllotTO extends BaseTO {

    /**
     * 投资人
     */
    @NotBlank(message = "投资人不能为空",groups = {ADD.class, EDIT.class})
    private String investor;

    /**
     * 权限
     */
    @NotBlank(message = "权限不能为空",groups = {ADD.class, EDIT.class})
    private String permissions;

    /**
     * 备注
     */
    private String remark;


    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}