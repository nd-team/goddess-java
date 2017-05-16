package com.bjike.goddess.housepay.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.housepay.dto.MoneyReadyDTO;
import com.bjike.goddess.housepay.entity.MoneyReady;

/**
 * 资金准备审核表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:32 ]
 * @Description: [ 资金准备审核表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MoneyReadyRep extends JpaRep<MoneyReady, MoneyReadyDTO> {

}