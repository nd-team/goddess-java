package com.bjike.goddess.assemble.service;

import com.bjike.goddess.assemble.dto.ModuleApplyDTO;
import com.bjike.goddess.assemble.entity.ModuleApply;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 模块应用业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-17 05:41 ]
 * @Description: [ 模块应用业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assembleSerCache")
@Service
public class ModuleApplySerImpl extends ServiceImpl<ModuleApply, ModuleApplyDTO> implements ModuleApplySer {

}