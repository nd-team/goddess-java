package com.bjike.goddess.qualifications.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.qualifications.enums.AptitudeStatus;

import javax.validation.constraints.NotNull;

/**
 * 资质信息管理资质状态传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-01 15:19]
 * @Description: [ 资质信息管理资质状态传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class QualificationsInfoStatusTO extends BaseTO {

    /**
     * 资质状态
     */
    @NotNull(message = "资质状态不能为空", groups = {ADD.class, EDIT.class})
    private AptitudeStatus status;

    public AptitudeStatus getStatus() {
        return status;
    }

    public void setStatus(AptitudeStatus status) {
        this.status = status;
    }
}
