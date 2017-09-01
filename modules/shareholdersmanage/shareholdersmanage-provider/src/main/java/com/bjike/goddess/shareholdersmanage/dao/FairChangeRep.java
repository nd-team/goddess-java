package com.bjike.goddess.shareholdersmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.shareholdersmanage.dto.FairChangeDTO;
import com.bjike.goddess.shareholdersmanage.entity.FairChange;

/**
* 公允值变动持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-29 05:36 ]
* @Description:	[ 公允值变动持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface FairChangeRep extends JpaRep<FairChange ,FairChangeDTO> { 

 }