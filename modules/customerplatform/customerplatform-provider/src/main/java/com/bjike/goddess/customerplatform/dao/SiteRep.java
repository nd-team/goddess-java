package com.bjike.goddess.customerplatform.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.customerplatform.dto.SiteDTO;
import com.bjike.goddess.customerplatform.entity.Site;

/**
* 站点持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-30 11:29 ]
* @Description:	[ 站点持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SiteRep extends JpaRep<Site ,SiteDTO> { 

 }