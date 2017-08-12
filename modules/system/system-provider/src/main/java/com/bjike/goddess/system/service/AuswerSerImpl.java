package com.bjike.goddess.system.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.system.dto.AuswerDTO;
import com.bjike.goddess.system.entity.Auswer;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 答案业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:49 ]
 * @Description: [ 答案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "systemSerCache")
@Service
public class AuswerSerImpl extends ServiceImpl<Auswer, AuswerDTO> implements AuswerSer {

}