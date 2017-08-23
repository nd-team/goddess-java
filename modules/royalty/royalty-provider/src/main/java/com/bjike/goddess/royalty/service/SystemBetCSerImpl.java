package com.bjike.goddess.royalty.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.royalty.dto.SystemBetCDTO;
import com.bjike.goddess.royalty.entity.SystemBetC;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 体系间对赌表C业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:56 ]
 * @Description: [ 体系间对赌表C业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "royaltySerCache")
@Service
public class SystemBetCSerImpl extends ServiceImpl<SystemBetC, SystemBetCDTO> implements SystemBetCSer {

}