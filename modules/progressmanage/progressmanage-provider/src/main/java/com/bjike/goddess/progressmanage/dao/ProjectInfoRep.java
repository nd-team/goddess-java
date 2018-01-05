package com.bjike.goddess.progressmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.progressmanage.dto.ProjectInfoDTO;
import com.bjike.goddess.progressmanage.entity.ProjectInfo;

/**
* 项目信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-06-09 04:41 ]
* @Description:	[ 项目信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProjectInfoRep extends JpaRep<ProjectInfo ,ProjectInfoDTO> { 

 }