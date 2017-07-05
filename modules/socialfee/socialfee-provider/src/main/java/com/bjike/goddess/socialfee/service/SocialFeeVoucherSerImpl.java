package com.bjike.goddess.socialfee.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.socialfee.dto.SocialFeeVoucherDTO;
import com.bjike.goddess.socialfee.entity.SocialFeeVoucher;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 社会缴费记账凭证记录业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-20 10:40 ]
 * @Description: [ 社会缴费记账凭证记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "socialfeeSerCache")
@Service
public class SocialFeeVoucherSerImpl extends ServiceImpl<SocialFeeVoucher, SocialFeeVoucherDTO> implements SocialFeeVoucherSer {

}