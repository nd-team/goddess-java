package com.bjike.goddess.attainment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireOptionUserDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireOptionUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 问卷填写信息表业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-06 11:35 ]
* @Description:	[ 问卷填写信息表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="attainmentSerCache")
@Service
public class SurveyQuestionnaireOptionUserSerImpl extends ServiceImpl<SurveyQuestionnaireOptionUser, SurveyQuestionnaireOptionUserDTO> implements SurveyQuestionnaireOptionUserSer { 

 }