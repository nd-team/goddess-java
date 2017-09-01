package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 变更股权类型
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:42 ]
 * @Description: [ 变更股权类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ChangeEquityTypeTO extends BaseTO {

    /**
     * 变更前股权类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "变更前股权类型不能为空")
    private String changeBeforeType;

    /**
     * 变更后股权类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "变更后股权类型不能为空")
    private String changeAfterType;

    /**
     * 变更时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "变更时间不能为空")
    private String changeDate;

    /**
     * 备注
     */
    private String remark;


    public String getChangeBeforeType() {
        return changeBeforeType;
    }

    public void setChangeBeforeType(String changeBeforeType) {
        this.changeBeforeType = changeBeforeType;
    }

    public String getChangeAfterType() {
        return changeAfterType;
    }

    public void setChangeAfterType(String changeAfterType) {
        this.changeAfterType = changeAfterType;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}