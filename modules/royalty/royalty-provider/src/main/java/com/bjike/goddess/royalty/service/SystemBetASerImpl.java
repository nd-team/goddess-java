package com.bjike.goddess.royalty.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.royalty.bo.SystemBetABO;
import com.bjike.goddess.royalty.dto.SystemBetADTO;
import com.bjike.goddess.royalty.entity.SystemBetA;
import com.bjike.goddess.royalty.to.SystemBetATO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 体系间对赌表A业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:22 ]
 * @Description: [ 体系间对赌表A业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "royaltySerCache")
@Service
public class SystemBetASerImpl extends ServiceImpl<SystemBetA, SystemBetADTO> implements SystemBetASer {
}