package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffshares.dto.PurchaseDTO;
import com.bjike.goddess.staffshares.entity.Purchase;
import com.bjike.goddess.staffshares.to.PurchaseTO;

/**
 * 干股申购表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 09:51 ]
 * @Description: [ 干股申购表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PurchaseSer extends Ser<Purchase, PurchaseDTO> {

    /**
     * 申请购买
     *
     * @param to
     */
    void buy(PurchaseTO to) throws SerException;
}