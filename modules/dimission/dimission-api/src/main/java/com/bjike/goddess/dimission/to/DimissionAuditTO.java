package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 离职审核传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-17 15:46]
 * @Description: [ 离职审核传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DimissionAuditTO extends BaseTO {

    /**
     * 审核意见
     */
    @NotNull(message = "审核意见不能为空", groups = {ADD.class, EDIT.class})
    private String opinion;

    /**
     * 是否通过
     */
    @NotNull(message = "是否通过不能为空", groups = {ADD.class, EDIT.class})
    private Boolean pass;

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }
}
