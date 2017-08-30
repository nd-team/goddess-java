package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 工作交接
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:24 ]
 * @Description: [ 工作交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkHandoverTO extends BaseTO {

    /**
     * 交接人姓名
     */
    @NotNull(message = "交接人姓名不能为空", groups = {ADD.class, EDIT.class})
    private String handover;

    /**
     * 被交接人姓名
     */
    @NotNull(message = "被交接人姓名不能为空", groups = {ADD.class, EDIT.class})
    private String transferred;

    /**
     * 交接信息明细
     */
    @NotNull(message = "交接信息明细不能为空", groups = {ADD.class, EDIT.class})
    private String handoverInfo;

    /**
     * 交接情况
     */
    @NotNull(message = "交接情况不能为空", groups = {ADD.class, EDIT.class})
    private String situation;



    public String getHandover() {
        return handover;
    }

    public void setHandover(String handover) {
        this.handover = handover;
    }

    public String getTransferred() {
        return transferred;
    }

    public void setTransferred(String transferred) {
        this.transferred = transferred;
    }

    public String getHandoverInfo() {
        return handoverInfo;
    }

    public void setHandoverInfo(String handoverInfo) {
        this.handoverInfo = handoverInfo;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

}