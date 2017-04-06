package com.bjike.goddess.attainment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaire;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 调研表 -> 问题业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-06 11:23 ]
* @Description:	[ 调研表 -> 问题业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="attainmentSerCache")
@Service
public class SurveyQuestionnaireSerImpl extends ServiceImpl<SurveyQuestionnaire, SurveyQuestionnaireDTO> implements SurveyQuestionnaireSer { 

 }