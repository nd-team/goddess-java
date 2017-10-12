package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.financeinit.dto.AccountDepartmentDTO;
import com.bjike.goddess.financeinit.entity.AccountDepartment;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 核算部门业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-10-10 02:43 ]
* @Description:	[ 核算部门业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="financeinitSerCache")
@Service
public class AccountDepartmentSerImpl extends ServiceImpl<AccountDepartment, AccountDepartmentDTO> implements AccountDepartmentSer { 

 }