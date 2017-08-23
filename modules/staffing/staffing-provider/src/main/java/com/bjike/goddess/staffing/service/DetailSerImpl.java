package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffing.dto.DetailDTO;
import com.bjike.goddess.staffing.entity.Detail;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 人工成本计划详细业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 02:59 ]
 * @Description: [ 人工成本计划详细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffingSerCache")
@Service
public class DetailSerImpl extends ServiceImpl<Detail, DetailDTO> implements DetailSer {

}