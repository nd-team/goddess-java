package com.bjike.goddess.voucher.service;

import com.bjike.goddess.voucher.dto.VoucherPermissionOperateDTO;
import com.bjike.goddess.voucher.entity.VoucherPermissionOperate;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 客户权限配置操作对象业务实现
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-05-25 02:12 ]
* @Description:	[ 客户权限配置操作对象业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="voucherSerCache")
@Service
public class VoucherPermissionOperateSerImpl extends ServiceImpl<VoucherPermissionOperate, VoucherPermissionOperateDTO> implements VoucherPermissionOperateSer {

 }