package com.bjike.goddess.system.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.system.dto.QuestionDTO;
import com.bjike.goddess.system.entity.Question;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 问题业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:48 ]
 * @Description: [ da业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "systemSerCache")
@Service
public class QuestionSerImpl extends ServiceImpl<Question, QuestionDTO> implements QuestionSer {

}