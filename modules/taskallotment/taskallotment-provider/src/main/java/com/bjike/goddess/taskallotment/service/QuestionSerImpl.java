package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.taskallotment.dto.QuestionDTO;
import com.bjike.goddess.taskallotment.entity.Question;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 问题业务实现
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-16 10:36 ]
* @Description:	[ 问题业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="taskallotmentSerCache")
@Service
public class QuestionSerImpl extends ServiceImpl<Question, QuestionDTO> implements QuestionSer { 

 }