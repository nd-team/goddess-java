package com.bjike.goddess.housepay.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.housepay.dto.PayRecordDTO;
import com.bjike.goddess.housepay.entity.PayRecord;

/**
 * 已付款记录持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:13 ]
 * @Description: [ 已付款记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PayRecordRep extends JpaRep<PayRecord, PayRecordDTO> {

}