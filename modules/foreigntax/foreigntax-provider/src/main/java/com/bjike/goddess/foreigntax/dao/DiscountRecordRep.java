package com.bjike.goddess.foreigntax.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.foreigntax.dto.DiscountRecordDTO;
import com.bjike.goddess.foreigntax.entity.DiscountRecord;

/**
 * 优惠备案持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:21 ]
 * @Description: [ 优惠备案持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DiscountRecordRep extends JpaRep<DiscountRecord, DiscountRecordDTO> {

}