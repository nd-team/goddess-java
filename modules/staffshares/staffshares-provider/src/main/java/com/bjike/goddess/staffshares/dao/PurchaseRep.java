package com.bjike.goddess.staffshares.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffshares.dto.PurchaseDTO;
import com.bjike.goddess.staffshares.entity.Purchase;

/**
 * 干股申购表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 09:51 ]
 * @Description: [ 干股申购表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PurchaseRep extends JpaRep<Purchase, PurchaseDTO> {

}