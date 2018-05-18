package com.bjike.goddess.businessproject.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businessproject.dto.OutsourcBusinessContractDTO;
import com.bjike.goddess.businessproject.entity.OutsourcBusinessContract;

/**
* 外包半外包项目合同管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-10-19 11:55 ]
* @Description:	[ 外包半外包项目合同管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface OutsourcBusinessContractRep extends JpaRep<OutsourcBusinessContract ,OutsourcBusinessContractDTO> { 

 }