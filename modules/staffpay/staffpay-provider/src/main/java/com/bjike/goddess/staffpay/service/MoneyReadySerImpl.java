package com.bjike.goddess.staffpay.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffpay.dto.MoneyReadyDTO;
import com.bjike.goddess.staffpay.entity.MoneyReady;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 资金准备审核表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:03 ]
 * @Description: [ 资金准备审核表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffpaySerCache")
@Service
public class MoneyReadySerImpl extends ServiceImpl<MoneyReady, MoneyReadyDTO> implements MoneyReadySer {

}