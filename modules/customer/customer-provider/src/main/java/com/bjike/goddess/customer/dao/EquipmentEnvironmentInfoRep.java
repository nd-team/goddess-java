package com.bjike.goddess.customer.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.customer.dto.EquipmentEnvironmentInfoDTO;
import com.bjike.goddess.customer.entity.EquipmentEnvironmentInfo;

/**
* 设备搭建环境信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-12-28 09:34 ]
* @Description:	[ 设备搭建环境信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface EquipmentEnvironmentInfoRep extends JpaRep<EquipmentEnvironmentInfo ,EquipmentEnvironmentInfoDTO> { 

 }