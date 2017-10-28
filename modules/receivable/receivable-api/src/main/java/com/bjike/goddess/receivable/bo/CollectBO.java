package com.bjike.goddess.receivable.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 汇总业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 10:19 ]
 * @Description: [ 汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectBO extends BaseBO {

    /**
     * 科目
     */
    private String subject;

    /**
     * 合计
     */
    private Double total;
    /**
     * 时间
     */
    private String time;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}