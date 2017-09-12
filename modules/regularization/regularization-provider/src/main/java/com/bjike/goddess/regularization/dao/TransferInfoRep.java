package com.bjike.goddess.regularization.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.regularization.dto.TransferInfoDTO;
import com.bjike.goddess.regularization.entity.TransferInfo;

/**
* 转正人员信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-09-12 02:20 ]
* @Description:	[ 转正人员信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TransferInfoRep extends JpaRep<TransferInfo ,TransferInfoDTO> { 

 }