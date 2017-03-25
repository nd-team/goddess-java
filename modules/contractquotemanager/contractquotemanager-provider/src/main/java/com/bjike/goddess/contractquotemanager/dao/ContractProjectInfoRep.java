package com.bjike.goddess.contractquotemanager.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractquotemanager.dto.ContractProjectInfoDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractProjectInfo;

/**
* 合同项目基本信息(临时表存放数据商务模块获取数据)持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-21 07:18 ]
* @Description:	[ 持久化接口,继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ContractProjectInfoRep extends JpaRep<ContractProjectInfo ,ContractProjectInfoDTO> { 

 }