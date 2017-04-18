package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 离职面谈信息传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-17 15:42]
 * @Description: [ 离职面谈信息传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DimissionInterviewTo extends BaseTO {

    /**
     * 内容
     */
    private String content;

    /**
     * 意见
     */
    private String opinion;

    /**
     * 权责
     */
    private Boolean authority;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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
