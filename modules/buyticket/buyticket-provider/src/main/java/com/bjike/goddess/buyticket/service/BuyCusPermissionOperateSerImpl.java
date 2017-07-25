package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.buyticket.dto.BuyCusPermissionOperateDTO;
import com.bjike.goddess.buyticket.entity.BuyCusPermissionOperate;
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
@CacheConfig(cacheNames = "buyCustomerSerCache")
@Service
public class BuyCusPermissionOperateSerImpl extends ServiceImpl<BuyCusPermissionOperate, BuyCusPermissionOperateDTO> implements BuyCusPermissionOperateSer {

}