package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.dto.EmotionThreeDTO;
import com.bjike.goddess.recruit.entity.EmotionThree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Properties;

/**
 * 情感标签三级业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:52 ]
 * @Description: [ 情感标签三级业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "recruitSerCache")
@Service
public class EmotionThreeSerImpl extends ServiceImpl<EmotionThree, EmotionThreeDTO> implements EmotionThreeSer {



}