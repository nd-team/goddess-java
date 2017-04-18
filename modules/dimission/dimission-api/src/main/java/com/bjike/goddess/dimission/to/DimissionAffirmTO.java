package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 离职薪资确认传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-17 15:53]
 * @Description: [ 离职薪资确认传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DimissionAffirmTO extends BaseTO {

    /**
     * 是否确认无误
     */
    private Boolean affirm;

    public Boolean getAffirm() {
        return affirm;
    }

    public void setAffirm(Boolean affirm) {
        this.affirm = affirm;
    }
}
