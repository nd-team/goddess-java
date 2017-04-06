package com.bjike.goddess.attainment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireUserDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 问卷调查历史记录业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-06 11:31 ]
* @Description:	[ 问卷调查历史记录业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="attainmentSerCache")
@Service
public class SurveyQuestionnaireUserSerImpl extends ServiceImpl<SurveyQuestionnaireUser, SurveyQuestionnaireUserDTO> implements SurveyQuestionnaireUserSer { 

 }