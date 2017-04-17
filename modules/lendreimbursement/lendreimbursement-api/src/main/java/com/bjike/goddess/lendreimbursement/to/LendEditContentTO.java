package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 借款审核直接修改
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:09 ]
 * @Description: [ 借款审核直接修改 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LendEditContentTO extends BaseTO {

    /**
     * 审核人
     */
    private String auditor;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}