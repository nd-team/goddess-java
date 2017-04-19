package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialbuy.dto.TempMatterDemandDTO;
import com.bjike.goddess.materialbuy.entity.TempMatterDemand;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 临时物质需求业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:08 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialbuySerCache")
@Service
public class TempMatterDemandSerImpl extends ServiceImpl<TempMatterDemand, TempMatterDemandDTO> implements TempMatterDemandSer {

}