package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 工作交接确认信息传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-17 16:25]
 * @Description: [ 工作交接确认信息传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class HandoverSuccessTO extends BaseTO {

    /**
     * 意见或内容
     */
    @NotNull(message = "意见或内容不能为空", groups = {ADD.class, EDIT.class})
    private String opinion;

    /**
     * 权责
     */
    @NotNull(message = "权责不能为空", groups = {ADD.class, EDIT.class})
    private Boolean authority;

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Boolean getAuthority() {
        return authority;
    }

    public void setAuthority(Boolean authority) {
        this.authority = authority;
    }
}
