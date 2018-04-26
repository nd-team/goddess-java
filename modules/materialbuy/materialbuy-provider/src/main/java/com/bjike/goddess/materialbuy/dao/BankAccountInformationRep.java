package com.bjike.goddess.materialbuy.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialbuy.dto.BankAccountInformationDTO;
import com.bjike.goddess.materialbuy.entity.BankAccountInformation;

/**
* 用户积分持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-02 09:36 ]
* @Description:	[ 用户积分持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BankAccountInformationRep extends JpaRep<BankAccountInformation ,BankAccountInformationDTO> { 

 }