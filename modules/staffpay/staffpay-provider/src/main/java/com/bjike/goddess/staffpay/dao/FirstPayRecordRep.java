package com.bjike.goddess.staffpay.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffpay.dto.FirstPayRecordDTO;
import com.bjike.goddess.staffpay.entity.FirstPayRecord;

/**
 * 第一次已付款记录持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:01 ]
 * @Description: [ 第一次已付款记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FirstPayRecordRep extends JpaRep<FirstPayRecord, FirstPayRecordDTO> {

}