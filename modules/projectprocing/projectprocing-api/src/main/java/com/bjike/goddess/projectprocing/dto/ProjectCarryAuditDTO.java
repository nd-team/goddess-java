package com.bjike.goddess.projectprocing.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 项目实施审核(针对没派工单情况)数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 06:41 ]
 * @Description: [ 项目实施审核(针对没派工单情况)数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectCarryAuditDTO extends BaseDTO {

    /**
     * 销售合同号
     */
    private String saleNum;

    /**
     * 立项情况
     */
    private String signProjectCondition;

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getSignProjectCondition() {
        return signProjectCondition;
    }

    public void setSignProjectCondition(String signProjectCondition) {
        this.signProjectCondition = signProjectCondition;
    }
}