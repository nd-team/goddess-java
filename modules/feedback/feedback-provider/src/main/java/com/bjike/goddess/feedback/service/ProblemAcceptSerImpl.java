package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.feedback.dto.ProblemAcceptDTO;
import com.bjike.goddess.feedback.entity.ProblemAccept;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 问题受理表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 06:12 ]
 * @Description: [ 问题受理表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class ProblemAcceptSerImpl extends ServiceImpl<ProblemAccept, ProblemAcceptDTO> implements ProblemAcceptSer {

}