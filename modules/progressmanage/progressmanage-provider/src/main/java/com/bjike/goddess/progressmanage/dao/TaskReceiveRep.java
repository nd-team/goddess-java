package com.bjike.goddess.progressmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.progressmanage.dto.TaskReceiveDTO;
import com.bjike.goddess.progressmanage.entity.TaskReceive;

/**
* 任务接收持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-07-03 02:33 ]
* @Description:	[ 任务接收持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TaskReceiveRep extends JpaRep<TaskReceive ,TaskReceiveDTO> { 

 }