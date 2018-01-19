package com.bjike.goddess.abilitydisplay.service;

import com.bjike.goddess.abilitydisplay.dto.ComCulturalDTO;
import com.bjike.goddess.abilitydisplay.entity.ComCultural;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 公司文化活动业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:13 ]
 * @Description: [ 公司文化活动业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "abilitydisplaySerCache")
@Service
public class ComCulturalSerImpl extends ServiceImpl<ComCultural, ComCulturalDTO> implements ComCulturalSer {


}