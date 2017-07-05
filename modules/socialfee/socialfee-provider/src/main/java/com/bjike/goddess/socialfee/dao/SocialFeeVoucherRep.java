package com.bjike.goddess.socialfee.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.socialfee.dto.SocialFeeVoucherDTO;
import com.bjike.goddess.socialfee.entity.SocialFeeVoucher;

/**
 * 社会缴费记账凭证记录持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-20 10:40 ]
 * @Description: [ 社会缴费记账凭证记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SocialFeeVoucherRep extends JpaRep<SocialFeeVoucher, SocialFeeVoucherDTO> {

}