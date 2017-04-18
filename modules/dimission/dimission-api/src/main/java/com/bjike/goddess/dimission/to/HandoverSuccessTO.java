package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.to.BaseTO;

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
    private String opinion;

    /**
     * 权责
     */
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
