package com.bjike.goddess.staffmove.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffmove.dto.CusPermissionOperateDTO;
import com.bjike.goddess.staffmove.entity.CusPermissionOperate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 权限配置操作对象业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-25 02:12 ]
 * @Description: [ 权限配置操作对象业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmoveSerCache")
@Service
public class CusPermissionOperateSerImpl extends ServiceImpl<CusPermissionOperate, CusPermissionOperateDTO> implements CusPermissionOperateSer {

}