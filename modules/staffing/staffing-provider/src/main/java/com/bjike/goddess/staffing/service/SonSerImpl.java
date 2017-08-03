package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffing.dto.SonDTO;
import com.bjike.goddess.staffing.entity.Son;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 人工成本计划子表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 11:57 ]
 * @Description: [ 人工成本计划子表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffingSerCache")
@Service
public class SonSerImpl extends ServiceImpl<Son, SonDTO> implements SonSer {
    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }
}