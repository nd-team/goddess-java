package com.bjike.goddess.qualifications.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.qualifications.dto.FinanceInfoDTO;
import com.bjike.goddess.qualifications.entity.FinanceInfo;

/**
* 财务资料持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 06:42 ]
* @Description:	[ 财务资料持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface FinanceInfoRep extends JpaRep<FinanceInfo ,FinanceInfoDTO> { 

 }