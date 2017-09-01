package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.shareholdersmanage.dto.ShareOutBonusDetailDTO;
import com.bjike.goddess.shareholdersmanage.entity.ShareOutBonusDetail;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 分红明细业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-21 05:55 ]
* @Description:	[ 分红明细业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="shareholdersmanageSerCache")
@Service
public class ShareOutBonusDetailSerImpl extends ServiceImpl<ShareOutBonusDetail, ShareOutBonusDetailDTO> implements ShareOutBonusDetailSer { 

 }