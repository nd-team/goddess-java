package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.foreigntax.dto.TaxVoucherDTO;
import com.bjike.goddess.foreigntax.entity.TaxVoucher;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 税金管理记账凭证记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 04:23 ]
 * @Description: [ 税金管理记账凭证记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "foreigntaxSerCache")
@Service
public class TaxVoucherSerImpl extends ServiceImpl<TaxVoucher, TaxVoucherDTO> implements TaxVoucherSer {

}