package com.bjike.goddess.eggert.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.eggert.dto.RecordDTO;
import com.bjike.goddess.eggert.entity.Record;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 信息记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:22 ]
 * @Description: [ 信息记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "eggertSerCache")
@Service
public class RecordSerImpl extends ServiceImpl<Record, RecordDTO> implements RecordSer {

}