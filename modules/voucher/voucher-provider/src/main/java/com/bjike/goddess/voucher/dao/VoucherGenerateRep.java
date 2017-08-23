package com.bjike.goddess.voucher.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import com.bjike.goddess.voucher.entity.VoucherGenerate;

/**
 * 记账凭证生成持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:33 ]
 * @Description: [ 记账凭证生成持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface VoucherGenerateRep extends JpaRep<VoucherGenerate, VoucherGenerateDTO> {

}