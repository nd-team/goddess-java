package com.bjike.goddess.voucher.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.voucher.dto.VoucherTotalDTO;
import com.bjike.goddess.voucher.entity.VoucherTotal;

/**
 * 记账凭证合计金额持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:35 ]
 * @Description: [ 记账凭证合计金额持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface VoucherTotalRep extends JpaRep<VoucherTotal, VoucherTotalDTO> {

}