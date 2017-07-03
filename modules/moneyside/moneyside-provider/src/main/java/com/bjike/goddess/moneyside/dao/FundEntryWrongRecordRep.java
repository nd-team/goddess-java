package com.bjike.goddess.moneyside.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.moneyside.dto.FundEntryWrongRecordDTO;
import com.bjike.goddess.moneyside.entity.FundEntryWrongRecord;

/**
 * 资金进入申请有误记录持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:07 ]
 * @Description: [ 资金进入申请有误记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FundEntryWrongRecordRep extends JpaRep<FundEntryWrongRecord, FundEntryWrongRecordDTO> {

}