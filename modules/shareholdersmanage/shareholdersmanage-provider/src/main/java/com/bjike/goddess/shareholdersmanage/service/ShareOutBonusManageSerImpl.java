package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.shareholdersmanage.dto.ShareOutBonusManageDTO;
import com.bjike.goddess.shareholdersmanage.entity.ShareOutBonusManage;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 分红管理业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-21 05:47 ]
* @Description:	[ 分红管理业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="shareholdersmanageSerCache")
@Service
public class ShareOutBonusManageSerImpl extends ServiceImpl<ShareOutBonusManage, ShareOutBonusManageDTO> implements ShareOutBonusManageSer { 

 }