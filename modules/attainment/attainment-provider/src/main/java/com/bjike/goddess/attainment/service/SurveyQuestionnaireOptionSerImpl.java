package com.bjike.goddess.attainment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireOptionDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireOption;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 调研表 -> 问题 -> 选项业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-06 11:26 ]
* @Description:	[ 调研表 -> 问题 -> 选项业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="attainmentSerCache")
@Service
public class SurveyQuestionnaireOptionSerImpl extends ServiceImpl<SurveyQuestionnaireOption, SurveyQuestionnaireOptionDTO> implements SurveyQuestionnaireOptionSer { 

 }