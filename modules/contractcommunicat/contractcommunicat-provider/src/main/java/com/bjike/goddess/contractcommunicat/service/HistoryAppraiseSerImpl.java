package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.contractcommunicat.dto.HistoryAppraiseDTO;
import com.bjike.goddess.contractcommunicat.entity.HistoryAppraise;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 历史评价业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:56 ]
 * @Description: [ 历史评价业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractcommunicatSerCache")
@Service
public class HistoryAppraiseSerImpl extends ServiceImpl<HistoryAppraise, HistoryAppraiseDTO> implements HistoryAppraiseSer {

}