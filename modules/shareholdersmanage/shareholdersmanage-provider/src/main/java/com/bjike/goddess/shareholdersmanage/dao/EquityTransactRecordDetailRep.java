package com.bjike.goddess.shareholdersmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransactRecordDetailDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecordDetail;

/**
* 股权交易记录详情持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-21 04:11 ]
* @Description:	[ 股权交易记录详情持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface EquityTransactRecordDetailRep extends JpaRep<EquityTransactRecordDetail,EquityTransactRecordDetailDTO> {

 }