package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffshares.dto.BuyscheduleDTO;
import com.bjike.goddess.staffshares.entity.Buyschedule;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 买入记录表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:09 ]
 * @Description: [ 买入记录表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class BuyscheduleSerImpl extends ServiceImpl<Buyschedule, BuyscheduleDTO> implements BuyscheduleSer {

}