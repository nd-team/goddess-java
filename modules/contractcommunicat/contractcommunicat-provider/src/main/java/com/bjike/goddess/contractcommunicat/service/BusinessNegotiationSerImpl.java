package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationDTO;
import com.bjike.goddess.contractcommunicat.entity.BusinessNegotiation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 商务洽谈业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractcommunicatSerCache")
@Service
public class BusinessNegotiationSerImpl extends ServiceImpl<BusinessNegotiation, BusinessNegotiationDTO> implements BusinessNegotiationSer {

}