package com.bjike.goddess.voucher.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.voucher.dto.VoucherTotalDTO;
import com.bjike.goddess.voucher.entity.VoucherTotal;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 记账凭证合计金额业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:35 ]
 * @Description: [ 记账凭证合计金额业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "voucherSerCache")
@Service
public class VoucherTotalSerImpl extends ServiceImpl<VoucherTotal, VoucherTotalDTO> implements VoucherTotalSer {

}