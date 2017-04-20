package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "是否确认无误不能为空", groups = {ADD.class, EDIT.class})
    private Boolean affirm;

    public Boolean getAffirm() {
        return affirm;
    }

    public void setAffirm(Boolean affirm) {
        this.affirm = affirm;
    }
}
