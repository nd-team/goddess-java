package com.bjike.goddess.projectprocing.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 结算工作进度管理数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:09 ]
 * @Description: [ 结算工作进度管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SettleWorkProgreManageDTO extends BaseDTO {
    /**
     * 派工名称
     */
    private String dispatchingName;

    /**
     * 外包合同号
     */
    private String contractNo;
    /**
     * 责任人
     */
    private String responsible;

    public String getDispatchingName() {
        return dispatchingName;
    }

    public void setDispatchingName(String dispatchingName) {
        this.dispatchingName = dispatchingName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
}