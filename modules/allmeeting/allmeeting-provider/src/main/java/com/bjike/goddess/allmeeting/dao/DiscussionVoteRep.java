package com.bjike.goddess.allmeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.allmeeting.dto.DiscussionVoteDTO;
import com.bjike.goddess.allmeeting.entity.DiscussionVote;

/**
* 意见投票持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-06-05 04:25 ]
* @Description:	[ 意见投票持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DiscussionVoteRep extends JpaRep<DiscussionVote ,DiscussionVoteDTO> { 

 }