package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.regularization.dto.TransferInfoDTO;
import com.bjike.goddess.regularization.entity.TransferInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 转正人员信息业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-09-12 02:20 ]
* @Description:	[ 转正人员信息业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="regularizationSerCache")
@Service
public class TransferInfoSerImpl extends ServiceImpl<TransferInfo, TransferInfoDTO> implements TransferInfoSer { 

 }