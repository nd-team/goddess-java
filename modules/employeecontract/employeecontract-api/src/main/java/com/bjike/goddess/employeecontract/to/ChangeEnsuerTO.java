package com.bjike.goddess.employeecontract.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-10 11:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ChangeEnsuerTO extends BaseTO{
    /**
     * 是否确认变更
     */
    private Boolean ifEnsureChange;

    /**
     * 确认变更人
     */
    private String ensureChanger;

    public Boolean getIfEnsureChange() {
        return ifEnsureChange;
    }

    public void setIfEnsureChange(Boolean ifEnsureChange) {
        this.ifEnsureChange = ifEnsureChange;
    }

    public String getEnsureChanger() {
        return ensureChanger;
    }

    public void setEnsureChanger(String ensureChanger) {
        this.ensureChanger = ensureChanger;
    }
}
