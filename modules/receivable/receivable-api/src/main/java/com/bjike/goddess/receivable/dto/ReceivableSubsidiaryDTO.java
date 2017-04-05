package com.bjike.goddess.receivable.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 回款明细数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReceivableSubsidiaryDTO extends BaseDTO {

    public static final String[] AREACOLLECTDETAIL = {"地区","项目名称", "派工单价","已派工量","完工时间",
            "验收交维时间","签字审批时间","ERP结算审批时间","发票审核时间","预计支付时间","到帐时间","管理费","到帐金额",
            "税金","税后金额","是否已走结算流程","详细情况"};


    public static final String[] AREACOLLECT = {"地区","管理费","到账金额","税金","税后金额"};

}