package com.bjike.goddess.moneyside.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyDTO;
import com.bjike.goddess.moneyside.entity.MoneyExitApply;

/**
 * 资金退出申请表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:37 ]
 * @Description: [ 资金退出申请表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MoneyExitApplyRep extends JpaRep<MoneyExitApply, MoneyExitApplyDTO> {

}