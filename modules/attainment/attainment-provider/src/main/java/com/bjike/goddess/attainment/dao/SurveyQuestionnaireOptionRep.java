package com.bjike.goddess.attainment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireOptionDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireOption;

/**
* 调研表问题选项持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-06 11:26 ]
* @Description:	[ 调研表问题选项持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SurveyQuestionnaireOptionRep extends JpaRep<SurveyQuestionnaireOption ,SurveyQuestionnaireOptionDTO> { 

 }