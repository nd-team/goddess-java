package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.dto.EmotionTwoDTO;
import com.bjike.goddess.recruit.entity.EmotionTwo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 情感标签二级业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:56 ]
 * @Description: [ 情感标签二级业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "recruitSerCache")
@Service
public class EmotionTwoSerImpl extends ServiceImpl<EmotionTwo, EmotionTwoDTO> implements EmotionTwoSer {


}