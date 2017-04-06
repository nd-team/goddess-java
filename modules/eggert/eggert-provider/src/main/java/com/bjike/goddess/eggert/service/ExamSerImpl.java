package com.bjike.goddess.eggert.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.eggert.dto.ExamDTO;
import com.bjike.goddess.eggert.entity.Exam;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 答题信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:20 ]
 * @Description: [ 答题信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "eggertSerCache")
@Service
public class ExamSerImpl extends ServiceImpl<Exam, ExamDTO> implements ExamSer {

}