package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.to.BaseTO;

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

    private String opinion;

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
