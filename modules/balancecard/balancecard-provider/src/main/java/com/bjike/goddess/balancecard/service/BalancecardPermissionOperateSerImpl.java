package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.dto.BalancecardPermissionOperateDTO;
import com.bjike.goddess.balancecard.entity.BalancecardPermissionOperate;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 平衡计分卡权限配置操作对象业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-25 02:12 ]
 * @Description: [ 平衡计分卡权限配置操作对象业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "balancecardSerCache")
@Service
public class BalancecardPermissionOperateSerImpl extends ServiceImpl<BalancecardPermissionOperate, BalancecardPermissionOperateDTO> implements BalancecardPermissionOperateSer {

}