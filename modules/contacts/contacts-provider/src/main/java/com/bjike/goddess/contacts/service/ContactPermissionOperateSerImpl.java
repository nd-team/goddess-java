package com.bjike.goddess.contacts.service;

import com.bjike.goddess.contacts.dto.ContactPermissionOperateDTO;
import com.bjike.goddess.contacts.entity.ContactPermissionOperate;
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
@CacheConfig(cacheNames ="contactsSerCache")
@Service
public class ContactPermissionOperateSerImpl extends ServiceImpl<ContactPermissionOperate, ContactPermissionOperateDTO> implements ContactPermissionOperateSer {

 }