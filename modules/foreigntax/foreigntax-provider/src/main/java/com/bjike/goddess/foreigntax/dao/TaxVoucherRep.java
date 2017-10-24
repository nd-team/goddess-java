package com.bjike.goddess.foreigntax.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.foreigntax.dto.TaxVoucherDTO;
import com.bjike.goddess.foreigntax.entity.TaxVoucher;

/**
 * 税金管理记账凭证记录持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 04:23 ]
 * @Description: [ 税金管理记账凭证记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TaxVoucherRep extends JpaRep<TaxVoucher, TaxVoucherDTO> {

}