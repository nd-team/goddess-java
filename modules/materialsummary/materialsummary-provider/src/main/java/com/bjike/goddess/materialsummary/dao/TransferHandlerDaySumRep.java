package com.bjike.goddess.materialsummary.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialsummary.dto.TransferHandlerDaySumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerDaySum;

/**
* 调动经手人日汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:53 ]
* @Description:	[ 调动经手人日汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TransferHandlerDaySumRep extends JpaRep<TransferHandlerDaySum ,TransferHandlerDaySumDTO> { 

 }