package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.supplier.dto.ContactSituationDTO;
import com.bjike.goddess.supplier.entity.ContactSituation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 联络情况业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:03 ]
* @Description:	[ 联络情况业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="supplierSerCache")
@Service
public class ContactSituationSerImpl extends ServiceImpl<ContactSituation, ContactSituationDTO> implements ContactSituationSer { 

 }