package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 补充资料公式业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-22 03:08 ]
 * @Description: [ 补充资料公式业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashFormulaDataBO extends BaseBO {

    /**
     * 补充资料id
     */
    private String dataId;

    /**
     * 补充资料
     */
    private String data;

    /**
     * 公式
     */
    private String form;


    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}