package com.bjike.goddess.staffwelfaremanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffwelfaremanage.dto.ThankStatementDTO;
import com.bjike.goddess.staffwelfaremanage.entity.ThankStatement;

/**
* 感谢语持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-04-06 09:14 ]
* @Description:	[ 感谢语持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ThankStatementRep extends JpaRep<ThankStatement ,ThankStatementDTO> { 

 }