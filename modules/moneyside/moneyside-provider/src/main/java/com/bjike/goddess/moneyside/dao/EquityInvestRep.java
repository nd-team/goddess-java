package com.bjike.goddess.moneyside.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.moneyside.dto.EquityInvestDTO;
import com.bjike.goddess.moneyside.entity.EquityInvest;

/**
 * 投资条件-股权投资持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:38 ]
 * @Description: [ 投资条件-股权投资持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EquityInvestRep extends JpaRep<EquityInvest, EquityInvestDTO> {

}