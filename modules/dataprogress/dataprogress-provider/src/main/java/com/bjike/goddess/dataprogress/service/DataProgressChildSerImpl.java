package com.bjike.goddess.dataprogress.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.dataprogress.dto.DataProgressChildDTO;
import com.bjike.goddess.dataprogress.entity.DataProgressChild;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 资料收集进度管理子表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-28 10:04 ]
 * @Description: [ 资料收集进度管理子表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "dataprogressSerCache")
@Service
public class DataProgressChildSerImpl extends ServiceImpl<DataProgressChild, DataProgressChildDTO> implements DataProgressChildSer {

}