package com.bjike.goddess.archive.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 档案调阅审核传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-12 18:01]
 * @Description: [ 档案调阅审核传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AccessAuditTO extends BaseTO {

    /**
     * 是否通过
     */
    private Boolean pass;

    /**
     * 意见
     */
    private String opinion;

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
