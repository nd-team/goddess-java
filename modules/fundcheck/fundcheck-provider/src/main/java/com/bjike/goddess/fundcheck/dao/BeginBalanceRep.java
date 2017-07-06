package com.bjike.goddess.fundcheck.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.fundcheck.dto.BeginBalanceDTO;
import com.bjike.goddess.fundcheck.entity.BeginBalance;

/**
 * 期初余额持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-05 03:34 ]
 * @Description: [ 期初余额持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BeginBalanceRep extends JpaRep<BeginBalance, BeginBalanceDTO> {

}