package com.bjike.goddess.projectissuehandle.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectissuehandle.dto.ProjectProblemAccDTO;
import com.bjike.goddess.projectissuehandle.entity.ProjectProblemAcc;

/**
* 项目中问题受理和处理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-08 03:43 ]
* @Description:	[ 项目中问题受理和处理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProjectProblemAccRep extends JpaRep<ProjectProblemAcc ,ProjectProblemAccDTO> { 

 }