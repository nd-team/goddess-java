package com.bjike.goddess.firmreward.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.firmreward.dto.BonusBudgetDTO;
import com.bjike.goddess.firmreward.entity.BonusBudget;

/**
 * 奖金预算持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:10 ]
 * @Description: [ 奖金预算持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BonusBudgetRep extends JpaRep<BonusBudget, BonusBudgetDTO> {

}