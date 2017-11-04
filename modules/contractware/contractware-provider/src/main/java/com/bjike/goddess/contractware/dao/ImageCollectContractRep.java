package com.bjike.goddess.contractware.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractware.dto.ImageCollectContractDTO;
import com.bjike.goddess.contractware.entity.ImageCollectContract;

/**
* 合同管理图形化持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-03 02:20 ]
* @Description:	[ 合同管理图形化持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ImageCollectContractRep extends JpaRep<ImageCollectContract ,ImageCollectContractDTO> { 

 }