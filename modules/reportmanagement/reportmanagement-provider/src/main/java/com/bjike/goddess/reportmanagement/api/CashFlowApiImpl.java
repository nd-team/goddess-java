package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.service.CashFlowSer;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 现金流量表业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-21 03:54 ]
* @Description:	[ 现金流量表业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("cashFlowApiImpl")
public class CashFlowApiImpl implements CashFlowAPI  {
    
    @Autowired
    private CashFlowSer cashFlowSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return cashFlowSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return cashFlowSer.guidePermission(guidePermissionTO);
    }
}