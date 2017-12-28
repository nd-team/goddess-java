package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.dto.LendPermissionOperateDTO;
import com.bjike.goddess.lendreimbursement.entity.LendPermissionOperate;
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
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class LendPermissionOperateSerImpl extends ServiceImpl<LendPermissionOperate, LendPermissionOperateDTO> implements LendPermissionOperateSer {

}