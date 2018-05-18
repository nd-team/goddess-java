package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.dto.CashFlowDTO;
import com.bjike.goddess.reportmanagement.entity.CashFlow;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;

import java.util.List;

/**
* 现金流量表业务接口
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-21 03:54 ]
* @Description:	[ 现金流量表业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CashFlowSer extends Ser<CashFlow, CashFlowDTO> {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    default CashFlow save(List<CashFlow> cashFlows) throws SerException{
        return null;
    }
}