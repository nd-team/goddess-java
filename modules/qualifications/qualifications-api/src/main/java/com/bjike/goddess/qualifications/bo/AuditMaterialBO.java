package com.bjike.goddess.qualifications.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 审核资料业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:44 ]
 * @Description: [ 审核资料业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AuditMaterialBO extends BaseBO {

    /**
     * 备案书
     */
    private String record;

    /**
     * 其他(附件)
     */
    private String other;


    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}