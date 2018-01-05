package com.bjike.goddess.incomecheck.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.incomecheck.dto.CheckIncomeDTO;
import com.bjike.goddess.incomecheck.entity.CheckIncome;

/**
 * 收入核算资金回笼持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:54 ]
 * @Description: [ 收入核算资金回笼持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CheckIncomeRep extends JpaRep<CheckIncome, CheckIncomeDTO> {

}