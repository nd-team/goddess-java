package com.bjike.goddess.assemble.service;

import com.bjike.goddess.assemble.dto.ModuleAssembleDTO;
import com.bjike.goddess.assemble.entity.ModuleAssemble;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 模块关联业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-12 04:42 ]
 * @Description: [ 模块关联业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assembleSerCache")
@Service
public class ModuleAssembleSerImpl extends ServiceImpl<ModuleAssemble, ModuleAssembleDTO> implements ModuleAssembleSer {

}