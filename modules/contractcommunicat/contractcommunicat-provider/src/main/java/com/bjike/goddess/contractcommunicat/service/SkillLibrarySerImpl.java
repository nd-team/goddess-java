package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.contractcommunicat.dto.SkillLibraryDTO;
import com.bjike.goddess.contractcommunicat.entity.SkillLibrary;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 谈判技巧库业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:55 ]
 * @Description: [ 谈判技巧库业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractcommunicatSerCache")
@Service
public class SkillLibrarySerImpl extends ServiceImpl<SkillLibrary, SkillLibraryDTO> implements SkillLibrarySer {

}