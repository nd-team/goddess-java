package com.bjike.goddess.checkfunds.beanlist;

import java.io.Serializable;

/**
 * 银行流水明细表现层对象
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-13 18:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Detail implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String val;

    /**
     * 银行流水Id
     */
    private String bankRecordId;

    /**
     * 标题下标
     */
    private Integer titleIndex;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getBankRecordId() {
        return bankRecordId;
    }

    public void setBankRecordId(String bankRecordId) {
        this.bankRecordId = bankRecordId;
    }

    public Integer getTitleIndex() {
        return titleIndex;
    }

    public void setTitleIndex(Integer titleIndex) {
        this.titleIndex = titleIndex;
    }
}
