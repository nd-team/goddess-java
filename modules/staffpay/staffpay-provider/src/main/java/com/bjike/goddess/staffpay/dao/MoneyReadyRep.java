package com.bjike.goddess.staffpay.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffpay.dto.MoneyReadyDTO;
import com.bjike.goddess.staffpay.entity.MoneyReady;

/**
 * 资金准备审核表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:03 ]
 * @Description: [ 资金准备审核表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MoneyReadyRep extends JpaRep<MoneyReady, MoneyReadyDTO> {

}