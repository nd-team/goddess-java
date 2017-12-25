package com.bjike.goddess.business.service;

import com.bjike.goddess.business.dto.CusPermissionOperateDTO;
import com.bjike.goddess.business.entity.CusPermissionOperate;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 工商权限配置操作对象业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-25 02:12 ]
 * @Description: [ 工商权限配置操作对象业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessSerCache")
@Service
public class CusPermissionOperateSerImpl extends ServiceImpl<CusPermissionOperate, CusPermissionOperateDTO> implements CusPermissionOperateSer {

}