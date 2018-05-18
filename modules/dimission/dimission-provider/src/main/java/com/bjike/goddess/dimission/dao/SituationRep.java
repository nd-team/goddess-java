package com.bjike.goddess.dimission.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.dimission.dto.SituationDTO;
import com.bjike.goddess.dimission.entity.Situation;

/**
* 离职办理节点情况持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-28 02:23 ]
* @Description:	[ 离职办理节点情况持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SituationRep extends JpaRep<Situation ,SituationDTO> { 

 }