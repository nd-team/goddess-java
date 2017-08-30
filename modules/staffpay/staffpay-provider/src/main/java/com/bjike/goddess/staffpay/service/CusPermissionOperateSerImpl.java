package com.bjike.goddess.staffpay.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffpay.dto.CusPermissionOperateDTO;
import com.bjike.goddess.staffpay.entity.CusPermissionOperate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 员工工资资金准备与支付权限配置操作对象业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-25 02:12 ]
 * @Description: [ 员工工资资金准备与支付权限配置操作对象业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffpaySerCache")
@Service
public class CusPermissionOperateSerImpl extends ServiceImpl<CusPermissionOperate, CusPermissionOperateDTO> implements CusPermissionOperateSer {

}