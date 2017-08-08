package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
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
public interface PurchaseAPI {

    /**
     * 申请购买
     */
    void buy(PurchaseTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void update(PurchaseTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     */
    void delete(String id) throws SerException;

    /**
     * 审核
     *
     * @param to
     */
    void examine(PurchaseTO to) throws SerException;
}