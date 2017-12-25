package com.bjike.goddess.moneyside.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyWrongRecordDTO;
import com.bjike.goddess.moneyside.entity.MoneyExitApplyWrongRecord;

/**
 * 资金退出申请有误记录持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-09 05:58 ]
 * @Description: [ 资金退出申请有误记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MoneyExitApplyWrongRecordRep extends JpaRep<MoneyExitApplyWrongRecord, MoneyExitApplyWrongRecordDTO> {

}