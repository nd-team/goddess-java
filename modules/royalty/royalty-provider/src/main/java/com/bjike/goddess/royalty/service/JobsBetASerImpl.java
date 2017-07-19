package com.bjike.goddess.royalty.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.royalty.dto.JobsBetADTO;
import com.bjike.goddess.royalty.entity.JobsBetA;
import com.bjike.goddess.royalty.entity.JobsBetB;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 岗位间对赌表A业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:21 ]
 * @Description: [ 岗位间对赌表A业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "royaltySerCache")
@Service
public class JobsBetASerImpl extends ServiceImpl<JobsBetA, JobsBetADTO> implements JobsBetASer {

}