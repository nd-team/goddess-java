package com.bjike.goddess.marketdevelopment.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 业务对象业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-28 02:54 ]
 * @Description: [ 业务对象业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessCompanySubVO extends BaseBO {
    /**
     * 可发展业务方向-科目
     */
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}