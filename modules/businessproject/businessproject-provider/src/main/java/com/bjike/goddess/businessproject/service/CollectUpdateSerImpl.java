package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.dto.CollectUpdateDTO;
import com.bjike.goddess.businessproject.entity.CollectUpdate;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 客户权限配置操作对象业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-25 02:12 ]
 * @Description: [ 客户权限配置操作对象业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class CollectUpdateSerImpl extends ServiceImpl<CollectUpdate, CollectUpdateDTO> implements CollectUpdateSer {

}