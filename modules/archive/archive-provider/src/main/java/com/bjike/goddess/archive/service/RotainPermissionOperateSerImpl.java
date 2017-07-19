package com.bjike.goddess.archive.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.archive.dto.RotainPermissionOperateDTO;
import com.bjike.goddess.archive.entity.RotainPermissionOperate;
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
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class RotainPermissionOperateSerImpl extends ServiceImpl<RotainPermissionOperate, RotainPermissionOperateDTO> implements RotainPermissionOperateSer {

}