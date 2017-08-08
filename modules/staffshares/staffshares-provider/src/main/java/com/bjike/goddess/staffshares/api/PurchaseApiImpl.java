package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffshares.service.PurchaseSer;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 干股申购表业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-08-05 09:51 ]
* @Description:	[ 干股申购表业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("purchaseApiImpl")
public class PurchaseApiImpl implements PurchaseAPI  {
    @Autowired
    private PurchaseSer purchaseSer;

    @Override
    public void buy(PurchaseTO to) throws SerException {
        purchaseSer.buy(to);
    }
}