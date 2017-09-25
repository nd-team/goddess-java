package com.bjike.goddess.staffentry.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 员工入职注册
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 员工入职注册 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RegisterEditTO extends BaseTO {

    /**
     * 是否入职
     */
    private Boolean entry;
    /**
     * 未入职原因
     */
    private String noEntryCause;

    public Boolean getEntry() {
        return entry;
    }

    public void setEntry(Boolean entry) {
        this.entry = entry;
    }

    public String getNoEntryCause() {
        return noEntryCause;
    }

    public void setNoEntryCause(String noEntryCause) {
        this.noEntryCause = noEntryCause;
    }
}